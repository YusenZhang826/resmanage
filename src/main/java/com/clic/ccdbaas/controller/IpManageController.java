package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.IpManage;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.IpManageService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = {"/v1/ipManage"})
public class IpManageController extends CloudBaseController {
    @Autowired
    IpManageService ipManageService;

    @GetMapping("/addIpManage2DB")
    public JsonResult addIpManage2DB() {
        try {
            ipManageService.generateIpFromVlan();
            //ipManageService.addIpInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("落库失败");
        }
        return renderSuccess("落库成功");
    }

    @GetMapping("/getAllIp")
    public TableDataInfo getAllInstance(IpManage ipManage) {
        startPage();
        List<HashMap> instances = ipManageService.getAllInstance(ipManage);
        return getDataTable(instances);
    }

    @GetMapping("/getIpByResId")
    public JsonResult getIpByResId(String resId) {
        HashMap ipManage = new HashMap<>();
        try {
            ipManage = ipManageService.getInstanceDetail(resId);
        } catch (Exception e) {
            return renderError("查询IP详情失败");
        }
        return renderSuccess(ipManage);
    }

    /**
     * 添加单条IP
     */
    @PostMapping(value = "/addIp")
    public JsonResult addIpManage(@RequestBody JSONObject obj) {
        try {
            IpManage ipManage = JSONObject.toJavaObject(obj, IpManage.class);
            ipManageService.addIpManage(ipManage);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("添加IP成功！");
    }

    //通过excel导入新增IP
    @CrossOrigin
    @PostMapping(value = "/addIpManageByExcel")
    public JsonResult addNetworkAreaByImport(@RequestPart("file") MultipartFile file) {
        BaseReadByLineListener<IpManage> readListener = new BaseReadByLineListener<IpManage>() {
            @Override
            public void process(IpManage ipManage) {
                ipManageService.addIpManageByExcel(ipManage);
            }
        };
        return renderUploadExcelFileResult(file, readListener, IpManage.class, 0, 1);
    }

    /**
     * 批量删除多条IP
     */
    @PostMapping(value = "/deleteIps")
    public JsonResult deleteIps(@RequestBody JSONObject obj) {
        try {
            JSONArray ips = obj.getJSONArray("resId");
            ipManageService.deleteIps(ips);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("删除IP成功！");
    }

    /**
     * 更新单条IP
     */
    @PostMapping(value = "/updateIp")
    public JsonResult updateIp(@RequestBody JSONObject obj) {
        try {
            ipManageService.updateIp(obj);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("更新IP成功！");
    }

    //excel导出
    @RequestMapping(value = "/exportIpManage")
    public void exportIpManage(HttpServletResponse response, IpManage ipManage) {
        List<IpManage> list = ipManageService.getAllIp(ipManage);
        ExcelUtil<IpManage> util = new ExcelUtil<IpManage>(IpManage.class);
        util.exportExcel(response, list, "IP管理");
    }

    @GetMapping(value = "/updateDhcp")
    public JsonResult updateDhcp() {
        try {
            ipManageService.updateDhcp();
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("更新DHCP等特殊IP出错");
        }
        return renderSuccess("更新DHCP等特殊IP成功");
    }

    @GetMapping(value = "/updateBelongProduct")
    public JsonResult updateBelongProduct() {
        try {
            ipManageService.updateBelongProduct();
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("更新belongProduc字段成功！");

    }

    @GetMapping(value = "/getSortCount")
    public JsonResult getSortCount() {
        JSONObject object = new JSONObject();
        try {
            object = ipManageService.getSortCount();
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess(object);

    }

}
