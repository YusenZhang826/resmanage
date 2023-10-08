package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.ComplianceRule;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.ComplianceRuleService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/configaudit")
public class ConfigAuditController extends CloudBaseController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigAuditController.class);
    @Autowired
    private ComplianceRuleService complianceRuleService;

    /**
     * 获取单条ComplianceRule信息
     */
    @GetMapping("/getComplianceRuleByResId")
    public JsonResult getComplianceRuleByResId(@RequestParam String resId) {
        try {
            Map complianceRule = complianceRuleService.getComplianceRuleByResId(resId);
            logger.info("成功获取 1 条数据");
            return renderSuccess(complianceRule);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
    }

    /**
     * 分页获取ComplianceRule列表信息
     */
    @GetMapping("/getAllComplianceRules")
    public TableDataInfo getAllComplianceRulesByPage(ComplianceRule searchInfo) {
        startPage();
        List<Map> complianceRuleList = complianceRuleService.getAllComplianceRules(searchInfo);
        logger.info("成功查询分页数据");
        return getDataTable(complianceRuleList);
    }

    /**
     * 获取全部配置审计规则列表，未分页
     *
     * @param searchInfo
     * @return
     */
    @GetMapping("/getNoPagedComRules")
    public TableDataInfo getAllComplianceRules(ComplianceRule searchInfo) {
        List<Map> complianceRuleList = complianceRuleService.getAllComplianceRules(searchInfo);
        logger.info("成功查询全量数据（未分页）");
        return getDataTable(complianceRuleList);
    }

    /**
     * 执行立即查询
     */
    @PostMapping("/executeSelectByResId")
    public JsonResult executeSelectByResId(@RequestBody JSONArray resIds) {
        try {
            complianceRuleService.executeSelectByResId(resIds);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }

        return renderSuccess("success!");
    }

    /**
     * 获取审计规则详情
     *
     * @param request
     * @return
     */
    @GetMapping("/result")
    public TableDataInfo getDetailById(HttpServletRequest request) {
        List<Map> relateResult = new ArrayList<>();
        try {
            relateResult = complianceRuleService.getTaskResult(request.getParameterMap());
            logger.info("成功执行结果查询");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getDataTable(relateResult);
    }


    /**
     * 导出结果集
     *
     * @param response
     * @param searchJSON
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody JSONObject searchJSON) {
        try {
            String ruleId = searchJSON.getString("ruleId");
            Map entityInfo = complianceRuleService.getResourceEntity(ruleId);
            String className = (String) entityInfo.get("path");
            String name = (String) entityInfo.get("name");

            List<Map> list = complianceRuleService.exportTaskResult(searchJSON);
            Class clazz = complianceRuleService.getResourceEntityClass(className);
            List exportList = JSON.parseArray(JSON.toJSONString(list), clazz);
            ExcelUtil util = new ExcelUtil<>(clazz);
            util.exportExcel(response, exportList, name);
            logger.info("成功导出数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行传递参数查询
     */
    //@PostMapping("executeSelectByResId")
    //@ResponseBody
    public JSONArray executeSelectByParam(@RequestParam String resId) {
        JSONArray objList = complianceRuleService.executeSelectByParam(resId);
        logger.info("成功执行查询 1 次");
        return objList;
    }

    /**
     * 添加配置审计规则
     */
    @PostMapping("/addComplianceRule")
    public JsonResult addComplianceRule(@RequestBody JSONObject rule) {
        int code = 0;
        try {
            code = complianceRuleService.addComplianceRule(rule);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("添加配置审计规则失败");
        }

        if (code == 1) return renderError("操作失败！规则名称已存在。");
        return renderSuccess();
    }

    /**
     * 修改配置审计规则
     */
    @PostMapping("/updateComplianceRule")
    public JsonResult updateComplianceRule(@RequestBody JSONObject rule) {
        int code = 0;
        try {
            code = complianceRuleService.updateComplianceRule(rule);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("修改配置审计规则失败");
        }

        if (code == 1) return renderError("操作失败！规则名称已存在。");
        return renderSuccess();
    }

    /**
     * 删除配置审计规则
     */
    @PostMapping("/deleteComplianceRule")
    public JsonResult deleteComplianceRule(@RequestBody JSONObject resIdJSON) {
        try {
            complianceRuleService.deleteComplianceRule(resIdJSON.getString("resId"));
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("删除配置审计规则失败");
        }
        return renderSuccess();
    }

    /**
     * 停用配置审计规则
     */
    @PostMapping("/pauseComplianceRule")
    public JsonResult pauseComplianceRule(@RequestBody JSONObject jsonObj) {
        try {
            String resId = jsonObj.getString("resId");
            String updateUser = jsonObj.getString("updateUser");
            complianceRuleService.pauseComplianceRule(resId, updateUser);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("停用配置审计规则失败");
        }
        return renderSuccess();
    }

    /**
     * 启用配置审计规则
     */
    @PostMapping("/resumeComplianceRule")
    public JsonResult resumeComplianceRule(@RequestBody JSONObject jsonObj) {
        try {
            String resId = jsonObj.getString("resId");
            String updateUser = jsonObj.getString("updateUser");
            complianceRuleService.resumeComplianceRule(resId, updateUser);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("启用配置审计规则失败");
        }
        return renderSuccess();
    }

    /**
     * 新增规则分类
     */
    @PostMapping("/addComplianceRuleClass")
    public JsonResult addComplianceRuleClass(@RequestBody JSONObject ruleClass) {
        try {
            if (complianceRuleService.addComplianceRuleClass(ruleClass)) {
                return renderSuccess();
            } else {
                return renderError("添加配置审计规则分类失败，该分类已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("添加配置审计规则分类失败");
        }
    }

    /**
     * 修改规则分类
     */
    @PostMapping("/updateComplianceRuleClass")
    public JsonResult updateComplianceRuleClass(@RequestBody JSONObject ruleClass) {
        try {
            if (complianceRuleService.updateComplianceRuleClass(ruleClass)) {
                return renderSuccess();
            } else {
                return renderError("修改配置审计规则分类失败，修改后的分类名称已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("修改配置审计规则分类失败");
        }
    }

    /**
     * 获取全部规则分类
     */
    @GetMapping("/getAllComplianceRuleClass")
    public TableDataInfo getAllComplianceRuleClass(@RequestParam(value = "pageNo", required = false) String pageNum,
                                                   @RequestParam(value = "pageSize", required = false) String pageSize) {
        List<HashMap> classes = complianceRuleService.getAllComplianceRuleClass(pageNum, pageSize);
        return getDataTable(classes);
    }

    /**
     * 删除一条规则分类
     */
    @PostMapping("/deleteComplianceRuleClass")
    public JsonResult deleteComplianceRuleClass(@RequestParam String id) {
        try {
            complianceRuleService.deleteComplianceRuleClass(id);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("删除配置审计规则分类失败");
        }
        return renderSuccess("删除配置审计规则分类成功");
    }

    /**
     * 获取检查范围的资源列表数据
     *
     * @return
     */
    @GetMapping("/resourceTypeList")
    public JsonResult getResourceTypeList() {
        return renderSuccess(complianceRuleService.getResourceTypeList());
    }

    /**
     * 获取结果集表头属性字段
     *
     * @return
     */
    @GetMapping("/headerAttributes")
    public JsonResult getResultTableHeader(@RequestParam String resId) {
        List<Map> header = new ArrayList<>();
        try {
            header = complianceRuleService.getTableHeader(resId);
        } catch (Exception e) {
            renderError(e.getMessage());
        }
        return renderSuccess(header);
    }

    /**
     * 获取指定资源类型的属性字段和关联关系描述数据
     *
     * @return
     */
    @PostMapping("/resourceAttributes")
    public JsonResult getResourceAttributes(@RequestBody JSONObject obj) {
        List[] info = new ArrayList[2];
        try {
            String resourceId = obj.getString("resourceId");
            info[0] = complianceRuleService.getResourceAttributes(resourceId);
            info[1] = complianceRuleService.getRelationsByResourceId(resourceId);
        } catch (Exception e) {
            renderError(e.getMessage());
        }
        return renderSuccess(info);
    }

    /**
     * 获取枚举类型字段取值范围
     *
     * @param requestBody
     * @return
     */
    @PostMapping("/groupSet")
    public JsonResult getGroupSet(@RequestBody JSONObject requestBody) {
        String resourceId = requestBody.getString("resourceId");
        String name = requestBody.getString("name");
        if (StringUtils.isNotEmpty(resourceId) && StringUtils.isNotEmpty(name)) {
            try {
                return renderSuccess(complianceRuleService.getGroupBySet(resourceId, name));
            } catch (Exception e) {
                renderError(e.getMessage());
            }
        }
        return renderError("参数传递错误！");
    }

    /**
     * 存储资源关联关系到数据库
     *
     * @param classNames
     * @return
     */
    @PostMapping("/storeRelations")
    public JsonResult storeOCRelation(@RequestBody JSONArray classNames) {
        try {
            complianceRuleService.loadRelationData2DB(classNames);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("Success!");
    }

    /**
     * 资源类型数据落库
     *
     * @return
     */
    @GetMapping("/storeResourceType")
    public JsonResult storeResourceType() {
        try {
            complianceRuleService.loadResourceTypeData2DB();
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("Success!");
    }

    /**
     * 统计不同状态的规则数量
     *
     * @return
     */
    @PostMapping("/countStatus")
    public JsonResult countRuleStatus(@RequestBody JSONObject jsobObj) {
        return renderSuccess(complianceRuleService.countRuleStatus(jsobObj));
    }

    /**
     * 配置选择不合规资源统计处的配置审计规则
     *
     * @param resIdArr
     * @return
     */
    @PostMapping("/configRules")
    public JsonResult configVisible(@RequestBody JSONArray resIdArr) {
        complianceRuleService.configSelectedItem(resIdArr);
        return renderSuccess();
    }

    /**
     * 存储资源关联关系到数据库
     *
     * @param relationObj
     * @return
     */
    @PostMapping("/storeRelationInstance")
    public JsonResult storeRelationInstance(@RequestBody JSONObject relationObj) {
        complianceRuleService.storeRelationInstance2DB(relationObj);
        return renderSuccess();
    }
}
