package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.RelationMapper;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.utils.PageUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class RelationService {
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private DetailService detailService;

    /**
     *单向获取资源关联关系主键，按照源和目标的顺序查找
     */
    public String getResIdByClassName(String source, String target){
        HashMap<String, String> map = new HashMap<>();
        map.put("source", source);
        map.put("target", target);
        return relationMapper.getResIdByClassName(map);
    }

    public String addRelation(String source, String target, String name, int cardinality, String remark) {
        HashMap<String, Object> map = new HashMap<>();
        String resId = IdUtils.generatedUUID();
        map.put("resId", resId);
        map.put("name", name);
        map.put("sourceClassName", source);
        map.put("targetClassName", target);
        map.put("cardinality", cardinality);
        map.put("remark", remark);
        String temp = getResIdByClassName(source,target);
        if(temp == null){
            relationMapper.addRelation(map);
            return resId;
        }else{
            map.put("resId",temp);
            relationMapper.updateRelation(map);
            return temp;
        }
    }

    public void addRelationInstance(String sourceInstanceId, String targetInstanceId, String relationId, String extraSpec) {
        HashMap<String, Object> map = new HashMap<>();
        String resId = IdUtils.generatedUUID();
        map.put("resId", resId);
        map.put("sourceInstanceId", sourceInstanceId);
        map.put("targetInstanceId", targetInstanceId);
        map.put("relationId", relationId);
        map.put("lastModified", new Date());
        map.put("extraSpec", extraSpec);
        String id = relationMapper.getResIdByInstanceId(map);
        if(id == null){
            relationMapper.addRelationInstance(map);
        }else{
            map.put("resId", id);
            relationMapper.updateRelationInstance(map);
        }
    }



    public JSONArray getRelationClass(String className) {
        JSONArray res = new JSONArray();
        List<HashMap> relations = relationMapper.getRelationBySingleClassName(className);
        for(HashMap map : relations){
            JSONObject obj = new JSONObject();
            obj.put("detail", map);
            if(map.get("sourceClassName").equals(className)){
                obj.put("relationClass", map.get("targetClassName"));
            }else {
                obj.put("relationClass", map.get("sourceClassName"));
            }
            res.add(obj);
        }
        return res;
    }

    public List<JSONObject> getAllRelationInstance(JSONObject object){
        List<JSONObject> result = new ArrayList<>();
        String className = object.getString("className");
        List<HashMap> relationClass = new ArrayList<>();
        if(object.containsKey("relationClass")){
            HashMap info = getRelationByClassName(object.getString("className"), object.getString("relationClass"));
            if(info== null) {
                info = getRelationByClassName(object.getString("relationClass"), object.getString("className"));
                //flag为1时查询的className为targetClass，为0时为sourceClass
                info.put("flag", 1);
            }else {
                info.put("flag", 0);
            }
            info.put("relationClass", object.getString("relationClass"));
            relationClass.add(info);
            System.out.println(relationClass);
        }else{
            List<HashMap> tempRelation = relationMapper.getRelationBySingleClassName(className);
            for(HashMap temp : tempRelation){
                HashMap<String, String> relation = temp;
                if(temp.get("sourceClassName").equals(className)){
                    relation.put("flag","0");
                    relation.put("relationClass", (String) temp.get("targetClassName"));
                }else{
                    relation.put("flag", "1");
                    relation.put("relationClass", (String) temp.get("sourceClassName"));
                }
                relationClass.add(relation);
            }
        }
        System.out.println(relationClass);
        for(HashMap relation : relationClass){
            JSONObject relationObj = new JSONObject();
            relationObj.put("relationClassName", relation.get("relationClass"));
            JSONArray relationInfos = new JSONArray();
            if(relation.get("flag").equals("1")){
                List<HashMap> instances = getRelationInstanceByRelationIdAndTargetInstanceId((String) relation.get("resId"),object.getString("resId"));
                for(HashMap instance : instances){
                    instance.put("relationInstanceId",instance.get("sourceInstanceId"));
                    instance.remove("sourceInstanceId");
                    JSONObject obj = new JSONObject(instance);
                    obj.put("detail", detailService.getInstanceDetail((String) relation.get("relationClass"), (String) instance.get("relationInstanceId")));
                    relationInfos.add(obj);
                }
            }else {
                List<HashMap> instances = getRelationInstanceByRelationIdAndSourceInstanceId((String) relation.get("resId"),object.getString("resId"));
                for(HashMap instance : instances){
                    instance.put("relationInstanceId",instance.get("targetInstanceId"));
                    instance.remove("targetInstanceId");
                    JSONObject obj = new JSONObject(instance);
                    obj.put("detail", detailService.getInstanceDetail((String) relation.get("relationClass"), (String) instance.get("relationInstanceId")));
                    relationInfos.add(obj);
                }
            }
            relationObj.put("relationInfo",relationInfos);
            result.add(relationObj);
        }
        return result;
    }

    private List<HashMap> getRelationInstanceByRelationIdAndSourceInstanceId(String relationId, String sourceInstanceId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("relationId", relationId);
        map.put("sourceInstanceId", sourceInstanceId);
        return relationMapper.getInstanceBySource(map);
    }

    private List<HashMap> getRelationInstanceByRelationIdAndTargetInstanceId(String relationId, String targetInstanceId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("relationId", relationId);
        map.put("targetInstanceId", targetInstanceId);
        return relationMapper.getInstanceByTarget(map);
    }

    private HashMap getRelationByClassName(String className, String relationClass) {
        HashMap<String, String> map = new HashMap<>();
        map.put("source", className);
        map.put("target", relationClass);
        return relationMapper.getRelationByClassName(map);
    }

    public TableDataInfo getRelationInstance(JSONObject object) {
        List<JSONObject> result = new ArrayList<>();
        TableDataInfo page;
        if(object.getInteger("flag") == 0){
            List<HashMap> instances = getRelationInstanceByRelationIdAndSourceInstanceId(object.getString("relationId"),object.getString("resId"));
            page = PageUtils.getDataTable(instances);
            for(HashMap instance : instances) {
                JSONObject obj = new JSONObject(instance);
                obj.put("relationInstanceId", instance.get("targetInstanceId"));
                obj.put("lastModified", instance.get("lastModified"));
                obj.put("extraSpec", instance.get("extraSpec"));
                if(JSONObject.parseObject((String) instance.get("extraSpec")).get("existFlag") == null){
                    try{
                        obj.put("detail", detailService.getInstanceDetail(object.getString("relationClass"), (String) instance.get("targetInstanceId")));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                result.add(obj);
            }
        }else{
            List<HashMap> instances = getRelationInstanceByRelationIdAndTargetInstanceId(object.getString("relationId"),object.getString("resId"));
            page = PageUtils.getDataTable(instances);
            for(HashMap instance : instances) {
                JSONObject obj = new JSONObject(instance);
                obj.put("relationInstanceId", instance.get("sourceInstanceId"));
                obj.put("lastModified", instance.get("lastModified"));
                obj.put("extraSpec", instance.get("extraSpec"));
                obj.put("detail", detailService.getInstanceDetail(object.getString("relationClass"), (String) instance.get("sourceInstanceId")));
                result.add(obj);
            }
        }
        page.setRows(result);
        return page;
    }

    public List<String> getExtraSpecBySourceIdAndTargetClassName(String resId, List className, String sourceClassName) {
        HashMap map = new HashMap<>();
        map.put("sourceInstanceId", resId);
        map.put("targetClassName", className);
        map.put("sourceClassName", sourceClassName);
        return relationMapper.getExtraSpecBySourceIdAndTargetClassName(map);
    }

    public List<HashMap> getInstancesByRelationId(List relationId) {
        return relationMapper.getInstancesByRelationId(relationId);
    }

    public List<HashMap> getInstancesByRelationIdAndExtraSpec(List<String> relationId, String extraSpecParam) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("relationId", relationId);
        map.put("extraSpecParam", extraSpecParam);
        return relationMapper.getInstancesByRelationIdAndExtraSpec(map);
    }
}
