package com.clic.ccdbaas.service;

import com.clic.ccdbaas.dao.LocalOperLogMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.LocalOperLog;
import com.clic.ccdbaas.entity.PhysicalHost;
import com.clic.ccdbaas.entity.PhysicalMachine;
import com.clic.ccdbaas.resp.LocalOperLog.LocalOperLogStaticsResp;
import com.clic.ccdbaas.resp.LocalOperLog.OneDayDetail;
import com.clic.ccdbaas.resp.LocalOperLog.ResTypeData;
import com.clic.ccdbaas.utils.sql.Condition;
import com.clic.ccdbaas.utils.sql.CountByGroupSearch;
import com.clic.ccdbaas.utils.sql.CountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/3/14 10:15
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class LocalOperLogService {
    @Autowired
    private LocalOperLogMapper localOperLogMapper;

    @Autowired
    private CloudVmareService cloudVmareService;
    @Autowired
    private PhysicalHostService physicalHostService;
    @Autowired
    private PhysicalMachineService physicalMachineService;

    @Autowired
    private ResourceSearchService resourceSearchService;

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now(); // 获取当前日期
        LocalTime time = LocalTime.of(0, 0, 0); // 创建一个0点的时间
        ZoneId zone = ZoneId.systemDefault(); // 获取系统默认的时区
        ZonedDateTime zdt = ZonedDateTime.of(localDate, time, zone); // 创建一个ZonedDateTime对象
        Date date = Date.from(zdt.toInstant()); // 转换为一个Date对象
        System.out.println(date); // 打印结果
    }

    public LocalOperLogStaticsResp getStatisticsForWeek() {
        LocalOperLogStaticsResp localOperLogStaticsResp = new LocalOperLogStaticsResp();

        CountByGroupSearch countByGroupSearch = new CountByGroupSearch();
        countByGroupSearch.setClassName("LocalOperLog");
        countByGroupSearch.setSearchedField("res_type");
        countByGroupSearch.setPreConditions(new ArrayList<Condition>() {
                                                {
                                                    add(new Condition() {{
                                                        setConditionType("and");
                                                        setDomainName("status");
                                                        setValues(new ArrayList() {{
                                                            add(0);
                                                        }});
                                                        setSearchType("=");
                                                        setDomainType("Integer");
                                                    }});
                                                    add(new Condition() {{
                                                        setConditionType("and");
                                                        setDomainName("oper_name");
                                                        setValues(new ArrayList() {{
                                                            add("com");
                                                        }});
                                                        setSearchType("startWith");
                                                        setDomainType("String");
                                                    }});
                                                    add(new Condition() {{
                                                        setConditionType("and");
                                                        setDomainName("res_type");
                                                        setValues(new ArrayList() {{
                                                            add("CLOUD_HOST");
                                                        }});
                                                        setSearchType("not in");
                                                        setDomainType("String");
                                                    }});
                                                }
                                            }
        );
        countByGroupSearch.setConditions(new ArrayList<Condition>() {{
            add(new Condition() {{
                setConditionType("and");
                setDomainName("oper_time");
                setSearchType("between");
            }});
        }});

        Map<String, ResTypeData> resTypeMap = new HashMap<>();

        LocalDate todayDate = LocalDate.now(); // 获取当前日期
        LocalTime time = LocalTime.of(0, 0, 0); // 创建一个0点的时间
        ZoneId zone = ZoneId.systemDefault(); // 获取系统默认的时区
        ZonedDateTime todayZdt = ZonedDateTime.of(todayDate, time, zone); // 创建一个ZonedDateTime对象
        ZonedDateTime beginDate = todayZdt.minusDays(7); // 创建一个ZonedDateTime对象
        for (int i = 0; i < 7; ++i) {
            Date date = Date.from(beginDate.toInstant()); // 转换为一个Date对象
            ZonedDateTime finalBeginDate = beginDate;
            countByGroupSearch.getConditions().get(0).setValues(new ArrayList() {{
                add(date);
                add(Date.from(finalBeginDate.plusDays(1).toInstant()));
            }});
            String dateStr = beginDate.toLocalDate().toString();
            localOperLogStaticsResp.getX().add(dateStr);
            List<CountInfo> countInfos = resourceSearchService.countSearchInstance(countByGroupSearch);//一天内不同资源类型的数量
            for (CountInfo countInfo : countInfos) {
                String resType = countInfo.getLabel();
                if (resTypeMap.containsKey(resType)) {
                    ResTypeData resTypeData = resTypeMap.get(resType);
                    resTypeData.getDetail().get(i).setCount(countInfo.getCount());
                } else {
                    ResTypeData resTypeData = new ResTypeData();
                    resTypeData.setResType(resType);
                    List<OneDayDetail> oneDayDetails = new ArrayList<>();
                    for (int j = 0; j < i; ++j) {
                        ZonedDateTime finalBeginDate1 = beginDate;
                        int finalJ = j;
                        int finalI = i;
                        oneDayDetails.add(new OneDayDetail() {{
                            setCount(0);
                            setDate(finalBeginDate1.minusDays(finalI - finalJ).toLocalDate().toString());
                        }});
                    }
                    oneDayDetails.add(new OneDayDetail() {{
                        setCount(countInfo.getCount());
                        setDate(dateStr);
                    }});
                    for (int j = i + 1; j < 7; ++j) {
                        ZonedDateTime finalBeginDate1 = beginDate;
                        int finalJ = j;
                        int finalI = i;
                        oneDayDetails.add(new OneDayDetail() {{
                            setCount(0);
                            setDate(finalBeginDate1.plusDays(finalJ - finalI).toLocalDate().toString());
                        }});
                    }
                    resTypeData.setDetail(oneDayDetails);
                    resTypeMap.put(resType, resTypeData);
                }
            }
            beginDate = beginDate.plusDays(1);
        }
        localOperLogStaticsResp.setData(new ArrayList<>(resTypeMap.values()));
        return localOperLogStaticsResp;
    }

    /**
     * 插入更新日志
     *
     * @param localOperLog
     */
    public void insertOperlog(LocalOperLog localOperLog) {
        localOperLogMapper.insertOperlog(localOperLog);
    }

    /**
     * 获取变更记录
     *
     * @param localOperLog
     * @return
     */
    public List<LocalOperLog> getAllModifyRecord(LocalOperLog localOperLog) {
        //List<LocalOperLog> localOperLogs = localOperLogMapper.selectLogListByParam(localOperLog);
        return localOperLogMapper.selectLogListByParam(localOperLog);
    }

    /**
     * 根据资源名称获取资源列表
     *
     * @param className
     * @return
     */
    public List getResByClassName(String className, String mainIp, String resId) {

        switch (className) {
            case "CLOUD_VM_NOVA":
                return cloudVmareService.getCloudvmList(new CloudVmare(mainIp, resId));
            case "SYS_X86Server":
                return physicalMachineService.getPhysicalList(new PhysicalMachine(mainIp, resId));
            case "CLOUD_HOST":
                return physicalHostService.getAllPhysicalHost(new PhysicalHost(mainIp, resId));
            default:
                return null;
        }
    }
}