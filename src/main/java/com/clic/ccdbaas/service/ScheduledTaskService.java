package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.ResourceTypeMapper;
import com.clic.ccdbaas.dao.ScheduledTaskMapper;
import com.clic.ccdbaas.entity.ScheduledTask;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class ScheduledTaskService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduledTaskMapper scheduledTaskMapper;
    @Autowired
    private ResourceTypeMapper resourceTypeMapper;
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);

    //创建单个定时任务
    public void startScheduledTask(Map map) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        String resId = (String) map.get("resId");
        jobDataMap.put("resId", resId);
        jobDataMap.put("resourceTypeId", (String) map.get("resourceTypeId"));
        jobDataMap.put("executeStatement", (String) map.get("executeStatement"));
        jobDataMap.put("dbTableName", resourceTypeMapper.getDBTableNameById((String) map.get("resourceTypeId")));
        jobDataMap.put("reverse", 0 == (Integer) map.get("searchType"));
        jobDataMap.put("periodic", map.get("periodic"));

        // 创建任务实例
        JobDetail jobDetail = JobBuilder.newJob(ScheduledTask.class)
                .usingJobData(jobDataMap)
                .withIdentity(resId)
                .storeDurably(true)
                .build();

        Trigger trigger = null;
        int triggerMechanism = (int) map.get("triggerMechanism");
        if (triggerMechanism == 0) { // 配置变更
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(resId)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 10 * * * ?"))
                    .build();
        } else { // 周期执行
            // 计算定时任务执行周期
            String period = (String) map.get("execPeriod");
            int hours = 0;
            if (period.contains("h")) {
                hours = Integer.parseInt(period.substring(0, period.indexOf("h")));
            }
            if (period.contains("d")) {
                hours = Integer.parseInt(period.substring(0, period.indexOf("d"))) * 24;
            }
            // 创建触发器
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(resId)
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInHours(hours)
                                    .repeatForever()
                    )
                    .build();//执行
        }

        try {
            scheduler.scheduleJob(jobDetail, trigger);
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动从数据库中扫描定时任务并启动
     */
    //暂时关闭，等上线打开
//    @PostConstruct
    @XxlJob("scanConfigAditRules")
    public void addScheduledTaskByOb() throws SchedulerException {
        List<HashMap<String, Object>> tasks = scheduledTaskMapper.getAllRunningScheduledTask();
        for (HashMap map : tasks) {
            map.put("periodic", true);
            startScheduledTask(map);
        }
    }

    /**
     * 关闭单个定时任务
     */
    public void shutdownScheduledTask(String resId) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(resId));
        logger.info("定时任务" + resId + "已关闭");
    }

    //恢复单个定时任务
    public void resumeScheduledTask(String resId) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(resId);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        Map config = jobDetail.getJobDataMap();
        config.put("periodic", false);
        scheduler.addJob(jobDetail, true);
        scheduler.resumeTrigger(TriggerKey.triggerKey(resId));
        logger.info("定时任务" + resId + "已重启");
    }

    //删除单个定时任务
    public void deleteScheduledTask(String resId) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(resId));//暂停触发器
        scheduler.unscheduleJob(TriggerKey.triggerKey(resId));//移除触发器
        scheduler.deleteJob(JobKey.jobKey(resId));//删除Job
        logger.info("定时任务" + resId + "已删除");
    }

    public void updateScheduledTask(JSONObject obj) throws SchedulerException {
        deleteScheduledTask(obj.getString("resId"));
        obj.put("periodic", false);
        startScheduledTask(obj);
    }

    public void deleteScheduledTaskByRuleId(String resId) throws SchedulerException {
        scheduledTaskMapper.deleteScheduledTaskByRuleId(resId);
        deleteScheduledTask(resId);
    }

    /**
     * 立即执行
     *
     * @param resId
     * @throws SchedulerException
     */
    public void executeJob(String resId) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(resId);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        JobDataMap config = jobDetail.getJobDataMap();
        config.put("periodic", false);
        scheduler.addJob(jobDetail, true);
        scheduler.triggerJob(jobKey);
    }
}
