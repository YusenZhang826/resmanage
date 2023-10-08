package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.clic.ccdbaas.dao.CloudVmNovaMapper;
import com.clic.ccdbaas.dao.IpManageMapper;
import com.clic.ccdbaas.dao.PhysicalHostMapper;
import com.clic.ccdbaas.dao.PhysicalMachineMapper;
import com.clic.ccdbaas.entity.*;
import com.clic.ccdbaas.enums.AllocateStatus;
import com.clic.ccdbaas.utils.IpSegmentUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("IP_MANAGE")
public class IpManageService {
    @Autowired
    private IpManageMapper ipManageMapper;
    @Autowired
    private PhysicalHostMapper physicalHostMapper;
    @Autowired
    private PhysicalMachineMapper physicalMachineMapper;
    @Autowired
    private CloudVmNovaMapper cloudVmNovaMapper;
    @Autowired
    private VlanService vlanService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OcService ocService;
    @Value("${oc.cmdb.subnet}")
    private String subnetClass;
    @Value("${oc.cmdb.port}")
    private String portClass;
    @Value("${oc.cmdb.port.subnet}")
    private String relationName;

    public void generateIpFromVlan() {
        String[] allIps = {};
        String broadcastId = "";
        String regEx = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])[/](\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        String regEx1 = "\\s((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])[/](\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        String regEx2 = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])[/]((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        String regEx3 = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])[~]((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        String regEx4 = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])[-]((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        String regEx5 = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])[~](\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        String regEx6 = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])[-](\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        String regEx7 = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])";
        List<VlanPlan> vlanList = vlanService.getAllVlanInstance(null);
        for (VlanPlan vlan : vlanList) {
            if (vlan.getName().matches(regEx1)) {
                SubnetUtils utils = new SubnetUtils(vlan.getName().replace(" ", ""));
                allIps = utils.getInfo().getAllAddresses();
                broadcastId = utils.getInfo().getBroadcastAddress();
            } else if (vlan.getName().matches(regEx)) {
                SubnetUtils utils = new SubnetUtils(vlan.getName());
                allIps = utils.getInfo().getAllAddresses();
                broadcastId = utils.getInfo().getBroadcastAddress();
            } else if (vlan.getName().matches(regEx2)) {
                String[] temp = vlan.getName().split("/");
                SubnetUtils utils = new SubnetUtils(temp[0] + "/" + IpSegmentUtils.getNetMask(temp[1]));
                allIps = utils.getInfo().getAllAddresses();
                broadcastId = utils.getInfo().getBroadcastAddress();
            } else if (vlan.getName().matches(regEx3)) {
                String[] temp = vlan.getName().split("~");
                List<String> allIpAndBroad = IpSegmentUtils.findIPsForIpv4(temp[0], temp[1]);
                allIps = allIpAndBroad.subList(0, allIpAndBroad.size() - 1).toArray(new String[0]);
                broadcastId = allIpAndBroad.get(allIpAndBroad.size() - 1);
            } else if (vlan.getName().matches(regEx4)) {
                String[] temp = vlan.getName().split("-");
                List<String> allIpAndBroad = IpSegmentUtils.findIPsForIpv4(temp[0], temp[1]);
                allIps = allIpAndBroad.subList(0, allIpAndBroad.size() - 1).toArray(new String[0]);
                broadcastId = allIpAndBroad.get(allIpAndBroad.size() - 1);
            } else if (vlan.getName().matches(regEx5)) {
                String[] temp = vlan.getName().split("~");
                String[] startIp = temp[0].split(".");
                List<String> allIpAndBroad = IpSegmentUtils.findIPsForIpv4(temp[0], startIp[0] + "." + startIp[1] + "." + startIp[2] + "." + temp[1]);
                allIps = allIpAndBroad.subList(0, allIpAndBroad.size() - 1).toArray(new String[0]);
                broadcastId = allIpAndBroad.get(allIpAndBroad.size() - 1);
            } else if (vlan.getName().matches(regEx6)) {
                String[] temp = vlan.getName().split("-");
                String[] startIp = temp[0].split(".");
                List<String> allIpAndBroad = IpSegmentUtils.findIPsForIpv4(temp[0], startIp[0] + "." + startIp[1] + "." + startIp[2] + "." + temp[1]);
                allIps = allIpAndBroad.subList(0, allIpAndBroad.size() - 1).toArray(new String[0]);
                broadcastId = allIpAndBroad.get(allIpAndBroad.size() - 1);
            } else if (vlan.getName().matches(regEx7)) {
                String[] startIp = vlan.getName().split(".");
                String endIp = startIp[0] + "." + startIp[1] + "." + startIp[2] + "." + "255";
                List<String> allIpAndBroad = IpSegmentUtils.findIPsForIpv4(vlan.getName(), endIp);
                allIps = allIpAndBroad.subList(0, allIpAndBroad.size() - 1).toArray(new String[0]);
                broadcastId = allIpAndBroad.get(allIpAndBroad.size() - 1);
            }

            for (int i = 0; i < allIps.length - 1; i++) {
                IpManage ipManage = new IpManage();
                ipManage.setAllocateStatus("未分配");
                ipManage.setVlanName(vlan.getName());
                ipManage.setIp(allIps[i]);
                addIpManage(ipManage);
            }
            //设置网关
            IpManage gateway = new IpManage();
            gateway.setAllocateStatus("保留");
            gateway.setVlanName(vlan.getName());
            gateway.setRemark("网关地址");
            gateway.setIp(allIps[allIps.length - 1]);
            addIpManage(gateway);
            //设置广播地址
            IpManage broadcast = new IpManage();
            broadcast.setAllocateStatus("保留");
            broadcast.setVlanName(vlan.getName());
            broadcast.setIp(broadcastId);
            broadcast.setRemark("广播地址");
            addIpManage(broadcast);
        }
    }

    public void addIpManage(IpManage ipManage) {
        if (ipManage.getIp() != null) {
            QueryWrapper<IpManage> queryWrapper = new QueryWrapper();
            queryWrapper.eq("ip", ipManage.getIp());
            IpManage temp = ipManageMapper.selectOne(queryWrapper);
            if (temp == null) {
                ipManage.setResId(IdUtils.generatedUUID());
                ipManage.setLastModified(new Date());
                ipManageMapper.addIp(ipManage);
            } else {
                ipManage.setResId(temp.getResId());
                if (!temp.equals(ipManage)) {
                    ipManage.setLastModified(new Date());
                    ipManageMapper.updateIp(ipManage);
                }
            }
        }
    }

    public List<HashMap> getAllInstance(IpManage ipManage) {
        if (ipManage.getResId() != null && ipManage.getResId() != "") {
            ipManage.setResIdArr(Arrays.asList(ipManage.getResId().split(",")));
        }
        if (ipManage.getAllocateStatus() != null && ipManage.getAllocateStatus() != "") {
            ipManage.setAllocateStatuss(Arrays.asList(ipManage.getAllocateStatus().split(",")));
        }
        ipManage.setExploratoryStateNullFlag(-1);
        if (ipManage.getExploratoryState() != null && ipManage.getExploratoryState() != "") {
            if (ipManage.getExploratoryState().equals("其他")) {
                ipManage.setExploratoryStateNullFlag(0);
            } else if (ipManage.getExploratoryState().contains("其他")) {
                ipManage.setExploratoryStateNullFlag(1);
                ipManage.setExploratoryState(ipManage.getExploratoryState().replace(",其他", "").replace("其他,", ""));
            } else {
                ipManage.setExploratoryStateNullFlag(2);
            }
            ipManage.setExploratoryStates(Arrays.asList(ipManage.getExploratoryState().split(",")));
        }
        return ipManageMapper.getAllInstance(ipManage);
    }

    public List<IpManage> getAllIp(IpManage ipManage) {
        if (ipManage.getResId() != null && ipManage.getResId() != "") {
            ipManage.setResIdArr(Arrays.asList(ipManage.getResId().split(",")));
        }
        if (ipManage.getAllocateStatus() != null && ipManage.getAllocateStatus() != "") {
            ipManage.setAllocateStatuss(Arrays.asList(ipManage.getAllocateStatus().split(",")));
        }
        ipManage.setExploratoryStateNullFlag(-1);
        if (ipManage.getExploratoryState() != null && ipManage.getExploratoryState() != "") {
            if (ipManage.getExploratoryState().equals("其他")) {
                ipManage.setExploratoryStateNullFlag(0);
            } else if (ipManage.getExploratoryState().contains("其他")) {
                ipManage.setExploratoryStateNullFlag(1);
                ipManage.setExploratoryState(ipManage.getExploratoryState().replace(",其他", "").replace("其他,", ""));
            } else {
                ipManage.setExploratoryStateNullFlag(2);
            }
            ipManage.setExploratoryStates(Arrays.asList(ipManage.getExploratoryState().split(",")));
        }
        return ipManageMapper.getAllIp(ipManage);
    }

    public HashMap getInstanceDetail(String resId) {
        return ipManageMapper.selectIpByResId(resId);
    }

    public void addIpInfo() {
        List<List> ipList = new ArrayList<>();
        ipList.add(ipManageMapper.selectIpsFromVm());
        ipList.add(ipManageMapper.selectIpsFromHost());
        ipList.add(ipManageMapper.selectIpsFromPhysicalMachine());
        for (List<HashMap<String, String>> ips : ipList) {
            for (HashMap<String, String> map : ips) {
                updateBelongProductByIp(map.get("mainIp"), map.get("belongProduct"));
                if (map.get("relatedIp") != null) {
                    if (map.get("relatedIp").contains(";")) {
                        String[] relateIps = map.get("relatedIp").split(";");
                        for (int i = 0; i < relateIps.length; i++) {
                            updateBelongProductByIp(relateIps[i], map.get("belongProduct"));
                        }
                    } else if (map.get("relatedIp").contains(",")) {
                        String[] relateIps = map.get("relatedIp").split(", ");
                        for (int i = 0; i < relateIps.length; i++) {
                            updateBelongProductByIp(relateIps[i], map.get("belongProduct"));
                        }
                    }
                }
                if (map.get("virtualIp") != null) {
                    if (map.get("virtualIp").contains(";")) {
                        String[] virtualIps = map.get("virtualIp").split(";");
                        for (int i = 0; i < virtualIps.length; i++) {
                            updateBelongProductByIp(virtualIps[i], map.get("belongProduct"));
                        }
                    } else if (map.get("virtualIp").contains(",")) {
                        String[] virtualIps = map.get("virtualIp").split(", ");
                        for (int i = 0; i < virtualIps.length; i++) {
                            updateBelongProductByIp(virtualIps[i], map.get("belongProduct"));
                        }
                    }
                }
            }
        }
    }

    private void updateBelongProductByIp(String mainIp, String belongProduct) {
        LambdaUpdateWrapper<IpManage> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
                .eq(IpManage::getIp, mainIp)
                .set(IpManage::getAllocateStatus, AllocateStatus.ALLOCATED)
                .set(IpManage::getBelongProduct, belongProduct);
        ipManageMapper.update(null, lambdaUpdateWrapper);
    }

    //通过宿主机、虚机、物理机三张表获取已分配的IP
    private String getIpInfoByIp(String ip) {
        List<List> mapList = new ArrayList<>();
        mapList.add(ipManageMapper.getIpsFromVm(ip));
        mapList.add(ipManageMapper.getIpsFromPhysicalMachine(ip));
        mapList.add(ipManageMapper.getIpsFromHost(ip));
        for (List<HashMap<String, String>> maps : mapList) {
            for (HashMap map : maps) {
                if (map.get("mainIp").equals(ip)) {
                    return (String) map.get("belongProduct");
                }
                if (map.get("relatedIp") != null) {
                    String[] relateIps = map.get("relatedIp").toString().split(";");
                    for (int i = 0; i < relateIps.length; i++) {
                        if (relateIps[i].equals(ip)) {
                            return (String) map.get("belongProduct");
                        }
                    }
                }
                if (map.get("virtualIp") != null) {
                    String[] virtualIps = map.get("virtualIp").toString().split(";");
                    for (int i = 0; i < virtualIps.length; i++) {
                        if (virtualIps[i].equals(ip)) {
                            return (String) map.get("belongProduct");
                        }
                    }
                }
            }
        }
        return null;
    }


    public int getResCount() {
        return ipManageMapper.getIpCount();
    }

    public void deleteIps(JSONArray jsonArray) {
        String[] ips = jsonArray.toArray(new String[jsonArray.size()]);
        for (String ip : ips) {
            ipManageMapper.deleteIp(ip);
        }
    }

    public void updateIp(JSONObject obj) {
        IpManage ipManage = JSONObject.toJavaObject(obj, IpManage.class);
        ipManage.setLastModified(new Date());
        ipManageMapper.updateIp(ipManage);
    }

    public void addIpManageByExcel(IpManage ipManage) {
        if (ipManage.getIp() != null) {
            QueryWrapper<IpManage> queryWrapper = new QueryWrapper();
            queryWrapper.eq("ip", ipManage.getIp());
            IpManage temp = ipManageMapper.selectOne(queryWrapper);
            if (temp == null) {
                ipManage.setResId(IdUtils.generatedUUID());
                ipManage.setLastModified(new Date());
                ipManageMapper.addIp(ipManage);
            } else {
                ipManage.setResId(temp.getResId());
                if (!temp.equals(ipManage)) {
                    ipManage.setLastModified(new Date());
                    ipManageMapper.updateIp(ipManage);
                }
            }
        } else {
            throw new IllegalArgumentException("缺少IP");
        }
    }

    //通过查询OC获取DHCP等特殊IP
    public void updateDhcp() {
        JSONArray subnets = ocService.getAllInstanceInfo(subnetClass);
        for (int i = 0; i < subnets.size(); i++) {
            JSONObject subnet = subnets.getJSONObject(i);
            String condition = "[{\"simple\":{\"name\":\"subnetId\",\"value\":\"" + subnet.getString("id") + "\",\"operator\":\"equal\"}}]";
            JSONArray portIds = ocService.getAllRelationWithCondition(relationName, condition);
            for (int j = 0; j < portIds.size(); j++) {
                String portId = portIds.getJSONObject(j).getString("portId");
                String ip = portIds.getJSONObject(j).getString("ipAddress");
                String port = ocService.getOcClassInstances(portClass, portId);
                String deviceOwner = JSONObject.parseObject(port).getString("deviceOwner");
                if (!deviceOwner.contains("compute")) {
                    JSONObject remark = new JSONObject();
                    remark.put("logicalRegionName", subnet.getString("logicalRegionName"));
                    remark.put("vdcName", subnet.getString("vdcName"));
                    remark.put("deviceOwner", deviceOwner);
                    IpManage ipManage = new IpManage();
                    ipManage.setIp(ip);
                    ipManage.setAllocateStatus("已分配");
                    ipManage.setRemark(remark.toJSONString());
                    ipManageMapper.updateByIp(ipManage);
                }
            }
        }
    }

    //更新ip_manage表中的belongProduct字段
    public void updateBelongProduct() {
        List<PhysicalHost> physicalHostRes = physicalHostMapper.getAllInstance(null);
        for (PhysicalHost res : physicalHostRes) {
            LambdaUpdateWrapper<IpManage> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(IpManage::getIp, res.getMainIp())
                    .isNull(IpManage::getBelongProduct)
                    .set(IpManage::getBelongProduct, res.getBelongProduct());
            ipManageMapper.update(null, updateWrapper);
        }

        List<PhysicalMachine> physicalMachineRes = physicalMachineMapper.getAllInstance(null);
        for (PhysicalMachine res : physicalMachineRes) {
            LambdaUpdateWrapper<IpManage> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(IpManage::getIp, res.getMainIp())
                    .isNull(IpManage::getBelongProduct)
                    .set(IpManage::getBelongProduct, res.getBelongProduct());
            ipManageMapper.update(null, updateWrapper);
        }

        List<CloudVmare> cloudVmNovaRes = cloudVmNovaMapper.getAllInstance(null);
        for (CloudVmare res : cloudVmNovaRes) {
            LambdaUpdateWrapper<IpManage> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(IpManage::getIp, res.getMainIp())
                    .isNull(IpManage::getBelongProduct)
                    .set(IpManage::getBelongProduct, res.getProjectName());
            ipManageMapper.update(null, updateWrapper);
        }
    }

    public JSONObject getSortCount() {
        JSONObject result = new JSONObject();
        List<HashMap> allocateStatus = ipManageMapper.getAllocateStatusCount();
        List<HashMap> exploratoryState = ipManageMapper.getExploratoryStateCount();
        List<HashMap> useOrg = ipManageMapper.getUseOrgCount();
        result.put("allocateStatus", JSONArray.parseArray(JSON.toJSONString(allocateStatus)));
        result.put("exploratoryState", JSONArray.parseArray(JSON.toJSONString(exploratoryState)));
        result.put("useOrg", JSONArray.parseArray(JSON.toJSONString(useOrg)));
        return result;
    }
}
