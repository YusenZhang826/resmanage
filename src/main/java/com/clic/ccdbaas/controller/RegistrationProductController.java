package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.RegistrationProduct;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.CloudVmareService;
import com.clic.ccdbaas.service.RegistrationProductService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.clic.ccdbaas.utils.PageUtils.getDataTable;
import static com.clic.ccdbaas.utils.PageUtils.startPage;

@RestController
@RequestMapping(value = {"/v1/registrationProduct"})
public class RegistrationProductController extends BaseController{

    @Autowired
    RegistrationProductService registrationProductService;
    @Autowired
    CloudVmareService cloudVmareService;
/*    //查询注册产品列表
    @CrossOrigin
    @PostMapping(value = "/getAllProduct")
    public JsonResult getAllProduct(@RequestBody JSONObject requestParams) {
        if (!requestParams.isEmpty()) {
            return renderSuccess(registrationProductService.getJson(requestParams));
        }
        return renderSuccess(registrationProductService.getJson());
    }*/

    @CrossOrigin
    @PostMapping(value = "/getAllProduct")
    public JsonResult getAllProduct( RegistrationProduct registrationProduct) {
        List<RegistrationProduct> productList = registrationProductService.getProductList(registrationProduct);

        return renderSuccess(productList);
    }

    //查询单个设备信息
    @CrossOrigin
    @GetMapping(value = "/getOne")
    public JsonResult getDeviceMessage(@RequestParam String instanceId){
        return renderSuccess(registrationProductService.getMessage(instanceId));
    }

    //应用注册产品导出
    @CrossOrigin
    @RequestMapping(value = "/exportProductList")
    public JsonResult exportProduct(@RequestBody JSONObject requestParams) {
        return renderSuccess(registrationProductService.exportProduct(requestParams));
    }

/*    //应用云干系人列表
    @CrossOrigin
    @PostMapping(value = "/getSingleProductUserInfo")
    public JsonResult getProductStakeholder(@RequestBody JSONObject requestParams) {
        String moki = registrationProductService.Moki();
        JSONArray tmpJsonInfo =JSONArray.parseArray(moki);
        return renderSuccess(tmpJsonInfo);
    }*/


    @PostMapping(value = "/getSingleProductUserInfo")
    public JsonResult getSingleProductUserInfo(@RequestBody JSONObject paramJson){
        List resJson = registrationProductService.getProductUserInfo(paramJson);
        return renderSuccess(resJson);
    }

    /**
     * 应用云产品信息落库
     */
    @GetMapping(value = "/updateACSProductInfo")
    public JsonResult updateACSProductInfo(){
        return renderSuccess(registrationProductService.updateACSProductInfo());
    }



    @GetMapping(value = "/getProductList")
    public TableDataInfo getProductList(RegistrationProduct registrationProduct){
        startPage();
        List<RegistrationProduct> productList = registrationProductService.getProductList(registrationProduct);
        return getDataTable(productList);

    }

    @GetMapping(value = "/getProductDetail")
    public JsonResult getProductDetail(@RequestParam String productToken){
        JSONObject productDetail = registrationProductService.getProductDetail(productToken);
        return  renderSuccess(productDetail);

    }

    @GetMapping(value = "/getProductCountInfoByStatus")
    public JsonResult getProductCountInfoByStatus(RegistrationProduct registrationProduct){
        List<Map> productCount = registrationProductService.getProductCountInfo(registrationProduct);
        return  renderSuccess(productCount);

    }


    @PostMapping("/export")
    public void export(HttpServletResponse response,@RequestBody RegistrationProduct registrationProduct)
    {
        List<RegistrationProduct> productList = registrationProductService.getProductList(registrationProduct);
        ExcelUtil<RegistrationProduct> util = new ExcelUtil<RegistrationProduct>(RegistrationProduct.class);
        util.exportExcel(response, productList, "产品信息列表");
    }
}
