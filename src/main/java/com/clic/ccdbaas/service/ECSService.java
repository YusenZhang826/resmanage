package com.clic.ccdbaas.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.entity.StorageDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * description:弹性云服务
 */
@Service
public class ECSService {
    private static final Logger logger = LoggerFactory.getLogger(ECSService.class);

    @Autowired
    private OcService ocService;

    @Value("${oc.cmdb.vm}")
    private String physicalList;

    //查找JSONObject的resid
    public String findResid(JSONObject jsob) {
        if (!jsob.containsKey("id")) {
            return "";
        } else {
            return jsob.getString("id");
        }
    }

    public JSONArray getJson(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, physicalList);
    }

    public JSONArray getX86ServerArr(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, physicalList);
    }


    public JSONArray getPhysicalHostArr(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, physicalList);
    }

    public JSONArray getJson() {
        return ocService.getAllInstanceInfo(physicalList);
    }

    /**
     * 使用参数修改单个资源实例
     *
     * @param postJson
     * @return
     */
    public boolean updateData(String postJson) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(JSON.parse(postJson));
        //判断json串中有没有res字段
        String resid = "";
        if (findResid(jsonObject) == "") {
            return false;
        } else {
            resid = findResid(jsonObject);
        }
        jsonObject.remove("id");
        String tmp = jsonObject.toString();
        String resId = "{" + "\"id\"" + ":" + "\"" + resid + "\"" + ",";
        tmp = tmp.substring(1);
        tmp = "[" + resId + tmp + "]";
        System.out.println("tmp" + tmp);
        try {
            ocService.updateOCInstances(physicalList, tmp);
            logger.info("更新成功");
        } catch (Exception e) {
            logger.info("更新失败");
        }
        return true;
    }

    /**
     * 使用参数批量修改资源实例
     *
     * @param physiclist
     * @return
     */
    public boolean updatePhysicalList(JSONArray physiclist) {
        try {
            for (int i = 0; i < physiclist.size(); i++) {
                JSONObject obj = physiclist.getJSONObject(i);
                updateData(obj.toString());
            }
            logger.info("物理服务器批量更新成功");
        } catch (Exception e) {
            logger.info("物理服务器批量更新失败");
            return false;
        }
        return true;
    }


    /**
     * 批量删除
     *
     * @param paramJson
     */
    public void delelteDevice(JSONArray paramJson) {
        logger.info("删除设备请求报文为:" + paramJson.toString());
        for (int i = 0; i < paramJson.size(); i++) {
            ocService.deleteOcClassInstances(physicalList, paramJson.get(i).toString());
            logger.info("删除资源成功,设备id为" + paramJson.get(i).toString());
        }
    }

    /**
     * 批量删除
     *
     * @param instanceId
     */
    public JSONObject getMessage(String instanceId) {
        logger.info("查询设备详情id为" + instanceId);
        JSONObject allInstanceInfo = ocService.getAllInstanceInfo(physicalList, instanceId);
        logger.info("设备详情：" + allInstanceInfo);
        return allInstanceInfo;
    }

    public JSONObject insertDevice(JSONObject requestParams) {
        List list = new ArrayList();
        list.add(requestParams.toString());
        String result = ocService.addInstances(physicalList, list.toString());
        return JSON.parseObject(result);
    }

    public void exportDevice(JSONArray list, HttpServletResponse response) {
        JSONArray request = null;
        try {
            if (!StringUtils.isEmpty(list)) {
                request = list;
            } else {
                request = getJson();
            }
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            ocService.setExcelRespProp(response, "库存设备列表");
            List<StorageDevice> memberList = request.toJavaList(StorageDevice.class);
            logger.info("Excel导出库存设备信息" + memberList.toString());
            EasyExcel.write(response.getOutputStream())
                    .head(StorageDevice.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("库存设备列表")
                    .doWrite(memberList);
        } catch (Exception e) {
            logger.info("库存设备列表失败");
        }
    }

}
