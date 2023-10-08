package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.ContractDetail;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.ContractDetailService;
import com.clic.ccdbaas.service.ResourceSearchService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = {"/v1/contractDetail"})
public class ContractDetailController extends CloudBaseController {
    @Autowired
    ContractDetailService contractDetailService;
    @Autowired
    ResourceSearchService resourceSearchService;

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<ContractDetail> util = new ExcelUtil<ContractDetail>(ContractDetail.class);
        util.importTemplateExcel(response, "sheet1");
    }

    @PostMapping("/importData")
    public JsonResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<ContractDetail> util = new ExcelUtil<ContractDetail>(ContractDetail.class);
        List<ContractDetail> contractDetailList = util.importExcel(file.getInputStream(),"sheet1");
        String message = contractDetailService.importContractDetail(contractDetailList, true);
        return renderSuccess(message);
    }


    @PostMapping("/addContractDetail")
    public JsonResult addReserveDisk(@RequestBody ContractDetail contractDetail) {
        try {
            contractDetailService.addContractDetail(contractDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("添加contract失败");
        }
        return renderSuccess();
    }

    @PostMapping(value = "/deleteContractDetailByIds")
    public JsonResult deleteContractDetailByIds(@RequestBody List<String> ids) {
        contractDetailService.deleteContractDetailByIds(ids);
        return renderSuccess();
    }

    @PostMapping(value = "/updateContractDetail")
    public JsonResult updateContractDetailById(@RequestBody ContractDetail contractDetail) {
        contractDetailService.updateContractDetailById(contractDetail);
        return renderSuccess();
    }

    @PostMapping(value = "/updateReserveStorageByIds")
    public JsonResult updateReserveStorageByIds(@RequestBody List<ContractDetail> contractDetailList) {
        contractDetailService.updateContractDetailByIds(contractDetailList);
        return renderSuccess();
    }

    @GetMapping(value = "/getContractDetailById")
    public JsonResult getContractDetailById(String id) {
        ContractDetail contractDetail = contractDetailService.getContractDetailById(id);
        return renderSuccess(contractDetail);
    }

    @PostMapping(value = "/advancedSearchByPage")
    public TableDataInfo advancedSearchByPage(@RequestBody AdvancedSearch advancedSearch) {
        advancedSearch.setClassName("ContractDetail");
        startPage();
        return getDataTable(resourceSearchService.advancedSearchInstance(advancedSearch));
    }

    @PostMapping(value = "/advanceExport")
    public void advancedExport(HttpServletResponse httpServletResponse, @RequestBody AdvancedSearch advancedSearch) {
        advancedSearch.setClassName("ContractDetail");
        resourceSearchService.advancedExport(httpServletResponse, advancedSearch);
    }


}
