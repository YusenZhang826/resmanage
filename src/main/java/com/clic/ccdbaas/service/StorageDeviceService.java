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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2022/11/17 13:52
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class StorageDeviceService {

    private static final Logger logger = LoggerFactory.getLogger(StorageDeviceService.class);

    @Autowired
    private OcService ocService;
    @Value("${oc.cmdb.storagedevice}")
    private String storageDeviceClass;
    @Value("${nginx.location.StorageDevice}")
    private String StorageDeviceLocation;
    @Value("${nginx.url}")
    private String nginxUrl;

    /**
     * 删除设备信息
     *
     * @param paramJson
     */
    public void delelteDevice(JSONArray paramJson) {
        logger.info("删除设备请求报文为:" + paramJson.toString());
        String instanceId = "";
        JSONObject jsonObject = null;
        for (int i = 0; i < paramJson.size(); i++) {
            jsonObject = (JSONObject) JSONObject.toJSON(paramJson.get(i));
            instanceId = jsonObject.getString("instanceId");
            ocService.deleteOcClassInstances(storageDeviceClass, instanceId);
            logger.info("删除资源成功,设备id为" + instanceId);
        }
    }

    public JSONArray getJson(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, storageDeviceClass);
    }

    public JSONArray getJson() {
        return ocService.getAllInstanceInfo(storageDeviceClass);
    }

    public JSONObject getMessage(String instanceId) {
        logger.info("查询设备详情id为" + instanceId);
        JSONObject allInstanceInfo = ocService.getAllInstanceInfo(storageDeviceClass, instanceId);
        logger.info("设备详情：" + allInstanceInfo);
        return allInstanceInfo;
    }

    public void exportDevice(HttpServletResponse response) {
        JSONArray request = null;
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            ocService.setExcelRespProp(response, "库存设备列表");
            request = getJson();
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

    public JSONArray importDevice(MultipartFile file) {
        List<StorageDevice> StorageDeviceList = null;
        JSONArray json = null;
        try {
            List<Map> mapList = new ArrayList<>();
            StorageDeviceList = EasyExcel.read(file.getInputStream())
                    .head(StorageDevice.class)
                    .sheet()
                    .doReadSync();
            for (StorageDevice storageDevice : StorageDeviceList) {
                Map map = JSON.parseObject(JSONObject.toJSONString(storageDevice), Map.class);
                map.remove("id");
                String nativeId = UUID.randomUUID().toString();
                map.put("nativeId", nativeId);
                mapList.add(map);
            }
            json = JSONArray.parseArray(JSON.toJSONString(mapList));
            logger.info("Excel导入库存设备信息为" + json.toString());
            String result = ocService.addInstances(storageDeviceClass, json.toString());
            if (result != null) {
                logger.info("库存设备信息导入成功");
            } else {
                logger.info("库存设备信息导入失败");
            }
            return JSON.parseObject(result).getJSONArray("idList");
        } catch (Exception e) {
            logger.info("库存设备信息导入失败");
        } finally {
            StorageDeviceList = null;
        }
        return null;
    }

    public JSONObject insertDevice(JSONObject requestParams) {
        List list = new ArrayList();
        list.add(requestParams.toString());
        String result = ocService.addInstances(storageDeviceClass, list.toString());
        return JSON.parseObject(result);
    }

    public JSONObject updateDevice(JSONObject requestParams) {

        return null;
    }
}
