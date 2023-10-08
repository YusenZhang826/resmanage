package com.clic.ccdbaas;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clic.ccdbaas.Annotation.EnumNote;
import com.clic.ccdbaas.conf.AESEncryptor;
import com.clic.ccdbaas.dao.*;
import com.clic.ccdbaas.entity.*;
import com.clic.ccdbaas.entity.example.ReserveJumperExample;
import com.clic.ccdbaas.entity.example.ReserveSanExample;
import com.clic.ccdbaas.enums.ResourceStatus;
import com.clic.ccdbaas.service.*;
import com.clic.ccdbaas.utils.AESUtils;
import com.clic.ccdbaas.utils.RedisClientUtil;
import com.clic.ccdbaas.utils.file.FileUtils;
import com.clic.ccdbaas.utils.sql.Condition;
import com.clic.ccdbaas.utils.sql.CountByGroupSearch;
import com.clic.ccdbaas.utils.sql.SqlUtil;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTests {
    @Autowired
    ReserveDeviceService reserveDeviceService;

    @Autowired
    CloudVmareService cloudVmareService;
    @Autowired
    ReserveJumperMapper reserveJumperMapper;
    @Autowired
    ReserveSanMapper reserveSanMapper;
    @Autowired
    ReserveDeviceMapper reserveDeviceMapper;
    @Autowired
    ReserveStorageMapper reserveStorageMapper;
    @Autowired
    ReserveServerMapper reserveServerMapper;
    @Autowired
    ResourceSearchService resourceSearchService;
    @Autowired
    CmdbService cmdbService;
    @Autowired
    OcService ocService;
    @Autowired
    PhysicalTapeMapper physicalTapeMapper;
    @Autowired
    LocalOperLogService localOperLogService;
    @Value("${oc.cmdb.vm}")
    private String vm;

    @Test
    public void DeviceTest() {
        JSONArray jarray = new JSONArray();
        JSONObject jb = new JSONObject();
        jb.put("name", "test1");
        jb.put("fanNum", "4");
        jarray.add(jb);
        reserveDeviceService.addReserveDevice(jarray);
        System.out.println(reserveDeviceService.getReserveDevice());
    }

    @Test
    public void VmTest() {
        JSONObject requestParams = new JSONObject();
        cloudVmareService.exportCloudVmare(requestParams);
    }

    @Test
    public void testGetReserveServerCount() {
        System.out.println(reserveDeviceService.getReserveServerAssortCount(true));
        System.out.println(reserveDeviceService.getReserveServerAssortCount(false));
        System.out.println(reserveDeviceService.getReserveServerAssortCount(null));
    }

    @Test
    public void testGetReserveJumperById() {
        ReserveJumperExample reserveJumperExample = new ReserveJumperExample();
        ReserveJumperExample.Criteria criteria = reserveJumperExample.createCriteria();
        criteria.andResIdEqualTo("");
        System.out.println(reserveJumperMapper.selectByExample(reserveJumperExample));
    }

    @Test
    public void testGetReserveSan() {
        ReserveSan reserveSan = new ReserveSan();
        reserveSan.setResourceStatus(ResourceStatus.RESERVE);
        reserveSan.setPhysicalPosition("上海卡园");
        ReserveSanExample reserveSanExample = new ReserveSanExample();
        ReserveSanExample.Criteria criteria = reserveSanExample.createCriteria();
        criteria.andSnLike("%HC5%");
        System.out.println(reserveSanMapper.selectByExample(reserveSanExample));
    }

    @Test
    public void testGetReserveStorage() {
        ReserveStorage reserveStorage = new ReserveStorage();
        reserveStorage.setPhysicalPosition("北京科技园");
        List<ReserveStorage> reserveStorageList = reserveDeviceService.getReserveStorages(reserveStorage, null);
        for (ReserveStorage reserveStorage1 : reserveStorageList) {
            System.out.println(reserveStorage1.getUpdateTime());
            System.out.println(reserveStorage1.getResourceStatus());
        }
    }

    @Test
    public void getReserveStorageCapacitySum() {
        ReserveStorage reserveStorage = new ReserveStorage();
        reserveStorage.setType1("分布式");
        reserveStorage.setType2("块");
        System.out.println(reserveDeviceMapper.getReserveStorageCapacitySum(reserveStorage));
    }

    @Test
    public void aesTest() {
        String txt = "@(&XMIhU99_9in{h_";
        AESEncryptor aesEncryptor = new AESEncryptor();
//        String enTxt = aesEncryptor.encrypt(txt);
        String enTxt = AESUtils.encrypt(txt);
        System.out.println(enTxt);
        System.out.println(AESUtils.decrypt(enTxt));
    }

    @Test
    public void txt2Str() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\新建文本文档 (2).txt";
        String content = FileUtils.txt2String(fileName, "UTF-8");
        System.out.println(content);
    }

    @Test
    public void createReserveStorage() throws InterruptedException {
        ReserveStorage reserveStorage = new ReserveStorage();
        reserveStorage.setResourceStatus(ResourceStatus.REMOVED);
        String resId = IdUtils.generatedUUID();
        System.out.println(resId);
        reserveStorage.setResId(resId);
        reserveStorage.setSn(IdUtils.generatedUUID());
        reserveDeviceService.addReserveStorage(reserveStorage);
        ReserveStorage temp = reserveDeviceService.getReserveStorages(reserveStorage, null).get(0);
        Assert.assertSame(temp.getResourceStatus(), reserveStorage.getResourceStatus());
    }

    @Test
    public void testFuzzySearch() {
        QueryWrapper<ReserveStorage> queryWrapperReserveStorage = SqlUtil.fuzzySearchFullField(ReserveStorage.class, "研发测试云", new ArrayList<>(Arrays.asList("physicalPosition", "manufacturer")));
        System.out.println(reserveStorageMapper.selectList(queryWrapperReserveStorage));
        QueryWrapper<ReserveServer> queryWrapperReserveServer = SqlUtil.fuzzySearchFullField(ReserveServer.class, "北京", null);
        System.out.println(reserveServerMapper.selectList(queryWrapperReserveServer));
        QueryWrapper<ReserveSan> queryWrapperReserveSan = SqlUtil.fuzzySearchFullField(ReserveSan.class, "北京", null);
        System.out.println(reserveSanMapper.selectList(queryWrapperReserveSan));
    }

    @Test
    public void testCountByGroupSearch() {
        CountByGroupSearch countByGroupSearch = new CountByGroupSearch();
        countByGroupSearch.setClassName("ReserveServer");
        countByGroupSearch.setSearchedField("allocateStatus");
        List<Condition> conditions = new ArrayList<>();
        countByGroupSearch.setConditions(conditions);
        System.out.println(resourceSearchService.countSearchInstance(countByGroupSearch));
    }

    @Test
    public void testUploadAllCloudVms() {
        cmdbService.uploadAllCloudVms();
    }

    @Test
    public void testAliasAnnotation() throws NoSuchFieldException {
        Class vmClazz = CloudVmare.class;
        Field name = vmClazz.getDeclaredField("status");
        EnumNote annotation = name.getAnnotation(EnumNote.class);
        System.out.println(annotation.code() + "-" + annotation.value() + "-" + (annotation.code().length == annotation.value().length));

    }

    @Test
    public void testGetClass() throws ClassNotFoundException {
        Class clazz = Class.forName("com.clic.ccdbaas.entity.ReserveSan");
        System.out.println(clazz);
    }

    @Test
    public void testGetOcCloudImageInstance() {
        JSONArray jsonArray = ocService.getAllInstanceInfo(vm);
        List<CloudVmare> cloudVmwareList = JSONObject.parseArray(jsonArray.toJSONString(), CloudVmare.class);
        System.out.println(cloudVmareService.getOcCloudVmInstance(cloudVmwareList));
    }

    @Test
    public void testClass() throws ClassNotFoundException, NoSuchFieldException {
        String searchType = Class.forName("com.clic.ccdbaas.entity.CloudVmare").getDeclaredField("i").getType().getSimpleName();
        System.out.println(searchType);
    }

    @Test
    public void testInsertPhysicalTape() {
        PhysicalTape physicalTape = new PhysicalTape();
        physicalTape.setSn(IdUtils.generatedUUID());
        physicalTape.setPhysicalPosition("123");
        physicalTape.setManufacturer("123");
        physicalTapeMapper.insert(physicalTape);
    }

    @Test
    public void testDateSub() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date firstDate = sdf.parse("2023-07-31 10:23:33");
        Date secondDate = sdf.parse("2023-07-31 12:58:00");
        long diff = secondDate.getTime() - firstDate.getTime();
        TimeUnit time = TimeUnit.HOURS;
        long sub = time.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println(sub);
    }

    @Test
    public void testVCenter() {
        String url = "https://" + "10.15.76.231" + "/sdk";
        try {
            ServiceInstance si = new ServiceInstance(new URL(url), "Administrator@vsphere.local", "Kjy@2020", true);
//            Folder rootFolder = si.getRootFolder();
//            //获取到集群数组
//            ManagedEntity[] ccs = new InventoryNavigator(rootFolder).searchManagedEntities("ClusterComputeResource");
//            ClusterComputeResource cluster = (ClusterComputeResource) ccs[0];
//            Datastore[] datastores = cluster.getDatastores();
//            for (Datastore datastore : datastores) {
//                System.out.println("Capacity for datastore " + datastore.getName() + ": " + datastore.getSummary().capacity + " bytes, rest:" + datastore.getSummary().freeSpace + "bytes");
//            }
            // 获取根文件夹对象
            Folder rootFolder = si.getRootFolder();

// 获取根文件夹中的所有子实体数组
            ManagedEntity[] dataCenters = rootFolder.getChildEntity();
            // 遍历子实体数组
            for (ManagedEntity datacenter : dataCenters) {
                ManagedEntity[] hosts = null;
                Datacenter dc = (Datacenter) datacenter;
                // 获取主机文件夹对象
                Folder hostFolder = dc.getHostFolder();
                ManagedEntity[] clusters = hostFolder.getChildEntity();
                ClusterComputeResource cluster = (ClusterComputeResource) clusters[0];
                hosts = cluster.getHosts();
                for (ManagedEntity host : hosts) {
                    // 判断是否为宿主机类型
                    if (host instanceof HostSystem) {
                        // 获取宿主机对象
                        HostSystem hs = (HostSystem) host;
                        // 获取宿主机配置信息对象
                        HostConfigInfo hci = hs.getConfig();
                        if (hci == null) continue;
                        AboutInfo hpi = hci.getProduct();
                        // 获取宿主机网络信息对象
                        HostNetworkInfo hni = hci.getNetwork();
                        // 获取宿主机虚拟网卡信息数组
                        HostVirtualNic[] hvns = hni.getVnic();
                        // 遍历虚拟网卡信息数组
                        for (HostVirtualNic hvn : hvns) {
                            // 获取虚拟网卡规格对象
                            HostVirtualNicSpec hens = hvn.getSpec();
                            // 获取虚拟网卡 IP 配置对象
                            HostIpConfig hic = hens.getIp();
                            // 获取宿主机 IP 地址
                            String ip = hic.getIpAddress();
                            // 打印宿主机 IP 地址
                            System.out.println("宿主机 IP 地址");
                            System.out.println(ip);
                            VirtualMachine[] vms = hs.getVms();
                            for (ManagedEntity vm : vms) {
                                // 判断是否为虚拟机类型
                                if (vm instanceof VirtualMachine) {
                                    // 获取虚拟机对象
                                    VirtualMachine virtualMachine = (VirtualMachine) vm;
                                    // 获取虚拟机摘要信息对象
                                    VirtualMachineSummary virtualMachineSummary = virtualMachine.getSummary();
                                    // 获取虚拟机配置信息对象
                                    VirtualMachineConfigSummary vmcs = virtualMachineSummary.getConfig();
                                    // 获取虚拟机操作系统全名
                                    String os = vmcs.getGuestFullName();
                                    // 打印虚拟机操作系统全名
                                    System.out.println("虚拟机操作系统全名");
                                    System.out.println(os);
                                    // 获取虚拟机客户机信息对象
                                    GuestInfo gi = virtualMachine.getGuest();
                                    // 获取虚拟机 IP 地址
                                    String vip = gi.getIpAddress();
                                    // 打印虚拟机 IP 地址
                                    System.out.println("虚拟机 IP 地址");
                                    System.out.println(vip);
                                    VirtualMachineRuntimeInfo vmri = virtualMachine.getRuntime();
                                    // 获取虚拟机所在的宿主机对象
                                    ManagedObjectReference mor = vmri.getHost();
                                    // 获取宿主机唯一标识符
                                    String id = mor.getVal();
                                    // 打印虚拟机名称和宿主机唯一标识符
                                    System.out.println("所在宿主机id");
                                    System.out.println(id);
                                }
                            }
                        }
                    }
                }
            }
            // 获取虚拟机文件夹中的所有子实体数组
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void testLocalOperLog() {
        localOperLogService.getStatisticsForWeek();
        RedisClientUtil.jedis.del("getStatisticsForWeek");
    }

}



