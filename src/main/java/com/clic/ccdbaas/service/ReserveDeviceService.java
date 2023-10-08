package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.dao.*;
import com.clic.ccdbaas.entity.*;
import com.clic.ccdbaas.entity.example.ReserveDiskExample;
import com.clic.ccdbaas.entity.example.ReserveSanExample;
import com.clic.ccdbaas.entity.example.ReserveServerExample;
import com.clic.ccdbaas.entity.example.ReserveStorageExample;
import com.clic.ccdbaas.enums.ResourceStatus;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.sql.FuzzySearch;
import com.clic.ccdbaas.utils.sql.SqlUtil;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReserveDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(ReserveDeviceService.class);
    @Autowired
    ReserveJumperMapper reserveJumperMapper;
    @Autowired
    ReserveStorageMapper reserveStorageMapper;
    @Autowired
    ReserveSanMapper reserveSanMapper;
    @Autowired
    ReserveDiskMapper reserveDiskMapper;
    @Autowired
    ReserveServerMapper reserveServerMapper;
    @Autowired
    GenericMapper genericMapper;
    @Autowired
    ReserveDeviceMapper reserveDeviceMapper;
    @Autowired
    PhysicalTapeMapper physicalTapeMapper;
    @Autowired
    VirtualTapeMapper virtualTapeMapper;
    @Autowired
    ReserveSafeMapper reserveSafeMapper;
    @Autowired
    ReserveNetworkMapper reserveNetworkMapper;
    @Autowired
    OcService ocService;

    @Value("${oc.cmdb.device}")
    private String device;

    /**
     * 查询全部的库存设备
     *
     * @return
     */
    public JSONArray getReserveDevice() {
        return ocService.getAllInstanceInfo(device);
    }

    /**
     * 按照一定过滤条件对库存设备进行查询
     *
     * @param requestParams 查询参数
     * @return
     */
    public JSONArray getReserveDevice(JSONObject requestParams) {
        String message = "";
        String condition = "";
        JSONObject jsob = null;
        JSONArray deviceArr = new JSONArray();
        try {
            condition = getParams(requestParams);
            int pageNo = 1;
            int pageSzie = 1000;
            message = ocService.getOcInstancesByCondition(device, pageNo, pageSzie, condition);

            jsob = JSONObject.parseObject(message);
            Integer totalNum = jsob.getInteger("totalNum");
            while ((pageNo - 1) * pageSzie < totalNum) {
                String tmpMessage = ocService.getOcInstancesByCondition(device, pageNo, pageSzie, condition);
                JSONObject tmpJsonInfo = JSON.parseObject(tmpMessage);
                JSONArray tmpDeviceArr = tmpJsonInfo.getJSONArray("objList");
                deviceArr.addAll(tmpDeviceArr);
                logger.info("获取" + device + "信息：第" + pageNo + "页，返回数据为-------" + tmpMessage);
                pageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceArr;
    }


    //获取请求body
    public String getParams(JSONObject requestParams) {

        String condition = "";
        if (requestParams.getString("name") != null) {
            condition = ocService.getCondition("name", requestParams.getString("name"), "contain", "and");
        }
        if (requestParams.getString("region") != null) {
            String value = requestParams.getString("region");
            String region = ocService.getRegion(value, requestParams);
            condition = ocService.getCondition("regionName", region, "equal", "and");
        }
        if (requestParams.getString("sysAdminBackup") != null) {
            condition = ocService.getCondition("sysAdministrator", requestParams.getString("sysAdministrator"), "equal", "and");
        }
        return condition;
    }

    /**
     * 批量新增库存设备
     *
     * @return
     */
    public JSONObject addReserveDevice(JSONArray jsonInfo) {

        String message = "";
        JSONObject jsob = null;
        JSONArray jarray = new JSONArray();

        //为库存设备设定nativaId
        for (int i = 0; i < jsonInfo.size(); i++) {
            JSONObject jb = jsonInfo.getJSONObject(i);
            String id = UUID.randomUUID().toString();
            jb.put("nativeId", id);
            jarray.add(jb);
        }

        try {
            message = ocService.addInstances(device, jarray.toString());
            jsob = JSONObject.parseObject(message);
        } catch (Exception e) {
            logger.info("新增库存设备过程中出现了错误：");
            e.printStackTrace();
        }

        return jsob;

    }

    /**
     * 获取全部库存服务器实例
     *
     * @param reserveServer
     * @return
     */
    public List<ReserveServer> getServerList(ReserveServer reserveServer, Boolean isRunning) {
        ReserveServerExample reserveServerExample = new ReserveServerExample();
        ReserveServerExample.Criteria criteria = reserveServerExample.createCriteria();
        reserveServerExample.setOrderByClause("-updateTime");
        if (isRunning != null) {
            if (isRunning) criteria.andAllocateStatusEqualTo("运行");
            else criteria.andAllocateStatusNotEqualTo("运行");
        }
        SqlUtil.fillCriteriaByInstance(reserveServer, criteria);
        return reserveServerMapper.selectByExample(reserveServerExample);
    }

    public ReserveServer getServerInstanceById(String id) {
        return reserveServerMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addReserveServer(ReserveServer reserveServer) {
        if (reserveServer.getLastUpdateTime() == null) {
            reserveServer.setLastUpdateTime(new Date());
        }
        checkAddReserveServerParams(reserveServer);
        reserveServer.setResId(IdUtils.generatedUUID());
        reserveServer.setAllocateStatus(ResourceStatus.RESERVE);
        reserveServerMapper.insertSelective(reserveServer);
        addReserveDiskFromExcel(reserveServer);
    }

    private void checkAddReserveServerParams(ReserveServer reserveServer) {
        if (reserveServer.getSn() == null || reserveServer.getSn().equals("")) {
            throw new RuntimeException("sn号为必填项");
        }
    }

    public List<ReserveDisk> getDiskList(ReserveDisk reserveDisk) {
        ReserveDiskExample reserveDiskExample = new ReserveDiskExample();
        ReserveDiskExample.Criteria criteria = reserveDiskExample.createCriteria();
        reserveDiskExample.setOrderByClause("-updateTime");
        SqlUtil.fillCriteriaByInstance(reserveDisk, criteria);
        return reserveDiskMapper.selectByExample(reserveDiskExample);
    }

    public void addReserveDisk(ReserveDisk reserveDisk) {
        reserveDisk.setResId(IdUtils.generatedUUID());
        reserveDiskMapper.insertSelective(reserveDisk);
    }

    public void deleteServer(JSONArray servers) {
        for (int i = 0; i < servers.size(); i++) {
            String id = (String) (servers.getJSONObject(i)).get("resId");
            reserveServerMapper.deleteByPrimaryKey(id);
            if ((servers.getJSONObject(i)).getInteger("deleteDiskFlag") == 1) {
                ReserveDiskExample example = new ReserveDiskExample();
                ReserveDiskExample.Criteria criteria = example.createCriteria();
                criteria.andServerIdEqualTo(id);
                reserveDiskMapper.deleteByExample(example);
            } else {
                ReserveDisk reserveDisk = new ReserveDisk();
                reserveDisk.setResId("");
                ReserveDiskExample example = new ReserveDiskExample();
                ReserveDiskExample.Criteria criteria = example.createCriteria();
                criteria.andServerIdEqualTo(id);
                reserveDiskMapper.updateByExampleSelective(reserveDisk, example);
            }
        }
    }

    public void deleteDisk(JSONArray disks) {
        for (int i = 0; i < disks.size(); i++) {
            reserveServerMapper.deleteByPrimaryKey((String) (disks.getJSONObject(i)).get("resId"));
        }
    }

    private void addReserveDiskFromExcel(ReserveServer reserveServer) {
        ReserveDisk reserveDisk = new ReserveDisk();

        if (reserveServer.getDisk1Count() != null && reserveServer.getDisk1Count() != 0) {
            reserveDisk.setDiskSize(reserveServer.getDisk1Size());
            reserveDisk.setInterfaceClass(reserveServer.getDisk1InterfaceClass());
            reserveDisk.setMediaClass(reserveServer.getDisk1MediaClass());
            reserveDisk.setUses(reserveServer.getDisk1Uses());
            reserveDisk.setServerId(reserveServer.getResId());
            for (int i = 0; i < reserveServer.getDisk1Count(); ++i) {
                addReserveDisk(reserveDisk);
            }
        }

        if (reserveServer.getDisk2Count() != null && reserveServer.getDisk2Count() != 0) {
            reserveDisk.setDiskSize(reserveServer.getDisk2Size());
            reserveDisk.setInterfaceClass(reserveServer.getDisk2InterfaceClass());
            reserveDisk.setMediaClass(reserveServer.getDisk2MediaClass());
            reserveDisk.setUses(reserveServer.getDisk2Uses());
            reserveDisk.setServerId(reserveServer.getResId());
            for (int i = 0; i < reserveServer.getDisk2Count(); ++i) {
                addReserveDisk(reserveDisk);
            }
        }

        if (reserveServer.getDisk3Count() != null && reserveServer.getDisk3Count() != 0) {
            reserveDisk.setDiskSize(reserveServer.getDisk3Size());
            reserveDisk.setInterfaceClass(reserveServer.getDisk3InterfaceClass());
            reserveDisk.setMediaClass(reserveServer.getDisk3MediaClass());
            reserveDisk.setUses(reserveServer.getDisk3Uses());
            reserveDisk.setServerId(reserveServer.getResId());
            for (int i = 0; i < reserveServer.getDisk3Count(); ++i) {
                addReserveDisk(reserveDisk);
            }
        }

        if (reserveServer.getDisk4Count() != null && reserveServer.getDisk4Count() != 0) {
            reserveDisk.setDiskSize(reserveServer.getDisk4Size());
            reserveDisk.setInterfaceClass(reserveServer.getDisk4InterfaceClass());
            reserveDisk.setMediaClass(reserveServer.getDisk4MediaClass());
            reserveDisk.setUses(reserveServer.getDisk4Uses());
            reserveDisk.setServerId(reserveServer.getResId());
            for (int i = 0; i < reserveServer.getDisk4Count(); ++i) {
                addReserveDisk(reserveDisk);
            }
        }
    }

    public List<ReserveDisk> getDiskByServerId(String id) {
        ReserveDiskExample example = new ReserveDiskExample();
        ReserveDiskExample.Criteria criteria = example.createCriteria();
        criteria.andServerIdEqualTo(id);
        return reserveDiskMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateReserveServersByIdOrSn(List<ReserveServer> reserveServers) {
        for (ReserveServer server : reserveServers) {
            String id = server.getResId();
            String sn = server.getSn();
            if (id == null && sn == null) {
                throw new IllegalArgumentException("必须传id或sn号");
            }
            ReserveServer temp;
            if (id == null) {
                temp = getReserveServerBySn(sn);
            } else {
                temp = reserveServerMapper.selectByPrimaryKey(id);
            }
            if (temp == null) {
                throw new RuntimeException("未查询到实例:" + server);
            } else {
                server.setResId(temp.getResId());
                server.setLastUpdateTime(new Date());
                reserveServerMapper.updateByPrimaryKeySelective(server);
            }
        }
    }

    public ReserveServer getReserveServerBySn(String sn) {
        if (sn.isEmpty()) {
            return null;
        }
        ReserveServerExample reserveServerExample = new ReserveServerExample();
        ReserveServerExample.Criteria criteria = reserveServerExample.createCriteria();
        criteria.andSnEqualTo(sn);
        List<ReserveServer> list = reserveServerMapper.selectByExample(reserveServerExample);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<ReserveServer> getReserveServerByresAllocationCode(String resAllocationCode) {
        ReserveServerExample reserveServerExample = new ReserveServerExample();
        reserveServerExample.createCriteria().andResAllocationCodeEqualTo(resAllocationCode);
        return reserveServerMapper.selectByExample(reserveServerExample);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateReserveServerBySn(ReserveServer server, Boolean isShelf) {
        ReserveServer temp = getReserveServerBySn(server.getSn());
        if (temp == null) {
            throw new RuntimeException(String.format("sn号为%s的设备不存在", server.getSn()));
        }
        server.setLastUpdateTime(new Date());
        server.setResId(temp.getResId());
        if (server.getAllocateStatus() == null && (isShelf == null || isShelf)) {
            server.setAllocateStatus(ResourceStatus.AVAILABLE);
        }
        reserveServerMapper.updateByPrimaryKeySelective(server);
    }

    public void updateReserveDiskById(JSONArray disks) {
        for (int i = 0; i < disks.size(); i++) {
            ReserveDisk disk = JSON.toJavaObject(disks.getJSONObject(i), ReserveDisk.class);
            String id = disk.getResId();
            ReserveDisk temp = reserveDiskMapper.selectByPrimaryKey(id);
            if (temp != null) {
                reserveDiskMapper.updateByPrimaryKeySelective(disk);
            } else {
                addReserveDisk(disk);
            }
        }
    }

    public JSONArray getReserveServerAssortCount(Boolean isRunning) {

        List<Map> reserveServerCountList = reserveDeviceMapper.getReserveServerCount(isRunning);
        JSONArray result = new JSONArray();
        //未分类主机数量
        long unknownCount = 0;
        for (Map reserveServerCount : reserveServerCountList) {
            String deviceAssort = (String) reserveServerCount.get("deviceAssort");
            long count = (long) reserveServerCount.get("count(*)");
            if (deviceAssort == null || deviceAssort.equals("")) {
                unknownCount += count;
            } else {
                JSONObject tempJsonObject = new JSONObject();
                tempJsonObject.put("label", deviceAssort);
                tempJsonObject.put("count", count);
                result.add(tempJsonObject);
            }
        }
        if (unknownCount > 0) {
            JSONObject tempJsonObject = new JSONObject();
            tempJsonObject.put("label", "未分类");
            tempJsonObject.put("count", unknownCount);
            result.add(tempJsonObject);
        }

        return result;
    }

    public JSONArray getAllReserveJumpers() {
        return JSONArray.parseArray(JSONArray.toJSONString(reserveJumperMapper.selectByExample(null)));
    }

    @Transactional(rollbackFor = Exception.class)
    public void addReserveJumper(ReserveJumper reserveJumper) {
        reserveJumper.setResId(IdUtils.generatedUUID());
        reserveJumperMapper.insertSelective(reserveJumper);
    }

    public List<ReserveStorage> getReserveStorages(ReserveStorage reserveStorage, Boolean isRunning) {
        ReserveStorageExample reserveStorageExample = new ReserveStorageExample();
        ReserveStorageExample.Criteria criteria = reserveStorageExample.createCriteria();
        reserveStorageExample.setOrderByClause("type1,physicalPosition,type2,clusterName");
        if (isRunning != null) {
            if (isRunning) criteria.andResourceStatusEqualTo("运行");
            else criteria.andResourceStatusNotEqualTo("运行");
        }
        SqlUtil.fillCriteriaByInstance(reserveStorage, criteria);
        return reserveStorageMapper.selectByExample(reserveStorageExample);
    }

    public List<ReserveStorage> getReserveStorageByIds(List<String> ids) {
        ReserveStorageExample reserveStorageExample = new ReserveStorageExample();
        ReserveStorageExample.Criteria criteria = reserveStorageExample.createCriteria();
        criteria.andResIdIn(ids);
        return reserveStorageMapper.selectByExample(reserveStorageExample);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addReserveStorage(ReserveStorage reserveStorage) {
        reserveStorage.setResId(IdUtils.generatedUUID());
        reserveStorage.setResourceStatus(ResourceStatus.RESERVE);
        reserveStorageMapper.insertSelective(reserveStorage);
    }

    public List<ReserveSan> getReserveSans(ReserveSan reserveSan, Boolean isRunning) {
        ReserveSanExample reserveSanExample = new ReserveSanExample();
        reserveSanExample.setOrderByClause("-updateTime");
        ReserveSanExample.Criteria criteria = reserveSanExample.createCriteria();
        if (isRunning != null) {
            if (isRunning) criteria.andResourceStatusEqualTo("运行");
            else criteria.andResourceStatusNotEqualTo("运行");
        }
        SqlUtil.fillCriteriaByInstance(reserveSan, criteria);
        return reserveSanMapper.selectByExample(reserveSanExample);
    }

    public List<ReserveSan> getReserveSanByIds(List<String> ids) {
        ReserveSanExample reserveSanExample = new ReserveSanExample();
        ReserveSanExample.Criteria criteria = reserveSanExample.createCriteria();
        criteria.andResIdIn(ids);
        return reserveSanMapper.selectByExample(reserveSanExample);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addReserveSan(ReserveSan reserveSan) {
        reserveSan.setResId(IdUtils.generatedUUID());
        reserveSan.setResourceStatus(ResourceStatus.RESERVE);
        reserveSanMapper.insertSelective(reserveSan);
    }

    @Transactional
    public void deleteReserveSanByIds(List<String> ids) {
        for (String id : ids) {
            reserveSanMapper.deleteByPrimaryKey(id);
        }
    }

    @Transactional
    public void deleteReserveStorageByIds(List<String> ids) {
        for (String id : ids) {
            reserveStorageMapper.deleteByPrimaryKey(id);
        }
    }

    public List<ReserveServer> getReserveServerByIds(List<String> ids) {
        ReserveServerExample reserveServerExample = new ReserveServerExample();
        ReserveServerExample.Criteria criteria = reserveServerExample.createCriteria();
        criteria.andResIdIn(ids);
        return reserveServerMapper.selectByExample(reserveServerExample);
    }

    public List fuzzySearchReserveDevice(FuzzySearch fuzzySearch) {
        Class clazz;
        BaseMapper baseMapper;
        switch (fuzzySearch.getClassName()) {
            case "ReserveSan":
                clazz = ReserveSan.class;
                baseMapper = reserveSanMapper;
                break;
            case "ReserveStorage":
                clazz = ReserveStorage.class;
                baseMapper = reserveStorageMapper;
                break;
            case "ReserveServer":
                clazz = ReserveServer.class;
                baseMapper = reserveServerMapper;
                break;
            default:
                throw new IllegalArgumentException("不支持的查询类型");
        }
        QueryWrapper queryWrapper = SqlUtil.fuzzySearchFullField(clazz, fuzzySearch.getValue(), fuzzySearch.getSearchedFields());
        return baseMapper.selectList(queryWrapper);
    }

    public List<Map> getReserveStorageCountInfo(Boolean isRunning) {
        List<Map> type1Count = reserveDeviceMapper.getReserveStorageType1Count(isRunning);
        List<Map> type2Count = reserveDeviceMapper.getReserveStorageType2Count(isRunning);
        Integer allCount = reserveDeviceMapper.getReserveStorageCount(isRunning);
        Integer nonTypeCount = reserveDeviceMapper.getReserveStorageNonTypeCount(isRunning);

        List<Map> result = new ArrayList<>();
        for (Map reserveStorageCount : type1Count) {
            String type1 = (String) reserveStorageCount.get("type1");
            long count = (long) reserveStorageCount.get("count(*)");
            Map<String, Object> item = new HashMap<>();
            item.put("label", type1);
            item.put("count", count);
            item.put("type", "type1");
            result.add(item);
        }
        for (Map reserveStorageCount : type2Count) {
            String type2 = (String) reserveStorageCount.get("type2");
            long count = (long) reserveStorageCount.get("count(*)");
            Map<String, Object> item = new HashMap<>();
            item.put("label", type2);
            item.put("count", count);
            item.put("type", "type2");
            result.add(item);
        }
        result.add(new HashMap() {
            {
                put("label", "全部存储设备");
                put("count", allCount);
            }
        });
        result.add(new HashMap() {
            {
                put("label", "未分类存储设备");
                put("count", nonTypeCount);
            }
        });
        ReserveSanExample reserveSanExample = new ReserveSanExample();
        ReserveSanExample.Criteria criteria = reserveSanExample.createCriteria();
        if (isRunning != null) {
            if (isRunning) criteria.andResourceStatusEqualTo("运行");
            else criteria.andResourceStatusNotEqualTo("运行");
        }

        Integer sanCount = reserveSanMapper.countByExample(reserveSanExample);
        result.add(new HashMap() {
            {
                put("label", "SAN交换机");
                put("count", sanCount);
            }
        });

        return result;
    }

    public Integer getReserveStorageCount(ReserveStorage reserveStorage, Boolean hasCapacity, Boolean isRunning) {
        ReserveStorageExample reserveStorageExample = new ReserveStorageExample();
        ReserveStorageExample.Criteria criteria = reserveStorageExample.createCriteria();
        SqlUtil.fillCriteriaByInstance(reserveStorage, criteria);
        if (hasCapacity) criteria.andTotalStorageCapacityGreaterThan(0);
        if (isRunning) criteria.andResourceStatusEqualTo("运行");
        Integer ans = reserveStorageMapper.countByExample(reserveStorageExample);
        return ans;
    }

    public Map<String, BigDecimal> getReserveStorageCapacity(ReserveStorage reserveStorage) {
        ReserveStorage temp = new ReserveStorage();
        temp.setType1(reserveStorage.getType1());
        temp.setType2(reserveStorage.getType2());
        temp.setPhysicalPosition(reserveStorage.getPhysicalPosition());
        Map<String, BigDecimal> map = reserveDeviceMapper.getReserveStorageCapacitySum(temp);
        BigDecimal rest = map.get("left");
        BigDecimal total = map.get("total");
        BigDecimal used = total.subtract(rest);
        map.put("usage", used);
        return map;
    }

    public List<Map<String, Object>> getCentralizedStorageRegionCountTableInfo() {
        List<Map<String, Object>> res = getStorageRegionCountByType1("集中式");
        for (Map map : res) {
            map.remove("object");
        }
        return res;
    }

    public List<Map<String, Object>> getDistributedStorageRegionCountTableInfo() {
        List<Map<String, Object>> res = getStorageRegionCountByType1("分布式");
        return res;
    }

    public List<Map<String, Object>> getStorageRegionCountByType1(String type1) {
        Map<String, String> type2NameMap = new HashMap<>();

        type2NameMap.put("块", "block");
        type2NameMap.put("文件", "file");
        type2NameMap.put("对象", "object");

        Map<String, Object> totalCountMap = new HashMap<>();
        totalCountMap.put("location", "总计");
        for (Map.Entry<String, String> type2NameEntry : type2NameMap.entrySet()) {
            totalCountMap.put(type2NameEntry.getValue(), 0);
        }

        Map<String, String> regionNameMap = new HashMap<>();
        regionNameMap.put("北京科技园", "科技园");
        regionNameMap.put("上海卡园", "卡园");

        List<Map<String, Object>> res = new ArrayList<>();

        for (Map.Entry<String, String> regionNameEntry : regionNameMap.entrySet()) {
            String region = regionNameEntry.getKey();
            Map<String, Object> regionCountMap = new HashMap<>();
            regionCountMap.put("location", regionNameEntry.getValue());
            for (Map.Entry<String, String> type2NameEntry : type2NameMap.entrySet()) {
                String type2 = type2NameEntry.getKey();
                ReserveStorage temp = new ReserveStorage();
                temp.setPhysicalPosition(region);
                temp.setType2(type2);
                temp.setType1(type1);
                Integer count = getReserveStorageCount(temp, true, true);
                regionCountMap.put(type2NameEntry.getValue(), count);
                totalCountMap.put(type2NameEntry.getValue(), count + (Integer) totalCountMap.get(type2NameEntry.getValue()));
            }
            res.add(regionCountMap);
        }
        res.add(totalCountMap);
        return res;
    }

    public List<Map<String, Object>> getCentralizedStorageRegionCapacityTableInfo() {
        List<Map<String, Object>> res = getStorageRegionCapacityByType1("集中式");
        for (Map map : res) {
            map.remove("objectLeft");
            map.remove("objectUsage");
            map.remove("objectTotal");
        }
        return res;
    }

    public List<Map<String, Object>> getDistributedStorageRegionCapacityTableInfo() {
        return getStorageRegionCapacityByType1("分布式");
    }

    public List<Map<String, Object>> getStorageRegionCapacityByType1(String type1) {
        Map<String, String> type2NameMap = new HashMap<>();

        type2NameMap.put("块", "block");
        type2NameMap.put("文件", "file");
        type2NameMap.put("对象", "object");

        Map<String, Object> totalCapacityMap = new HashMap<>();
        totalCapacityMap.put("location", "总计");
        for (Map.Entry<String, String> type2NameEntry : type2NameMap.entrySet()) {
            totalCapacityMap.put(type2NameEntry.getValue() + "Left", new BigDecimal(0));
            totalCapacityMap.put(type2NameEntry.getValue() + "Usage", new BigDecimal(0));
            totalCapacityMap.put(type2NameEntry.getValue() + "Total", new BigDecimal(0));
        }

        Map<String, String> regionNameMap = new HashMap<>();
        regionNameMap.put("北京科技园", "科技园");
        regionNameMap.put("上海卡园", "卡园");

        List<Map<String, Object>> res = new ArrayList<>();

        for (Map.Entry<String, String> regionNameEntry : regionNameMap.entrySet()) {
            String region = regionNameEntry.getKey();
            Map<String, Object> regionCapacityMap = new HashMap<>();
            regionCapacityMap.put("location", regionNameEntry.getValue());
            for (Map.Entry<String, String> type2NameEntry : type2NameMap.entrySet()) {
                String type2 = type2NameEntry.getKey();
                ReserveStorage temp = new ReserveStorage();
                temp.setPhysicalPosition(region);
                temp.setType2(type2);
                temp.setType1(type1);
                Map<String, BigDecimal> capacityMap = getReserveStorageCapacity(temp);
                for (Map.Entry<String, BigDecimal> capacityMapEntry : capacityMap.entrySet()) {
                    String key = capacityMapEntry.getKey();
                    key = Character.toUpperCase(key.charAt(0)) + key.substring(1);
                    BigDecimal value = capacityMapEntry.getValue();
                    regionCapacityMap.put(type2NameEntry.getValue() + key, value);
                    totalCapacityMap.put(type2NameEntry.getValue() + key, value.add((BigDecimal) totalCapacityMap.get(type2NameEntry.getValue() + key)));
                }
            }
            res.add(regionCapacityMap);
        }
        res.add(totalCapacityMap);
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateReserveStorageById(ReserveStorage reserveStorage) {
        String id = reserveStorage.getResId();
        if (id == null || id.isEmpty()) {
            throw new RuntimeException("id为必填项");
        }

        ReserveStorage temp = reserveStorageMapper.selectByPrimaryKey(id);
        if (temp == null) {
            throw new RuntimeException(String.format("id为%s的设备不存在", id));
        }

        //数据库中一定不为空
        if (reserveStorage.getTotalStorageCapacity() == null) {
            reserveStorage.setTotalStorageCapacity(temp.getTotalStorageCapacity());
        }
        if (reserveStorage.getRestStorageCapacity() == null) {
            reserveStorage.setRestStorageCapacity(temp.getRestStorageCapacity());
        }

        checkResourceStorageCapacity(reserveStorage.getRestStorageCapacity(), reserveStorage.getTotalStorageCapacity());

        reserveStorageMapper.updateByPrimaryKeySelective(reserveStorage);
    }

    void checkResourceStorageCapacity(Integer rest, Integer total) {
        if (rest < 0 || total < 0) {
            throw new RuntimeException("容量不得小于零");
        }
        if (rest > total) {
            throw new RuntimeException("剩余容量不得大于全部容量");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateReserveStorageByIds(List<ReserveStorage> reserveStorageList) {
        for (ReserveStorage reserveStorage : reserveStorageList) {
            updateReserveStorageById(reserveStorage);
        }
    }


    public void insertPhysicalTape(PhysicalTape physicalTape) {
        physicalTapeMapper.insert(physicalTape);
    }

    public void insertVirtualTape(VirtualTape virtualTape) {
        virtualTapeMapper.insert(virtualTape);
    }

    public void updatePhysicalTape(PhysicalTape physicalTape) {
        physicalTapeMapper.updateById(physicalTape);
    }

    public void updateVirtualTape(VirtualTape virtualTape) {
        virtualTapeMapper.updateById(virtualTape);
    }

    public void deletePhysicalTape(PhysicalTape physicalTape) {
        physicalTapeMapper.deleteById(physicalTape.getResId());
    }

    public void deleteVirtualTape(VirtualTape virtualTape) {
        virtualTapeMapper.deleteById(virtualTape.getResId());
    }

    @Transactional(rollbackFor = Exception.class)
    public void uploadReserveSafeByExcel(ReserveSafe reserveSafe) {
        List<ReserveSafe> reserveSafeList = reserveSafeMapper.selectList(null);
        Map<String, ReserveSafe> originalReserveSafeListMap = reserveSafeList.stream().collect(Collectors.toMap(k -> k.getSn(), ReserveSafe -> ReserveSafe));
        reserveSafe.setResId(IdUtils.generatedUUID());
        reserveSafe.setResourceStatus("运行");

        if (reserveSafe.getSn() == null) {
            throw new IllegalArgumentException("sn号为空");
        }

        String locationCode = reserveSafe.getLocationCode();
        if (locationCode == null) {
            throw new IllegalArgumentException("locationCode为空");
        }
        if (StringUtils.charAtCount(locationCode, '-') != 2 || locationCode.charAt(locationCode.length() - 1) != 'U') {
            throw new IllegalArgumentException("locationCode不合规");
        }

        if (originalReserveSafeListMap.containsKey(reserveSafe.getSn())) {
            reserveSafe.setResId(null);
            QueryWrapper<ReserveSafe> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sn", reserveSafe.getSn());
            reserveSafeMapper.update(reserveSafe, queryWrapper);
        } else {
            reserveSafeMapper.insert(reserveSafe);
        }
    }

    public List<ReserveSafe> getReserveSafe(ReserveSafe reserveSafe) {
        return reserveSafeMapper.getAllInstance(reserveSafe);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addReserveSafe(ReserveSafe reserveSafe) {
        reserveSafe.setResId(IdUtils.generatedUUID());
        reserveSafe.setResourceStatus("库存");
        reserveSafeMapper.insert(reserveSafe);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateReserveSafe(List<ReserveSafe> reserveSafeList) {
        for (ReserveSafe reserveSafe : reserveSafeList) {
            LambdaQueryWrapper<ReserveSafe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            String resId = reserveSafe.getResId();
            if (resId == null) {
                throw new IllegalArgumentException("必须传id号");
            }
            lambdaQueryWrapper.eq(ReserveSafe::getResId, resId);
            ReserveSafe tempReserveSafe = reserveSafeMapper.selectOne(lambdaQueryWrapper);
            if (tempReserveSafe != null) {
                reserveSafe.setUpdateTime(new Date());
                reserveSafeMapper.update(reserveSafe, lambdaQueryWrapper);
            } else {
                throw new RuntimeException("未查询到实例:" + reserveSafe);
            }
        }
    }

    @Transactional
    public void deleteReserveSafe(List<ReserveSafe> reserveSafeList) {
        for (ReserveSafe reserveSafe : reserveSafeList) {
            LambdaQueryWrapper<ReserveSafe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ReserveSafe::getResId, reserveSafe.getResId());
            reserveSafeMapper.delete(lambdaQueryWrapper);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void uploadReserveNetworkByExcel(ReserveNetwork reserveNetwork) {
        List<ReserveNetwork> reserveNetworkList = reserveNetworkMapper.selectList(null);
        Map<String, ReserveNetwork> originalReserveNetworkListMap = reserveNetworkList.stream().collect(Collectors.toMap(k -> k.getSn(), ReserveNetwork -> ReserveNetwork));
        reserveNetwork.setResId(IdUtils.generatedUUID());

        if (reserveNetwork.getSn() == null) {
            throw new IllegalArgumentException("sn号为空");
        }

        String locationCode = reserveNetwork.getLocationCode();
        if (locationCode == null) {
            throw new IllegalArgumentException("locationCode为空");
        }
        if (StringUtils.charAtCount(locationCode, '-') != 2 || locationCode.charAt(locationCode.length() - 1) != 'U') {
            throw new IllegalArgumentException("locationCode不合规");
        }

        if (reserveNetwork.getDeviceType().equals("防火墙")) {
            reserveNetwork.setDeviceType("FIREWALL");
        } else if (reserveNetwork.getDeviceType().equals("交换机")) {
            reserveNetwork.setDeviceType("SWITCH");
        } else if (reserveNetwork.getDeviceType().equals("路由器")) {
            reserveNetwork.setDeviceType("ROUTER");
        } else if (reserveNetwork.getDeviceType().equals("负载均衡")) {
            reserveNetwork.setDeviceType("LOAD_BALANCE");
        }

        if (originalReserveNetworkListMap.containsKey(reserveNetwork.getSn())) {
            reserveNetwork.setResId(null);
            QueryWrapper<ReserveNetwork> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sn", reserveNetwork.getSn());
            reserveNetworkMapper.update(reserveNetwork, queryWrapper);
        } else {
            reserveNetworkMapper.insert(reserveNetwork);
        }
    }

    public List<ReserveNetwork> getReserveNetwork(ReserveNetwork reserveNetwork) {
        return reserveNetworkMapper.getAllInstance(reserveNetwork);
    }

    @Transactional
    public void deleteReserveNetwork(List<ReserveNetwork> reserveNetworkList) {
        for (ReserveNetwork reserveNetwork : reserveNetworkList) {
            LambdaQueryWrapper<ReserveNetwork> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ReserveNetwork::getResId, reserveNetwork.getResId());
            reserveNetworkMapper.delete(lambdaQueryWrapper);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateReserveNetwork(List<ReserveNetwork> reserveNetworkList) {
        for (ReserveNetwork reserveNetwork : reserveNetworkList) {
            LambdaQueryWrapper<ReserveNetwork> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            String resId = reserveNetwork.getResId();
            if (resId == null) {
                throw new IllegalArgumentException("必须传id号");
            }
            lambdaQueryWrapper.eq(ReserveNetwork::getResId, resId);
            ReserveNetwork tempReserveNetwork = reserveNetworkMapper.selectOne(lambdaQueryWrapper);
            if (tempReserveNetwork != null) {
                reserveNetwork.setUpdateTime(new Date());
                reserveNetworkMapper.update(reserveNetwork, lambdaQueryWrapper);
            } else {
                throw new RuntimeException("未查询到实例:" + reserveNetwork);
            }
        }
    }

    public JSONArray getAllReserveNetworkCount() {

        List<Map> reserveNetworkCountList = reserveDeviceMapper.getReserveNetworkCount();
        JSONArray result = new JSONArray();
        //未分类主机数量
        long unknownCount = 0;
        for (Map reserveNetworkCount : reserveNetworkCountList) {
            String deviceType = (String) reserveNetworkCount.get("deviceType");
            long count = (long) reserveNetworkCount.get("count(*)");
            if (deviceType == null || deviceType.equals("")) {
                unknownCount += count;
            } else {
                JSONObject tempJsonObject = new JSONObject();
                tempJsonObject.put("label", deviceType);
                tempJsonObject.put("count", count);
                result.add(tempJsonObject);
            }
        }
        if (unknownCount > 0) {
            JSONObject tempJsonObject = new JSONObject();
            tempJsonObject.put("label", "未分类");
            tempJsonObject.put("count", unknownCount);
            result.add(tempJsonObject);
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateReserveSanByIds(List<ReserveSan> reserveSanList) {
        for (ReserveSan reserveSan : reserveSanList) {
            String id = reserveSan.getResId();
            if (id == null) {
                throw new IllegalArgumentException("必须传id号");
            }
            ReserveSan temp = reserveSanMapper.selectByPrimaryKey(id);
            if (temp != null) {
                reserveSanMapper.updateByPrimaryKeySelective(reserveSan);
            } else {
                throw new RuntimeException("未查询到实例:" + reserveSan);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDisksFromServer(String serverId) {
        ReserveDiskExample reserveDiskExample = new ReserveDiskExample();
        ReserveDiskExample.Criteria criteria = reserveDiskExample.createCriteria();
        criteria.andServerIdEqualTo(serverId);
        reserveDiskMapper.deleteByExample(reserveDiskExample);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteReserveServer(String serverId) {
        reserveServerMapper.deleteByPrimaryKey(serverId);
        deleteDisksFromServer(serverId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void uploadSynchroReserveServer(ReserveServer reserveServer, Boolean updateWhenDuplicateSn) {
        if (Strings.isBlank(reserveServer.getSn())) {
            throw new IllegalArgumentException("sn号为空");
        }
        ReserveServer temp = getReserveServerBySn(reserveServer.getSn());
        if (temp != null) {
            if (updateWhenDuplicateSn) {
                reserveServer.setResId(temp.getResId());
                reserveServer.setLastUpdateTime(new Date());
                reserveServerMapper.updateByPrimaryKeySelective(reserveServer);
            } else {
                throw new RuntimeException(String.format("sn号为%s的设备已存在", reserveServer.getSn()));
            }
        } else {
            reserveServer.setResId(IdUtils.generatedUUID());
            reserveServer.setLastUpdateTime(new Date());
            reserveServerMapper.insertSelective(reserveServer);
        }
        if (reserveServer.getDisk1Count() != null && reserveServer.getDisk1Count() > 0) {
            //delete all origin disks
            deleteDisksFromServer(reserveServer.getResId());
            addReserveDiskFromExcel(reserveServer);
        }
    }

    public ReserveNetwork getReserveNetworkById(String resId) {
        LambdaQueryWrapper<ReserveNetwork> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ReserveNetwork::getResId, resId);
        ReserveNetwork reserveNetwork = reserveNetworkMapper.selectOne(lambdaQueryWrapper);
        return reserveNetwork;
    }
}