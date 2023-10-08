package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.DepartmentMapper;
import com.clic.ccdbaas.dao.UserMapper;
import com.clic.ccdbaas.entity.Department;
import com.clic.ccdbaas.entity.SysUser;
import com.clic.ccdbaas.entity.User;
import com.clic.ccdbaas.utils.CompareObjectUtils;
import com.clic.ccdbaas.utils.HttpClientUtils;
import com.clic.ccdbaas.utils.StringUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    DepartmentMapper deptMapper;


    @Value("${app.gettoken.url}")
    private String authUrl;
    @Value("${app.user.url}")
    private String appUserUrl;
    @Value("${app.info.key}")
    private String key;
    @Value("${app.info.secret}")
    private String secret;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public long getUserId(String employeeNo, String password) {
        if (employeeNo != null && password != null) {
            User temp = new User();
            temp.setEmployeeNo(employeeNo);
            temp.setPassword(password);
            return userMapper.getUserId(temp);
        }
        return 0;
    }


    public List getUserId(List smIds) {

        return userMapper.getProductUserInfo(smIds);
    }

    /**
     * 获取应用云accesstoken
     *
     * @return
     */
    public String getAppToken() {
        JSONObject paramJson = new JSONObject();
        paramJson.put("app_key", key);
        paramJson.put("app_secret", secret);
        //   System.setProperty ("jsse.enableSNIExtension", "false");
        String resInfo = HttpClientUtils.HttpsPostWithJson(authUrl, paramJson.toJSONString(), "utf-8");
        JSONObject tokenJson = JSON.parseObject(resInfo);
        String token = tokenJson.getJSONObject("result").getString("access_token");
        return token;
    }

    /**
     * 获取应用云用户信息
     *
     * @return
     */
    public JSONArray getUserInfo(JSONObject queryParam) {
        String token = getAppToken();
        token = "Bearer " + token;
        String nextPageId;
        JSONArray retArr = new JSONArray();

        try {
            do {
                String pageInfo = HttpClientUtils.httpsPostWithAuthInfo(appUserUrl, queryParam.toJSONString(), token);
                JSONObject pageJson = JSON.parseObject(pageInfo).getJSONObject("result").getJSONObject("data");
                nextPageId = pageJson.getString("nextPagingId");
                retArr.addAll(pageJson.getJSONArray("list"));
                queryParam.put("nextPagingId", nextPageId);
            } while (!StringUtils.isEmpty(nextPageId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retArr;
    }

    /**
     * 根据应用云数据初始化用户信息
     *
     * @return
     */
    public String initUserInfo() throws IllegalAccessException {
        // 生产
        JSONObject queryParam = new JSONObject();
        JSONArray userArr = getUserInfo(queryParam);
        // 测试
//        String fileName = "C:\\Users\\Administrator\\Desktop\\project\\应用云人员.txt";
//        String str = FileUtils.txt2String(fileName, "UTF-8");
//        JSONObject userJson = JSON.parseObject(str);
//        JSONArray userArr = userJson.getJSONObject("result").getJSONObject("data").getJSONArray("list");

        Set<String> users = new HashSet<>(userMapper.selectAllSysUserEmpNo());
        Map<String, Long> deptDict = getDeptDict();
        int count = 0;

        for (int i = 0; i < userArr.size(); i++) {
            JSONObject userInfo = userArr.getJSONObject(i);
            String empNo = userInfo.getString("uid");

            String group = userInfo.getString("group");
            if (StringUtils.isEmpty(group)) continue;

            String largeGroup = userInfo.getString("largeGroup");
            if (StringUtils.isEmpty(largeGroup) || largeGroup.equals("中国人寿") ||
                    group.contains("分公司") || group.contains("审计中心")) largeGroup = "寿险公司";
            long deptId = deptDict.get(largeGroup + group);

            SysUser user = new SysUser();
            user.setEmployeeNo(empNo);
            user.setUserName(userInfo.getString("fn"));
            user.setPhonenumber(userInfo.getString("tel"));
            user.setEmail(userInfo.getString("email"));
            user.setStatus("0");
            user.setDelFlag("0");
            user.setGroupId(deptId);
            user.setDeptId(deptMapper.selectDeptById(deptId).getParentId());
            user.setCreateBy("admin");
            user.setPassword("$2a$10$j5VqIYjR44..Kz03eGqQMeCtbfBb6fjK/kpY24rr.HKIGReasJ0Hq");
            user.setNewPassword("admin123");
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setCreateTime(df.parse(df.format(System.currentTimeMillis())));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (users.contains(empNo)) {
                Map originUser = userMapper.selectUserByEmpNo(empNo);
                Map userDict = JSONObject.parseObject(JSONObject.toJSONString(user), Map.class);
                if (!CompareObjectUtils.equals(originUser, userDict)) {
                    userMapper.updateUserByEmpNo(user);
                    count++;
                }
            } else {
                userMapper.insertUserInfo(user);
                //维护角色信息
                Long userId = userMapper.selectUserIdByEmpNo(empNo);
                Map roleMap = new HashMap();
                roleMap.put("userId", userId);
                //默认配置为普通角色
                roleMap.put("roleId", "2");
                userMapper.insertUserRole(roleMap);
                count++;
            }
        }

        return count + " pieces of data from yingyongyun has been initialized successfully!";
    }

    /**
     * 获取全部部门数据
     */
    public void selectDeptAll() {
        List<Department> allDepts = deptMapper.selectDeptAll();
        for (Department dept : allDepts) {
            System.out.println(dept.toString());
        }
    }

    /**
     * 获取部门和部门id字典，key是部门名称，除了顶级vdc名是直接顶级vdc，其他的由大团队和子团队名组成，如 云计算团队云服务管理组，value表示子团队id
     *
     * @return
     */
    public Map<String, Long> getDeptDict() {
        List<Department> depts = deptMapper.selectDeptAll();
        Map<String, Long> groupMap = new HashMap();
        for (Department dept : depts) {
            String group = dept.getDeptName();
            Long id = dept.getDeptId();
            if (dept.getParentId() == 0) {
                groupMap.put(group, id);
                continue;
            }

            String parentGroup = deptMapper.selectDeptById(dept.getParentId()).getDeptName();
            String name = parentGroup + group;

            if (!groupMap.containsKey(name)) groupMap.put(name, id);
        }

        return groupMap;
    }

    /**
     * 根据应用云数据初始化部门数据
     *
     * @return
     */
    public String initDept() {
        // 生产
        JSONObject queryParam = new JSONObject();
        JSONArray data = getUserInfo(queryParam);
        // 测试
//        String fileName = "C:\\Users\\Administrator\\Desktop\\project\\应用云人员.txt";
//        String info = FileUtils.txt2String(fileName, "UTF-8");
//        JSONObject jsonObject = JSONObject.parseObject(info);
//        JSONArray data = jsonObject.getJSONObject("result").getJSONObject("data").getJSONArray("list");
        Map<String, Long> groupMap = getDeptDict();
        int count = 0;
        long topId = groupMap.get("寿险公司");

        for (int i = 0; i < data.size(); i++) {
            JSONObject json = data.getJSONObject(i);
            String group = json.getString("group");
            if (StringUtils.isEmpty(group)) continue;

            String largeGroup = json.getString("largeGroup");
            String currentGroup = largeGroup + group;

//            if (largeGroup.equals("分公司")) continue;
            if (groupMap.containsKey(currentGroup)) continue;

            if (json.containsKey("topGroup")) {
                String topGroup = json.get("topGroup").toString();
                if (largeGroup.equals(topGroup) && !groupMap.containsKey(largeGroup + group)) {
                    long parentId = deptMapper.selectDeptIdByGroupAndParent(topId, largeGroup);
                    deptMapper.insertDept(createDept(group, parentId, 1));
                    groupMap.put(largeGroup + group, deptMapper.selectDeptIdByGroupAndParent(parentId, group));
                    count++;
                    continue;
                }

                if (!groupMap.containsKey(topGroup + largeGroup)) {
                    if (!groupMap.containsKey("寿险公司" + topGroup)) {
                        deptMapper.insertDept(createDept(topGroup, topId, 1));
                        groupMap.put("寿险公司" + topGroup, deptMapper.selectDeptIdByGroupAndParent(topId, topGroup));
                        count++;
                    }

                    Long parentId = groupMap.get("寿险公司" + topGroup);
                    deptMapper.insertDept(createDept(largeGroup, parentId, 0));
                    groupMap.put(topGroup + largeGroup, deptMapper.selectDeptIdByGroupAndParent(parentId, largeGroup));
                    count++;
                }

                Long parentId = groupMap.get(topGroup + largeGroup);
                deptMapper.insertDept(createDept(group, parentId, 0));
                groupMap.put(largeGroup + group, deptMapper.selectDeptIdByGroupAndParent(parentId, group));
                count++;
            } else {
                if (StringUtils.isEmpty(largeGroup)) { // 厂商
                    if (!groupMap.containsKey("寿险公司" + group)) {
                        deptMapper.insertDept(createDept(group, topId, 4));
                        groupMap.put(largeGroup + group, deptMapper.selectDeptIdByGroupAndParent(topId, group));
                        count++;
                    }

                    continue;
                }

                int orderNum = 0;
                if (largeGroup.equals("中国人寿")) {
                    if (!groupMap.containsKey("寿险公司" + group)) {
//                    continue;
                        deptMapper.insertDept(createDept(group, topId, 0));
                        groupMap.put("寿险公司" + group, deptMapper.selectDeptIdByGroupAndParent(topId, group));
                        count++;
                    }

                    continue;
                } else if (largeGroup.contains("分公司")) orderNum = 2;
                else if (largeGroup.contains("审计中心")) orderNum = 3;
                else orderNum = 4;

                if (!groupMap.containsKey("寿险公司" + largeGroup)) {
                    deptMapper.insertDept(createDept(largeGroup, topId, orderNum));
                    groupMap.put("寿险公司" + largeGroup, deptMapper.selectDeptIdByGroupAndParent(topId, largeGroup));
                    count++;
                }

                long parentId = groupMap.get("寿险公司" + largeGroup);
                deptMapper.insertDept(createDept(group, parentId, 0));
                groupMap.put(largeGroup + group, deptMapper.selectDeptIdByGroupAndParent(parentId, group));
                count++;
            }
        }

        return count + " pieces of data from yingyongyun has been initialized successfully!";
    }

    /**
     * 创建部门
     *
     * @param deptName
     * @param parentId
     * @param orderNum
     * @return
     */
    public Department createDept(String deptName, long parentId, int orderNum) {
        Department dept = new Department();
        dept.setDeptName(deptName);
        dept.setParentId(parentId);
        dept.setCreateBy("admin");
        dept.setStatus("0");
        dept.setDelFlag("0");
        dept.setOrderNum(orderNum);
        dept.setAncestors(deptMapper.selectDeptById(parentId).getAncestors() + "," + parentId);
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dept.setCreateTime(df.parse(df.format(System.currentTimeMillis())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dept;
    }

    /**
     * 根据工号获取员工信息
     *
     * @param empNo
     * @return
     */
    public Map getUserCardInfoByEmpNo(String empNo) {
        Map userInfo = userMapper.selectUserByEmpNo(empNo);
        String ancestors = (String) userInfo.get("ancestors");
        String[] deptIds = ancestors.split(",");
        for (int i = 0; i < deptIds.length; i++) {
            String deptId = deptIds[i].trim();
            if (deptId.equals("0")) continue;

            String deptName = deptMapper.selectDeptNameById(deptId);
            userInfo.put("dept" + i, deptName);
        }

        return userInfo;
    }

    /**
     * 定时更新部门和用户数据
     */
    @XxlJob("updateUserInfo")
    public void updateUserInfo() {
        initDept();
        try {
            initUserInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
