package com.clic.ccdbaas.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.LocalOperLog;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.LocalOperLogService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.RedisClientUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/3/15 14:35
 * @email chenjianhua@bmsoft.com.cn
 */
@RestController
@RequestMapping(value = {"/v1/record"})
public class LocalOperLogController extends CloudBaseController {
    @Autowired
    private LocalOperLogService localOperLogService;

    /**
     * 条件分页查询所有的变更记录
     *
     * @param localOperLog
     * @return
     */
    @GetMapping(value = "/allModifyRecord")
    public TableDataInfo allModifyRecord(LocalOperLog localOperLog) {

        startPage();
        List<LocalOperLog> allModifyRecord = localOperLogService.getAllModifyRecord(localOperLog);

        return getDataTable(allModifyRecord);

    }


    /**
     * 条件分页查询所有的变更记录
     *
     * @param localOperLog
     * @return
     */
    @GetMapping(value = "/toOut/allModifyRecord")
    public TableDataInfo getAllModifyRecord(LocalOperLog localOperLog) {
        startPage();
        List<LocalOperLog> allModifyRecord = localOperLogService.getAllModifyRecord(localOperLog);

        return getDataTable(allModifyRecord);

    }


    /**
     * 条件分页查询列表
     *
     * @param className
     * @return
     */
    @GetMapping(value = "/toOut/getResByClassName/{className}")
    public TableDataInfo getAllResInfo(@PathVariable(value = "className") String className, @RequestParam(required = false) String mainIp, @RequestParam(required = false) String resId) {
        startPage();
        List allResRecord = localOperLogService.getResByClassName(className, mainIp, resId);

        return getDataTable(allResRecord);

    }

    @GetMapping(value = "/localOperLog/getStatisticsForWeek")
    public JsonResult getStatisticsForWeek() {
        String resStr = null;
        try {
            resStr = RedisClientUtil.jedis.get("getStatisticsForWeek");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!Strings.isBlank(resStr)) {
            JSONObject objJson = JSON.parseObject(resStr);
            return renderSuccess(objJson);
        }
        Object res = localOperLogService.getStatisticsForWeek();
        //res转json
        String jsonStrRes = JSON.toJSONString(res);
        //2 hours
        RedisClientUtil.jedis.setex("getStatisticsForWeek", 7200, jsonStrRes);
        JSONObject objJson = JSON.parseObject(jsonStrRes);
        return renderSuccess(objJson);
    }
}
