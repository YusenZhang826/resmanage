package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.ObjectStorage;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.ObjectStorageService;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/v1/objectStorage"})
public class ObjectStorageController extends CloudBaseController {
    @Autowired
    ObjectStorageService objectStorageService;

    @GetMapping("/getAllObjectStorage")
    public TableDataInfo getAllInstance(ObjectStorage objectStorage) {
        startPage();
        List<ObjectStorage> instances = objectStorageService.getAllInstance(objectStorage);
        return getDataTable(instances);
    }

    @GetMapping("/addObjectStorage2DB")
    public JsonResult addObjectStorage2DB() {
        try {
            objectStorageService.addObjectStorage2DB();
        } catch (Exception e) {
            return renderError("落库失败");
        }
        return renderSuccess("落库成功");
    }

    @GetMapping("/getObjectStorageByResId")
    public JsonResult getObjectStorageByResId(String resId) {
        try {
            ObjectStorage objectStorage = objectStorageService.getInstanceDetail(resId);
            return renderSuccess(objectStorage);
        } catch (Exception e) {
            return renderError("查询对象存储失败");
        }
    }
}
