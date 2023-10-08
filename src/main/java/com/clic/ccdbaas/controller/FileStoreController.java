package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.FileStore;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.FileStoreService;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/v1/fileStore"})
public class FileStoreController extends CloudBaseController {
    @Autowired
    private FileStoreService fileStoreService;

    @GetMapping("/getAllFileStore")
    public TableDataInfo getAllFileStore(FileStore fileStore) {
        startPage();
        List<FileStore> fileStores = fileStoreService.getAllFileStore(fileStore);
        return getDataTable(fileStores);
    }


    @GetMapping("getFileStoreByResId")
    public JsonResult getFileStoreByResId(@RequestParam String resId) {
        try {
            JSONObject file = fileStoreService.getInstanceDetail(resId);
            return renderSuccess(file);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("查询详情失败");
        }
    }


    @GetMapping("/addFile2DB")
    public JsonResult addFile2DB() {
        fileStoreService.addFile2DB();
        //fileStoreService.addCloudFile2DB();
        //fileStoreService.addOutFile2OB();
        return new JsonResult(200, null, "文件存储信息已落库");
    }

    @PostMapping("/addSystemLink")
    public JsonResult addSystemLink(@RequestBody JSONArray array) {
        fileStoreService.addSystemLink(array);
        return renderSuccess("URL已落库");
    }

    @GetMapping("/dataClean")
    public JsonResult dataClean() {
        try {
            fileStoreService.dataClean();
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("文件存储数据清洗失败");
        }
        return renderSuccess("文件存储数据清洗完成");
    }

    @GetMapping("/addReserveStorageRelation")
    public JsonResult addReserveStorageRelation() {
        fileStoreService.addReserveStorageRelation();
        return renderSuccess("关联关系已增加");
    }
}
