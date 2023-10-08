package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.Annotation.Alias;
import com.clic.ccdbaas.Annotation.EnumNote;
import com.clic.ccdbaas.dao.ComplianceRuleMapper;
import com.clic.ccdbaas.dao.RelationMapper;
import com.clic.ccdbaas.dao.ResourceTypeMapper;
import com.clic.ccdbaas.dao.ScheduledTaskMapper;
import com.clic.ccdbaas.entity.ComplianceRule;
import com.clic.ccdbaas.utils.PageUtils;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;


@Service
public class ComplianceRuleService {

    private static final Logger logger = LoggerFactory.getLogger(ComplianceRuleService.class);
    @Autowired
    ComplianceRuleMapper complianceRuleMapper;
    @Autowired
    ScheduledTaskMapper scheduledTaskMapper;
    @Autowired
    ScheduledTaskService taskService;
    @Autowired
    OcService ocService;
    @Autowired
    RelationService relationService;
    @Autowired
    ResourceTypeMapper resourceTypeMapper;
    @Autowired
    RelationMapper relationMapper;
    @Value("${oc.cmdb.vm}")
    String cloudVM;
    @Value("${oc.cmdb.vm.alias}")
    String cloudVMAlias;
    private final String entityPrefix = "com.clic.ccdbaas.entity.";
    private final String searchCategoryId = "A313DE6D18954D15A87BED1A65BFC4BF";

    public Map getComplianceRuleByResId(String resId) throws ClassNotFoundException {
        Map ruleInfo = complianceRuleMapper.getComplianceRuleByResId(resId);
        int type = (int) ruleInfo.get("type");
        if (type == 1) return ruleInfo;

        int checkType = (int) ruleInfo.get("checkType");
        JSONArray conditions = JSONArray.parseArray((String) ruleInfo.get("conditionOp"));
        if (checkType == 1) {
            ruleInfo.put("conditionOp", conditions);
            return ruleInfo;
        }

        String resourceId = (String) ruleInfo.get("resourceTypeId");
        Map<String, String> entityField = getClassField(resourceTypeMapper.selectEntityClassName(resourceId));
        for (int i = 0; i < conditions.size(); i++) {
            JSONObject conditionObj = conditions.getJSONObject(i);
            String domainName = conditionObj.getString("domainName");
            String searchType = conditionObj.getString("searchType");

            JSONArray valuesArr = conditionObj.getJSONArray("values");
            StringBuilder valueStr = new StringBuilder();
            if (valuesArr.size() > 0) valueStr.append(valuesArr.getString(0));
            conditionObj.put("values", valueStr.toString());

            if (searchType.equals("is null") || searchType.equals("is not null") || searchType.equals("repeat"))
                conditionObj.put("isShowSearch", false);
            else conditionObj.put("isShowSearch", true);
            String domainType = entityField.get(domainName);
            conditionObj.put("type", domainType);
        }
        ruleInfo.put("conditionOp", conditions);

        return ruleInfo;
    }

    public List<Map> getAllComplianceRules(ComplianceRule searchInfo) {
        if (!StringUtils.isEmpty(searchInfo.getStatusStr())) {
            String statusStr = searchInfo.getStatusStr();
            List<Integer> statusList = new ArrayList<>();
            String[] statusArr = statusStr.split(",");
            for (String status : statusArr) statusList.add(Integer.parseInt(status));
            searchInfo.setStatusArr(statusList);
        }
        return complianceRuleMapper.getAllComplianceRules(searchInfo);
    }

    /**
     * 立即执行
     *
     * @param resIds
     */
    public void executeSelectByResId(JSONArray resIds) throws SchedulerException {
        for (int i = 0; i < resIds.size(); i++) {
            String resId = resIds.getString(i);
            taskService.executeJob(resId);
        }
    }

    public JSONArray executeSelectByParam(String resId) {
        Map complianceRule = complianceRuleMapper.getComplianceRuleByResId(resId);
        String conditionOp = (String) complianceRule.get("executeStatement");
        JSONObject jsonObject = JSONObject.parseObject(conditionOp);

        logger.info(jsonObject.toJSONString());
        String tableName = jsonObject.get("tableName").toString();
        String conditions = "";
        JSONArray conditionList = jsonObject.getJSONArray("conditionList");
        JSONArray logicList = jsonObject.getJSONArray("logic");
        for (int i = 0; i < conditionList.size(); i++) {
            String item = "";
            JSONObject obj = conditionList.getJSONObject(i);
            item = obj.getString("attribute") + obj.getString("condition") + obj.getString("value");
            conditions = item + logicList.getString(i);
        }
        return complianceRuleMapper.executeSelectByParam(tableName, conditions);
    }

    /**
     * 获取配置审计规则结果集
     *
     * @param searchObjs
     * @return
     */
    public List<Map> getTaskResult(Map<String, String[]> searchObjs) throws ClassNotFoundException {
        String ruleId = searchObjs.get("ruleId")[0];
        if (complianceRuleMapper.selectStatus(ruleId) == 0) return new ArrayList<>();

        Map fuzzySearchDict = new HashMap<>();
        Map enumSearchDict = new HashMap();
        Map<String, String> searchDict = new HashMap<>();
        for (Map.Entry<String, String[]> entry : searchObjs.entrySet()) {
            String key = entry.getKey();
            if (key.equals("pageNum") || key.equals("pageSize") || key.equals("ruleId")) continue;

            String value = entry.getValue()[0];
            searchDict.put(key, value);
        }

        String resourceTypeId = complianceRuleMapper.selectResourceTypeId(ruleId);
        getSearchDict(searchDict, fuzzySearchDict, enumSearchDict, resourceTypeId);

        String tableName = resourceTypeMapper.getDBTableNameById(resourceTypeId);
        Map params = new HashMap<>();
        params.put("tableName", tableName);
        params.put("primaryKey", "resId");
        params.put("ruleId", ruleId);

        PageUtils.startPage();
        return scheduledTaskMapper.getResult(params, fuzzySearchDict, enumSearchDict, null);
    }


    /**
     * 解析枚举查询参数字典
     *
     * @param searchObjs
     * @return
     */
    public void getSearchDict(Map<String, String> searchObjs, Map fuzzySearchDict, Map enumSearchDict, String resourceTypeId)
            throws ClassNotFoundException {
        Map<String, Map> enumsDict = getEnumField(resourceTypeId);

        for (Iterator<String> iter = searchObjs.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            String value = searchObjs.get(key);
            // 乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            // 枚举类型值映射
            String enumKey = key;
            if (key.equals("class_Name")) enumKey = "className";
            if (enumsDict.containsKey(enumKey)) {
                Map<String, String> enums = (Map<String, String>) enumsDict.get(enumKey);
                for (String k : enums.keySet()) {
                    if (k.contains(value)) {
                        value = enums.get(k);
                        break;
                    }
                }
                enumSearchDict.put(key, value);
            } else {
                fuzzySearchDict.put(key, value);
            }
        }
    }

    /**
     * 导出
     *
     * @param jsonObj
     * @return
     */
    public List<Map> exportTaskResult(JSONObject jsonObj) throws ClassNotFoundException {
        String ruleId = jsonObj.getString("ruleId");
        if (complianceRuleMapper.selectStatus(ruleId) == 0) return new ArrayList<>();

        HashSet<String> resIdSet = null;
        if (jsonObj.containsKey("resIds")) {
            JSONArray resIds = jsonObj.getJSONArray("resIds");
            resIdSet = new HashSet<>(resIds.toJavaList(String.class));
        }
        jsonObj.remove("ruleId");
        jsonObj.remove("resIds");

        Map searchDict = JSONObject.toJavaObject(jsonObj, Map.class);
        Map fuzzySearchParams = new HashMap();
        Map enumSearchParam = new HashMap();
        String resourceTypeId = complianceRuleMapper.selectResourceTypeId(ruleId);
        getSearchDict(searchDict, fuzzySearchParams, enumSearchParam, resourceTypeId);

        String tableName = resourceTypeMapper.getDBTableNameById(resourceTypeId);
        Map params = new HashMap<>();
        params.put("tableName", tableName);
        params.put("primaryKey", "resId");
        params.put("ruleId", ruleId);

        return scheduledTaskMapper.getResult(params, fuzzySearchParams, enumSearchParam, resIdSet);
    }


    /**
     * 根据资源类型名称获取对应资源的实体类型
     *
     * @param className
     * @return
     */
    public Class getResourceEntityClass(String className) throws ClassNotFoundException {
        return Class.forName(entityPrefix + className);
    }

    /**
     * 根据资源类型id获取对应实体类信息
     *
     * @param ruleId
     * @return
     */
    public Map getResourceEntity(String ruleId) {
        String resoureId = complianceRuleMapper.selectResourceTypeId(ruleId);
        return resourceTypeMapper.selectEntityByRId(resoureId);
    }

    /**
     * 新建配置审计规则
     *
     * @param obj
     * @return 执行码：0代表执行成功；1代表操作失败，规则名称已存在。。
     * @throws SchedulerException
     */
    public int addComplianceRule(JSONObject obj) throws SchedulerException, NoSuchFieldException, ClassNotFoundException {
        if (complianceRuleMapper.countAppointedName(obj.getString("name").trim(), null) > 0) return 1;

        parseCondition(obj);
        obj.put("resId", IdUtils.generatedUUID());
        obj.put("status", 1);
        obj.put("createTime", new Date());
        obj.put("lastModified", new Date());
        obj.put("type", 2);
        obj.put("configFlag", 1);
        String categoryId = obj.getString("categoryId");
        obj.put("searchType", searchCategoryId.equals(categoryId) ? 0 : 1);
        taskService.startScheduledTask(obj);
        complianceRuleMapper.addComplianceRule(JSONObject.parseObject(obj.toString(), ComplianceRule.class));

        return 0;
    }

    /**
     * 修改配置审计规则
     * * @param obj
     * * @return 执行码：0代表执行成功；1代表操作失败，规则名称已存在。。
     * * @throws SchedulerException
     */
    public int updateComplianceRule(JSONObject obj) throws SchedulerException, NoSuchFieldException, ClassNotFoundException {
        String resId = obj.getString("resId");
        String newName = obj.getString("name");
        if (complianceRuleMapper.countAppointedName(newName.trim(), resId) > 0) return 1;
        String categoryId = obj.getString("categoryId");
        int searchType = searchCategoryId.equals(categoryId) ? 0 : 1;
        obj.put("searchType", searchType);
        Map oldRule = complianceRuleMapper.getComplianceRuleByResId(resId);

        obj.put("lastModified", new Date());
        boolean periodChanged = false, conditionChanged = false;
        if ((obj.getInteger("triggerMechanism")) == 1) {
            String originExecPeriod = (String) oldRule.get("execPeriod");
            //修改了定时任务执行周期或语句
            if (!originExecPeriod.equals(obj.getString("execPeriod"))) periodChanged = true;
        }

        String originConditionOP = (String) oldRule.get("conditionOp");
        if (!originConditionOP.equals(obj.getString("conditionOp"))) {
            parseCondition(obj);
            conditionChanged = true;
        }

        if (periodChanged || conditionChanged)
            taskService.updateScheduledTask(obj);

        complianceRuleMapper.updateComplianceRule(JSONObject.parseObject(obj.toString(), ComplianceRule.class));
        return 0;
    }

    /**
     * 解析处理condition条件，并生成sql语句
     *
     * @param obj
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public void parseCondition(JSONObject obj) throws ClassNotFoundException, NoSuchFieldException {
        String resourceTypeId = obj.getString("resourceTypeId");
        String entityName = resourceTypeMapper.selectEntityClassName(resourceTypeId);
        String tName = resourceTypeMapper.getDBTableNameById(resourceTypeId);
        JSONArray conditionArr = obj.getJSONArray("conditionOp");
        for (int i = 0; i < conditionArr.size(); i++) {
            JSONObject conditionObj = conditionArr.getJSONObject(i);
            String domainName = conditionObj.getString("domainName");
            String searchType = conditionObj.getString("searchType");

            String valueStr = "";
            if (searchType.equals("repeat")) valueStr = tName;
            else valueStr = conditionObj.getString("values");

            String domainType = Class.forName(entityPrefix + entityName).getDeclaredField(domainName).getType().getSimpleName();
            JSONArray values = new JSONArray();
            if (domainType.equals("String")) {
                values.add(valueStr);
            } else if (domainType.equals("Integer") || domainType.equals("int")) {
                values.add(Integer.valueOf(valueStr));
            }
            conditionObj.put("values", values);
        }

        JSONObject conditionOp = new JSONObject();
        conditionOp.put("className", entityName);
        conditionOp.put("conditions", conditionArr);

        AdvancedSearch advancedSearch = JSONObject.parseObject(conditionOp.toString(), AdvancedSearch.class);
        String sql = advancedSearch.createSelectSql(tName);
        obj.put("executeStatement", sql);
    }

    public void deleteComplianceRule(String resId) throws SchedulerException {
        complianceRuleMapper.deleteComplianceRule(resId);
        scheduledTaskMapper.deleteScheduledTaskByRuleId(resId);
        taskService.deleteScheduledTaskByRuleId(resId);
    }

    public void pauseComplianceRule(String resId, String updateUser) throws SchedulerException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("resId", resId);
        map.put("status", 0);
        map.put("lastModified", new Date());
        map.put("lastUpdateBy", updateUser);
        complianceRuleMapper.updateComplianceRuleStatus(map);
        taskService.shutdownScheduledTask(resId);
    }

    public void resumeComplianceRule(String resId, String updateUser) throws SchedulerException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("resId", resId);
        map.put("status", 1);
        map.put("lastModified", new Date());
        map.put("lastUpdateBy", updateUser);
        complianceRuleMapper.updateComplianceRuleStatus(map);
        taskService.resumeScheduledTask(resId);
    }

    public Boolean addComplianceRuleClass(JSONObject ruleClass) {
        if (complianceRuleMapper.selectRuleClassByName(ruleClass.getString("categoryName")) == null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", IdUtils.generatedUUID());
            map.put("categoryName", ruleClass.getString("categoryName"));
            map.put("createTime", new Date());
            map.put("last_Modified", new Date());
            map.put("createdBy", ruleClass.getString("createdBy"));
            complianceRuleMapper.addComplianceRuleClass(map);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateComplianceRuleClass(JSONObject ruleClass) {
        if (complianceRuleMapper.selectRuleClassByName(ruleClass.getString("categoryName")) == null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", ruleClass.getString("id"));
            map.put("categoryName", ruleClass.getString("categoryName"));
            map.put("last_Modified", new Date());
            complianceRuleMapper.updateComplianceRuleClass(map);
            return true;
        } else {
            return false;
        }
    }

    public List<HashMap> getAllComplianceRuleClass(String pageNum, String pageSize) {
        int num = -1, size = 1;
        if (pageNum != null && pageSize != null) {
            num = Integer.parseInt(pageNum) - 1;
            size = Integer.parseInt(pageSize);
        }

        return complianceRuleMapper.getAllComplianceRuleClass(num * size, size);
    }

    public void deleteComplianceRuleClass(String id) {
        complianceRuleMapper.deleteComplianceRuleClass(id);
    }

    /**
     * 获取检查范围的资源类型列表
     *
     * @return
     */
    public List<Map> getResourceTypeList() {
        return resourceTypeMapper.selectAllResourceType();
    }

    /**
     * 获取结果集对应表头数据
     *
     * @return
     */
    public List<Map> getTableHeader(String resId) throws NoSuchFieldException, ClassNotFoundException {
        String resourceId = complianceRuleMapper.selectResourceTypeId(resId);
        return getResourceAttributes(resourceId);
    }

    /**
     * 获取实体类各属性名和属性类型映射表
     *
     * @param className
     * @return
     */
    public Map<String, String> getClassField(String className) throws ClassNotFoundException {
        Class clazz = Class.forName(entityPrefix + className);
        Map<String, String> ret = new HashMap();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Alias alias = field.getAnnotation(Alias.class);
            if (alias == null) continue;

            String name = field.getName();
            String type = alias.businessType();
            ret.put(name, type);
        }
        return ret;
    }

    /**
     * 获取指定资源实体类的枚举类型字典，key为字段名，value为枚举值字典
     *
     * @param resourceId
     * @return
     * @throws ClassNotFoundException
     */
    public Map<String, Map> getEnumField(String resourceId) throws ClassNotFoundException {
        Map<String, Map> ret = new HashMap<>();
        String className = resourceTypeMapper.selectEntityClassName(resourceId);
        if (className.equals("HostInstance")) className = "CloudVmare";
        Class clazz = Class.forName(entityPrefix + className);
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            EnumNote enumNote = field.getAnnotation(EnumNote.class);
            if (enumNote == null) continue;

            String fieldName = field.getName();
            String[] code = enumNote.code();
            String[] values = enumNote.value();
            Map<String, String> enums = new HashMap<>();
            for (int j = 0; j < code.length; j++) enums.put(values[j], code[j]);
            ret.put(fieldName, enums);
        }
        return ret;
    }

    /**
     * 根据资源类型id获取该资源的属性字段列表
     *
     * @param resourceId
     * @return
     */
    public List<Map> getResourceAttributes(String resourceId) throws NoSuchFieldException, ClassNotFoundException {
        String resourceClassName = resourceTypeMapper.selectEntityClassName(resourceId);
        Class clazz = Class.forName(entityPrefix + resourceClassName);
        List<Map> ret = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Map note = new HashMap();
            Alias alias = field.getAnnotation(Alias.class);
            if (alias == null) continue;

            note.put("key", i);
            note.put("name", alias.columnName());
            note.put("showText", alias.alias());
            note.put("type", alias.businessType());
            ret.add(note);
        }
        return ret;
    }

    /**
     * 获取指定枚举型字段取值范围
     *
     * @param resourceId
     * @param name
     * @return
     */
    public List<Map> getGroupBySet(String resourceId, String name) throws ClassNotFoundException, NoSuchFieldException {
        String resourceClassName = resourceTypeMapper.selectEntityClassName(resourceId);
        Class clazz = Class.forName(entityPrefix + resourceClassName);
        List<Map> ret = new ArrayList<>();
        Field field = clazz.getDeclaredField(name);
        EnumNote enumNote = field.getAnnotation(EnumNote.class);
        if (enumNote == null) return ret;

        String[] code = enumNote.code();
        String[] valus = enumNote.value();
        if (code.length != valus.length) return ret;

        for (int i = 0; i < code.length; i++) {
            Map item = new HashMap();
            item.put("value", code[i]);
            item.put("name", valus[i]);
            ret.add(item);
        }

        return ret;
    }

    /**
     * 资源关联关系落库
     */
    public void loadRelationData2DB(JSONArray classNames) {
        for (int i = 0; i < classNames.size(); i++) {
            String className = classNames.getString(i);
            JSONArray relationArr = ocService.getAllRelationClass(className);
            for (int j = 0; j < relationArr.size(); j++) {
                JSONObject relationObj = relationArr.getJSONObject(j);
                JSONObject sourceRemark = relationObj.getJSONObject("sourceRemark");
                JSONObject targetRemark = relationObj.getJSONObject("targetRemark");
                if (sourceRemark == null || targetRemark == null) continue;

                String sourceClassName = relationObj.getString("sourceClassName");
                String targetClassName = relationObj.getString("targetClassName");
                String name = relationObj.getString("name");
                int cardinality = relationObj.getInteger("cardinality");
                JSONObject remark = new JSONObject();
                remark.put("sourceRemark", sourceRemark);
                remark.put("targetRemark", targetRemark);
                relationService.addRelation(sourceClassName, targetClassName, name, cardinality, remark.toString());
            }
        }
    }

    /**
     * 获取资源className和对应中文名映射关系
     *
     * @return
     */
    public Map<String, String> getResourceInfoMap() {
        JSONArray resourceArr = ocService.getAllResourceTypeInfo();
        Map<String, String> retDict = new HashMap<>();
        for (int i = 0; i < resourceArr.size(); i++) {
            JSONObject resourceObj = resourceArr.getJSONObject(i);
            String className = resourceObj.getString("name");
            JSONObject displayName = resourceObj.getJSONObject("displayName");
            if (displayName == null || displayName.size() == 0) continue;

            retDict.put(className, displayName.getString("zh_CN"));
        }

        return retDict;
    }

    /**
     * 获取已入库资源的className集合
     *
     * @return
     */
    public Set<String> getLoadedResourceInfo() {
        Set<String> resourceClassSet = relationMapper.getResourceClassName("sourceClassName");
        resourceClassSet.addAll(relationMapper.getResourceClassName("targetClassName"));
        return resourceClassSet;
    }

    /**
     * 资源类型数据落库
     */
    public void loadResourceTypeData2DB() {
        Map<String, String> resourceInfo = getResourceInfoMap();
        Set<String> loadedResourceInfo = getLoadedResourceInfo();

        // 只更新已经有关联关系记录的资源信息
        for (Map.Entry<String, String> entry : resourceInfo.entrySet()) {
            String className = entry.getKey();
            if (!loadedResourceInfo.contains(className)) continue;

            String name = entry.getValue();
            String resId = IdUtils.generatedUUID();
            Map<String, String> updateinfo = new HashMap<>();
            updateinfo.put("resId", resId);
            updateinfo.put("className", className);
            updateinfo.put("name", name);
            resourceTypeMapper.insertResourceType(updateinfo);
        }
    }

    /**
     * 获取指定className的资源的关联关系描述列表
     *
     * @param className
     * @return
     */
    public List<Map> getRelationsByClassName(String className) {
        List<HashMap> relations = relationMapper.getRelationBySingleClassName(className);
        List<Map> relationsDesc = new ArrayList<>();

        for (Map relation : relations) {
            JSONObject remarkJSON = JSONObject.parseObject((String) relation.get("remark"));
            if (remarkJSON == null || remarkJSON.size() == 0) continue;
            if (!remarkJSON.containsKey("sourceRemark") || !remarkJSON.containsKey("targetRemark")) continue;

            String resId = (String) relation.get("resId");
            String sourceClassName = (String) relation.get("sourceClassName");
            String targetClassName = (String) relation.get("targetClassName");
            String remark = "", relateName = "";
            if (sourceClassName.equals(className)) {
                remark = remarkJSON.getJSONObject("sourceRemark").getString("zh_CN");
                relateName = resourceTypeMapper.selectNameByClassName(targetClassName);
            } else {
                remark = remarkJSON.getJSONObject("targetRemark").getString("zh_CN");
                relateName = resourceTypeMapper.selectNameByClassName(sourceClassName);
            }

            Map info = new HashMap();
            info.put("name", remark + "-" + relateName);
            info.put("value", resId);
            relationsDesc.add(info);
        }

        return relationsDesc;
    }

    /**
     * 获取指定resourceid的资源的关联关系描述列表
     *
     * @param resourceId
     * @return
     */
    public List<Map> getRelationsByResourceId(String resourceId) {
        String className = resourceTypeMapper.selectOcClassName(resourceId);
        List<Map> relationsDesc = getRelationsByClassName(className);
        if (className.equals(cloudVM)) relationsDesc.addAll(getRelationsByClassName(cloudVMAlias));

        return relationsDesc;
    }

    /**
     * 统计不同状态的规则数量
     *
     * @return
     */
    public List<Map> countRuleStatus(JSONObject jsonObj) {
        ComplianceRule complianceRule = JSONObject.parseObject(jsonObj.toJSONString(), ComplianceRule.class);
        if (!StringUtils.isEmpty(complianceRule.getStatusStr())) {
            String statusStr = complianceRule.getStatusStr();
            List<Integer> statusList = new ArrayList<>();
            String[] statusArr = statusStr.split(",");
            for (String status : statusArr) statusList.add(Integer.parseInt(status));
            complianceRule.setStatusArr(statusList);
        }

        return complianceRuleMapper.countRuleGroupByStatus(complianceRule);
    }

    /**
     * 配置不合规资源统计的配置审计显示规则
     *
     * @param resIdArr
     */
    public void configSelectedItem(JSONArray resIdArr) {
        List<String> resIds = resIdArr.toJavaList(String.class);
        complianceRuleMapper.updateConfigFlag(null);
        complianceRuleMapper.updateConfigFlag(resIds);
    }

    /**
     * 落库存在某实例关系的数据
     *
     * @param obj
     */
    public void storeRelationInstance2DB(JSONObject obj) {
        String relationName = obj.getString("relationName");
        String relationId = relationMapper.getResIdByRelationName(relationName);
        String sourcePK = obj.getString("sourcePK");
        String targetPK = obj.getString("targetPK");
        JSONArray relationInstances = ocService.getAllRelationInfo(relationName);

        for (int i = 0; i < relationInstances.size(); i++) {
            JSONObject relationInstance = relationInstances.getJSONObject(i);

            HashMap<String, Object> insertInfo = new HashMap<>();
            insertInfo.put("resId", IdUtils.generatedUUID());
            insertInfo.put("sourceInstanceId", relationInstance.getString(sourcePK));
            insertInfo.put("targetInstanceId", relationInstance.getString(targetPK));
            insertInfo.put("relationId", relationId);
            insertInfo.put("lastModified", relationInstance.getTimestamp("last_Modified").toLocalDateTime());
            insertInfo.put("extraSpec", relationInstance.toString());
            relationMapper.addRelationInstance(insertInfo);
        }
    }
}
