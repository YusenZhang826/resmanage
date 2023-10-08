package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.RelationDb;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.RelationDbService;
import com.clic.ccdbaas.service.ResourceSearchService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import com.clic.ccdbaas.utils.sql.CountByGroupSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/relationDb")
public class RelationDbController extends CloudBaseController {

    @Autowired
    RelationDbService relationDbService;

    @Autowired
    ResourceSearchService resourceSearchService;


    @GetMapping("/saveRelationDbInfo")
    public void saveRelationDbInfo() {
        relationDbService.saveAllRelationDb();
    }

    @PostMapping(value = "/advancedSearchByPage")
    public TableDataInfo advancedSearchByPage(@RequestBody AdvancedSearch advancedSearch) {
        advancedSearch.setClassName("RelationDb");
        startPage();
        return getDataTable(resourceSearchService.advancedSearchInstance(advancedSearch));
    }

    @PostMapping(value = "/countByGroupSearch")
    public JsonResult countByGroupSearch(@RequestBody CountByGroupSearch countByGroupSearch) {
        return renderSuccess(resourceSearchService.countSearchInstance(countByGroupSearch));
    }


    @GetMapping(value = "/getRelationDbByPage")
    public TableDataInfo getClusterByPage(RelationDb relationDb) {
        startPage();
        List<RelationDb> allRelationDbInfo = relationDbService.getAllRelationDb(relationDb);
        return getDataTable(allRelationDbInfo);

    }


}
