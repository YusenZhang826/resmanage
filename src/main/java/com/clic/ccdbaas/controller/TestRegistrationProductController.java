package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.entity.TestRegistrationProduct;
import com.clic.ccdbaas.service.TestRegistrationProductService;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/v1/testRegistrationProduct"})
public class TestRegistrationProductController extends BaseController {
    @Autowired
    private TestRegistrationProductService testRegistrationProductService;

    @GetMapping("/addFromRegistrationProduct")
    public JsonResult addIpManage2DB() {
        try {
            testRegistrationProductService.addFromRegistrationProduct();
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("从生产注册产品导入数据失败");
        }
        return renderSuccess("从生产注册产品导入数据成功");
    }

    @GetMapping("/getTestRegistrationProduct")
    public JSONArray getAllRegistrationProduct(TestRegistrationProduct testRegistrationProduct) {
        List<TestRegistrationProduct> products = testRegistrationProductService.getAllRegistrationProduct(testRegistrationProduct);
        return JSONArray.parseArray(JSON.toJSONString(products));
    }

    @PostMapping("/addTestRegistrationProduct")
    public JsonResult addTestRegistrationProduct(@RequestBody JSONArray array) {
        try {
            testRegistrationProductService.addTestRegistrationProduct(array);
            return renderSuccess("添加归属产品成功");
        } catch (Exception e) {
            return renderError("添加归属产品失败");
        }
    }

    @GetMapping("/auth")
    public JsonResult auth() {
        try {
            testRegistrationProductService.addProductAuthorization();
        } catch (Exception e) {
            return renderError("授权失败");
        }
        return renderSuccess("授权成功");
    }

    @GetMapping("/getAccountByIp")
    public JsonResult getAccountByIp(@RequestParam String ip, @RequestParam Boolean adminFlag) {
        try {
            return renderSuccess(testRegistrationProductService.getAccountByIp(ip, adminFlag));
        } catch (Exception e) {
            return renderError("获取主机账户失败");
        }
    }

    @GetMapping("/getAccountPassword")
    public JsonResult getAccountPassword(@RequestParam int hostId, @RequestParam String accountId) {
        try {
            return renderSuccess(testRegistrationProductService.getAccountPassword(hostId, accountId));
        } catch (Exception e) {
            return renderError("获取账户密码失败");
        }
    }
}
