package com.clic.ccdbaas.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
@Component
public class exportService {
    @Autowired
    private OcService ocService;

    @Value("${oc.cmdb.instances.url}")
    private String Url;

    @Value("${nginx.cmdb.location}")
    private String cmdbLocation;

    @Value("${oc.cmdb.server}")
    private String physicalList;

    public JSONArray getPhysical(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams,physicalList);
    }


    public JSONArray getAllPhysical() {
        return ocService.getAllInstanceInfo(physicalList);
    }

    /**
     * 导出物理服务器
     */
    @SneakyThrows
    public JSONObject exportCloudVmare(JSONObject requestParams){
        JSONArray vmjs = new JSONArray();
        if(!requestParams.isEmpty()){
            vmjs = getPhysical(requestParams);
        }
        else{
            vmjs = getAllPhysical();
        }
        List<PhysicalService> vmList = JSONObject.parseArray(vmjs.toJSONString(), PhysicalService.class);
        String nowTime = Long.toString(System.currentTimeMillis());
        String fileName = nowTime + ".xlsx";
        File filePath = new File(cmdbLocation + physicalList);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        String path = filePath+"\\" + fileName;
        String url = Url + physicalList + '/' + fileName;
        EasyExcel.write(path,PhysicalService.class).sheet("物理服务器列表").doWrite(vmList);
        JSONObject message = new JSONObject();
        message.put("url", url);
        return message;
    }
}
