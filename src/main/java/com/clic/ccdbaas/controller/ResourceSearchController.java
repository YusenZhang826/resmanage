package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.ResourceSearchService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import com.clic.ccdbaas.utils.sql.CountByGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.clic.ccdbaas.utils.PageUtils.getDataTable;
import static com.clic.ccdbaas.utils.PageUtils.startPage;

@RestController
@RequestMapping("/v1/search")
public class ResourceSearchController extends BaseController {
    @Autowired
    ResourceSearchService resourceSearchService;

    @PostMapping(value = "/advancedSearchByPage")
    public TableDataInfo advancedSearchByPage(@RequestBody AdvancedSearch advancedSearch) {
        startPage();
        return getDataTable(resourceSearchService.advancedSearchInstance(advancedSearch));
    }

    @PostMapping(value = "/advancedCount")
    public JsonResult advancedCount(@RequestBody AdvancedSearch advancedSearch) {
        return renderSuccess(resourceSearchService.advancedCountInstance(advancedSearch));
    }

    @PostMapping(value = "/countByGroupSearch")
    public JsonResult countByGroupSearch(@RequestBody CountByGroupSearch countByGroupSearch) {
        return renderSuccess(resourceSearchService.countSearchInstance(countByGroupSearch));
    }

    @GetMapping(value = "/getClassInfo")
    public JsonResult getClassInfo(String className) {
        return renderClassInfo(resourceSearchService.getResourceClassInfo(className));
    }

    @PostMapping(value = "/advancedExport")
    public void advancedExport(HttpServletResponse httpServletResponse, @RequestBody AdvancedSearch advancedSearch) {
        resourceSearchService.advancedExport(httpServletResponse, advancedSearch);
    }

    @PostMapping(value = "/advancedExportByEasyExcel")
    public void advancedExportByEasyExcel(HttpServletResponse httpServletResponse, @RequestBody AdvancedSearch advancedSearch) {
        resourceSearchService.advancedExportByEasyExcel(httpServletResponse, advancedSearch);
    }

}
