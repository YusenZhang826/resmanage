package com.clic.ccdbaas.entity;

import com.clic.ccdbaas.dao.ComplianceRuleMapper;
import com.clic.ccdbaas.dao.GenericMapper;
import com.clic.ccdbaas.dao.ScheduledTaskMapper;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


@DisallowConcurrentExecution
public class ScheduledTask implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    ScheduledTaskMapper scheduledTaskMapper;
    @Autowired
    ComplianceRuleMapper complianceRuleMapper;
    @Autowired
    GenericMapper genericMapper;

    private final int insertBatchSize = 10;
//    private int insertBatchSize = 5000;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("----------当前时间" + sdf.format(new Date()) + "获取定时任务：" + jobExecutionContext.getJobDetail().getJobDataMap().get("resId") + "-----------");

        JobDataMap paramsMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String ruleId = paramsMap.getString("resId");
        String resourceTypeId = paramsMap.getString("resourceTypeId");
        String sqlStr = paramsMap.getString("executeStatement");
        String dbTableName = paramsMap.getString("dbTableName");
        boolean reverse = paramsMap.getBoolean("reverse");
        boolean periodic = paramsMap.getBoolean("periodic");

        if (periodic) {
            Map ruleStatus = complianceRuleMapper.selectRuleLockedInfo(ruleId);
            String period = (String) ruleStatus.get("execPeriod");
            if (!StringUtils.isEmpty(period) && ruleStatus.get("lastExecTime") != null) {
                Date lastExecTime = (Date) ruleStatus.get("lastExecTime");
                long hours = 0;
                if (period.contains("h")) {
                    hours = Long.parseLong(period.substring(0, period.indexOf("h")));
                }
                if (period.contains("d")) {
                    hours = Long.parseLong(period.substring(0, period.indexOf("d"))) * 24;
                }

                try {
                    Date currentTime = sdf.parse(sdf.format(new Date()));
                    long diff = currentTime.getTime() - lastExecTime.getTime();
                    TimeUnit time = TimeUnit.HOURS;
                    long sub = time.convert(diff, TimeUnit.MILLISECONDS);
                    if (sub < hours) {
                        logger.info("----------当前时间" + sdf.format(new Date()) + "释放定时任务：" + jobExecutionContext.getJobDetail().getJobDataMap().get("resId") + "-----------");
                        return;
                    }
                } catch (ParseException e) {
                    logger.info("----------当前时间" + sdf.format(new Date()) + "释放定时任务：" + jobExecutionContext.getJobDetail().getJobDataMap().get("resId") + "-----------");
                    throw new RuntimeException(e);
                }
            }
        }

        Integer runningStatus = complianceRuleMapper.selectRunningStatus(ruleId);
        // 防止并发冲突
        if (runningStatus != null && runningStatus == 0) {
            logger.info("----------当前时间" + sdf.format(new Date()) + "释放定时任务：" + jobExecutionContext.getJobDetail().getJobDataMap().get("resId") + "-----------");
            return;
        }

        logger.info("----------当前时间" + sdf.format(new Date()) + "开始执行定时任务：" + jobExecutionContext.getJobDetail().getJobDataMap().get("resId") + "-----------");
        // 修改配置审计中的执行状态字段值为执行中
        Map updateRuleInfo = new HashMap();
        updateRuleInfo.put("resId", ruleId);
        updateRuleInfo.put("runningStatus", 0);
        int affectedRows = complianceRuleMapper.updateExecResult(updateRuleInfo);
        // 防止并发冲突
        if (affectedRows == 0) {
            logger.info("----------当前时间" + sdf.format(new Date()) + "释放定时任务：" + jobExecutionContext.getJobDetail().getJobDataMap().get("resId") + "-----------");
            return;
        }

        List<String> executeResult = new ArrayList<>();
        try {
            executeResult = genericMapper.selectPublicItemPKList(sqlStr);
        } catch (Exception e) {
            e.printStackTrace();
            updateRuleInfo.put("runningStatus", 1);
            updateRuleInfo.put("lastExecResult", 0);
            updateRuleInfo.put("lastExecTime", new Date());
            complianceRuleMapper.updateExecResult(updateRuleInfo);
        }

        int incomplianceSize = executeResult.size();
        List<Map> resultIdsList = new ArrayList<>();
        for (int i = 0; i < incomplianceSize; i++) {
            String resId = executeResult.get(i);
            String id = IdUtils.generatedUUID();

            Map<String, String> resultIds = new HashMap<>();
            resultIds.put("id", id);
            resultIds.put("resId", resId);
            resultIdsList.add(resultIds);
        }

        // 删除过期数据
        scheduledTaskMapper.deleteExpireData(ruleId);
        // 批量更新结果数据
        int loop = (int) Math.ceil(incomplianceSize * NumberUtils.DOUBLE_ONE / insertBatchSize);
        for (int i = 0; i < loop; i++) {
            int endSize = (i + 1) * insertBatchSize;
            endSize = Math.min(endSize, incomplianceSize);
            List<Map> tmpListInfo = resultIdsList.subList(i * insertBatchSize, endSize);
            scheduledTaskMapper.batchInsertResult(ruleId, resourceTypeId, tmpListInfo);
        }

        // 更新配置审计中的查询结果信息
        String primaryKey = "resId";
        int total = scheduledTaskMapper.getResourceTotal(dbTableName, primaryKey);
        int complianceSize = total - incomplianceSize;

        updateRuleInfo.put("runningStatus", 1);
        updateRuleInfo.put("lastExecResult", 1);
        updateRuleInfo.put("total", total);
        updateRuleInfo.put("incompliance", reverse ? complianceSize : incomplianceSize);
        updateRuleInfo.put("compliance", reverse ? incomplianceSize : complianceSize);
        updateRuleInfo.put("lastExecTime", new Date());
        complianceRuleMapper.updateExecResult(updateRuleInfo);
        logger.info("----------" + sdf.format(new Date()) + "定时任务执行完成：" + jobExecutionContext.getJobDetail().getJobDataMap().get("resId") + "-----------");
    }
}
