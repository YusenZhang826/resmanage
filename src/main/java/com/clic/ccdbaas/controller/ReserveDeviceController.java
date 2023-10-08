package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.*;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.ReserveDeviceService;
import com.clic.ccdbaas.service.ResourceSearchService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import com.clic.ccdbaas.utils.sql.CountByGroupSearch;
import com.clic.ccdbaas.utils.sql.FuzzySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = {"/v1/reserveDevice"})
public class ReserveDeviceController extends CloudBaseController {

    @Autowired
    ReserveDeviceService reserveDeviceService;

    @Autowired
    ResourceSearchService resourceSearchService;

    /**
     * 获取全部库存服务器
     */
    @GetMapping(value = "/getReserveServerByPage")
    public TableDataInfo getReserveServerByPage(ReserveServer reserveServer, Boolean isRunning) {
        startPage();
        List<ReserveServer> reserveServers = reserveDeviceService.getServerList(reserveServer, isRunning);
        return getDataTable(reserveServers);
    }

    @PostMapping("/exportReserveServers")
    public void exportReserveServers(HttpServletResponse response, ReserveServer reserveServer, Boolean isRunning) {
        List<ReserveServer> reserveServers = reserveDeviceService.getServerList(reserveServer, isRunning);
        ExcelUtil<ReserveServer> util = new ExcelUtil<>(ReserveServer.class);
        util.exportExcel(response, reserveServers, "库存服务器");
    }

    @PostMapping(value = "/exportReserveServerByIds")
    public void exportReserveServers(HttpServletResponse response, @RequestBody List<String> ids) {
        List<ReserveServer> reserveServers = reserveDeviceService.getReserveServerByIds(ids);
        ExcelUtil<ReserveServer> util = new ExcelUtil<>(ReserveServer.class);
        util.exportExcel(response, reserveServers, "库存服务器");
    }


    /**
     * 根据resId获取库存服务器详情
     */
    @GetMapping(value = "/getServerInstanceById")
    public JsonResult getServerInstanceById(String id) {
        ReserveServer reserveServer = reserveDeviceService.getServerInstanceById(id);
        return renderSuccess(reserveServer);
    }

    /**
     * 根据服务器id查询绑定磁盘
     */
    @GetMapping(value = "/getDiskByServerId")
    public TableDataInfo getDiskByServerId(String id) {
        startPage();
        List<ReserveDisk> reserveDisk = reserveDeviceService.getDiskByServerId(id);
        return getDataTable(reserveDisk);
    }

    /**
     * 新增库存服务器
     */
    @PostMapping("/addReserveServer")
    public JsonResult addReserveServer(@RequestBody JSONObject reserveServer) {
        try {
            ReserveServer server = reserveServer.toJavaObject(ReserveServer.class);
            reserveDeviceService.addReserveServer(server);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("插入库存服务器失败");
        }
        return renderSuccess();
    }

    /**
     * 获取全部库存磁盘
     */
    @GetMapping(value = "/getReserveDiskByPage")
    public TableDataInfo getReserveDiskByPage(ReserveDisk reserveDisk) {
        startPage();
        List<ReserveDisk> reserveDisks = reserveDeviceService.getDiskList(reserveDisk);
        return getDataTable(reserveDisks);
    }

    /**
     * 新增库存磁盘
     */
    @PostMapping("/addReserveDisk")
    public JsonResult addReserveDisk(@RequestBody ReserveDisk reserveDisk) {
        try {
            reserveDeviceService.addReserveDisk(reserveDisk);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("添加库存磁盘失败");
        }
        return renderSuccess();
    }


    //查询接口
    @CrossOrigin
    @PostMapping(value = "/getAllReserveDevice")
    public JSONArray getReserveDeviceList(@RequestBody JSONObject requestParams) {
        if (!requestParams.isEmpty()) {
            return reserveDeviceService.getReserveDevice(requestParams);
        }
        return reserveDeviceService.getReserveDevice();
    }

    //添加单个或多个库存设备
    @CrossOrigin
    @RequestMapping(value = "/addReserveDevice")
    public JsonResult addReserveDevice(@RequestBody JSONArray requestParams) {
        try {
            reserveDeviceService.addReserveDevice(requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError(e.getMessage());
        }
        return renderSuccess("添加库存设备成功！");
    }

    /**
     * 删除库存服务器
     */
    @PostMapping(value = "/deleteServer")
    public JsonResult deleteServer(@RequestBody JSONArray servers) {
        try {
            reserveDeviceService.deleteServer(servers);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("删除库存服务器失败");
        }
        return renderSuccess("删除库存服务器成功");
    }

    /**
     * 删除库存磁盘
     */
    @PostMapping(value = "/deleteDisk")
    public JsonResult deleteDisk(@RequestBody JSONArray disks) {
        try {
            reserveDeviceService.deleteDisk(disks);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("删除库存磁盘失败");
        }
        return renderSuccess("删除库存磁盘成功");
    }


    @PostMapping(value = "/uploadReserveServerFile")
    public JsonResult uploadReserveServerFile(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<ReserveServer> readListener = new BaseReadByLineListener<ReserveServer>() {
            @Override
            public void process(ReserveServer reserveServer) {
                reserveDeviceService.addReserveServer(reserveServer);
            }
        };
        return renderUploadExcelFileResult(file, readListener, ReserveServer.class);
    }

    /**
     * 更新库存服务器
     */
    @PostMapping(value = "/updateReserveServerById")
    public JsonResult updateReserveServerById(@RequestBody List<ReserveServer> servers) {
        reserveDeviceService.updateReserveServersByIdOrSn(servers);
        return renderSuccess("更新成功");
    }

    /**
     * 更新库存磁盘
     */
    @PostMapping(value = "/updateReserveDiskById")
    public JsonResult updateReserveDiskById(@RequestBody JSONArray disks) {
        try {
            reserveDeviceService.updateReserveDiskById(disks);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("更新库存磁盘失败");
        }
        return renderSuccess("更新库存磁盘成功");
    }

    @GetMapping(value = "/getAllReserveServersCount")
    public JsonResult getAllReserveServersCount(Boolean isRunning) {
        return renderSuccess(reserveDeviceService.getReserveServerAssortCount(isRunning));
    }

    @PostMapping(value = "/uploadUpdateReserveServerFile")
    public JsonResult uploadUpdateReserveServerFile(@RequestParam(value = "file") MultipartFile file, Boolean isShelf) {
        BaseReadByLineListener<ReserveServer> readListener = new BaseReadByLineListener<ReserveServer>() {
            @Override
            public void process(ReserveServer reserveServer) {
                reserveDeviceService.updateReserveServerBySn(reserveServer, isShelf);
            }
        };
        return renderUploadExcelFileResult(file, readListener, ReserveServer.class);
    }

    @GetMapping(value = "/getAllReserveJumpers")
    public JsonResult getAllReserveJumpers() {
        startPage();
        return renderSuccess(reserveDeviceService.getAllReserveJumpers());
    }

    @PostMapping(value = "/uploadReserveJumperFile")
    public JsonResult uploadReserveJumperFile(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<ReserveJumper> readListener = new BaseReadByLineListener<ReserveJumper>() {
            @Override
            public void process(ReserveJumper reserveJumper) {
                reserveDeviceService.addReserveJumper(reserveJumper);
            }
        };
        return renderUploadExcelFileResult(file, readListener, ReserveJumper.class);
    }

    @GetMapping(value = "/getReserveStorageByPage")
    public TableDataInfo getAllReserveStorageByPage(ReserveStorage reserveStorage, Boolean isRunning) {
        startPage();
        return getDataTable(reserveDeviceService.getReserveStorages(reserveStorage, isRunning));
    }

    @PostMapping(value = "/uploadReserveStorageFile")
    public JsonResult uploadReserveStorageFile(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<ReserveStorage> readListener = new BaseReadByLineListener<ReserveStorage>() {
            @Override
            public void process(ReserveStorage reserveStorage) {
                reserveDeviceService.addReserveStorage(reserveStorage);
            }
        };
        return renderUploadExcelFileResult(file, readListener, ReserveStorage.class);
    }

    @GetMapping(value = "/getAllReserveStoragesCount")
    public JsonResult getAllReserveStoragesCount(Boolean isRunning) {
        return renderSuccess(reserveDeviceService.getReserveStorageCountInfo(isRunning));
    }

    @GetMapping(value = "/getReserveSanByPage")
    public TableDataInfo getAllReserveSanByPage(ReserveSan reserveSan, Boolean isRunning) {
        startPage();
        return getDataTable(reserveDeviceService.getReserveSans(reserveSan, isRunning));
    }

    @PostMapping(value = "/uploadReserveSanFile")
    public JsonResult uploadReserveSanFile(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<ReserveSan> readListener = new BaseReadByLineListener<ReserveSan>() {
            @Override
            public void process(ReserveSan reserveSan) {
                reserveDeviceService.addReserveSan(reserveSan);
            }
        };
        return renderUploadExcelFileResult(file, readListener, ReserveSan.class);
    }

    @PostMapping(value = "/deleteReserveSanByIds")
    public JsonResult deleteReserveSanByIds(@RequestBody List<String> ids) {
        reserveDeviceService.deleteReserveSanByIds(ids);
        return renderSuccess();
    }

    @PostMapping(value = "/deleteReserveStorageByIds")
    public JsonResult deleteReserveStorageByIds(@RequestBody List<String> ids) {
        reserveDeviceService.deleteReserveStorageByIds(ids);
        return renderSuccess();
    }

    @PostMapping(value = "/exportReserveStorageByIds")
    public void exportReserveStorages(HttpServletResponse response, @RequestBody List<String> ids) {
        List<ReserveStorage> reserveStorages = reserveDeviceService.getReserveStorageByIds(ids);
        ExcelUtil<ReserveStorage> util = new ExcelUtil<>(ReserveStorage.class);
        util.exportExcel(response, reserveStorages, "存储设备");
    }

    @PostMapping(value = "/exportReserveStorages")
    public void exportReserveStorages(HttpServletResponse response, ReserveStorage reserveStorage, Boolean isRunning) {
        List<ReserveStorage> reserveStorages = reserveDeviceService.getReserveStorages(reserveStorage, isRunning);
        ExcelUtil<ReserveStorage> util = new ExcelUtil<>(ReserveStorage.class);
        util.exportExcel(response, reserveStorages, "存储设备");
    }

    @PostMapping(value = "/exportReserveSanByIds")
    public void exportReserveSans(HttpServletResponse response, @RequestBody List<String> ids) {
        List<ReserveSan> reserveSans = reserveDeviceService.getReserveSanByIds(ids);
        ExcelUtil<ReserveSan> util = new ExcelUtil<>(ReserveSan.class);
        util.exportExcel(response, reserveSans, "SAN交换机");
    }

    @PostMapping(value = "/exportReserveSans")
    public void exportReserveSans(HttpServletResponse response, ReserveSan reserveSan, Boolean isRunning) {
        List<ReserveSan> reserveSans = reserveDeviceService.getReserveSans(reserveSan, isRunning);
        ExcelUtil<ReserveSan> util = new ExcelUtil<>(ReserveSan.class);
        util.exportExcel(response, reserveSans, "SAN交换机");
    }

    @GetMapping(value = "/getReserveSanClassInfo")
    public JsonResult getReserveSanClassInfo() {
        return renderClassInfo(ReserveSan.class);
    }

    @GetMapping(value = "/getReserveStorageClassInfo")
    public JsonResult getReserveStorageClassInfo() {
        return renderClassInfo(ReserveStorage.class);
    }

    @GetMapping(value = "/getReserveJumperClassInfo")
    public JsonResult getReserveJumperClassInfo() {
        return renderClassInfo(ReserveJumper.class);
    }

    @GetMapping(value = "/getReserveServerClassInfo")
    public JsonResult getReserveServerClassInfo() {
        return renderClassInfo(ReserveServer.class);
    }

    @PostMapping(value = "/advancedSearchByPage")
    public TableDataInfo advancedSearchByPage(@RequestBody AdvancedSearch advancedSearch) {
        startPage();
        return getDataTable(resourceSearchService.advancedSearchInstance(advancedSearch));
    }

    @GetMapping(value = "/open/getReserveServerBySn")
    public JsonResult getReserveServerBySn(String sn) {
        ReserveServer reserveServer = reserveDeviceService.getReserveServerBySn(sn);
        return reserveServer == null ? renderError(String.format("sn号为%s的设备不存在", sn)) : renderSuccess(reserveServer);
    }

    @GetMapping(value = "/open/getReserveServerByResAllocationCode")
    public JsonResult getReserveServerByresAllocationCode(String resAllocationCode) {
        if (resAllocationCode.isEmpty()) return renderError("resAllocationCode为必填项");
        List<?> list = reserveDeviceService.getReserveServerByresAllocationCode(resAllocationCode);
        return list.isEmpty() ? renderError(String.format("资源分配代码为%s的设备不存在", resAllocationCode)) : renderSuccess(list);
    }

    @GetMapping(value = "/getCentralizedStorageRegionCount")
    public JsonResult getCentralizedStorageRegionCount() {
        return renderSuccess(reserveDeviceService.getCentralizedStorageRegionCountTableInfo());
    }

    @GetMapping(value = "/getDistributedStorageRegionCount")
    public JsonResult getDistributedStorageRegionCount() {
        return renderSuccess(reserveDeviceService.getDistributedStorageRegionCountTableInfo());
    }

    @GetMapping(value = "/getCentralizedStorageRegionCapacity")
    public JsonResult getCentralizedStorageRegionCapacity() {
        return renderSuccess(reserveDeviceService.getCentralizedStorageRegionCapacityTableInfo());
    }

    @GetMapping(value = "/getDistributedStorageRegionCapacity")
    public JsonResult getDistributedStorageRegionCapacity() {
        return renderSuccess(reserveDeviceService.getDistributedStorageRegionCapacityTableInfo());
    }

    @PostMapping(value = "/updateReserveStorage")
    public JsonResult updateReserveStorageById(@RequestBody ReserveStorage reserveStorage) {
        reserveDeviceService.updateReserveStorageById(reserveStorage);
        return renderSuccess();
    }

    @PostMapping(value = "/updateReserveStorageByIds")
    public JsonResult updateReserveStorageByIds(@RequestBody List<ReserveStorage> reserveStorageList) {
        reserveDeviceService.updateReserveStorageByIds(reserveStorageList);
        return renderSuccess();
    }

    @PostMapping(value = "/fuzzySearchByPage")
    public TableDataInfo fuzzySearchByPage(@RequestBody FuzzySearch fuzzySearch) {
        startPage();
        return getDataTable(reserveDeviceService.fuzzySearchReserveDevice(fuzzySearch));
    }

    @PostMapping(value = "/countByGroupSearch")
    public JsonResult countByGroupSearch(@RequestBody CountByGroupSearch countByGroupSearch) {
        return renderSuccess(resourceSearchService.countSearchInstance(countByGroupSearch));
    }

    @PostMapping(value = "/uploadPhysicalTapeFile")
    public JsonResult uploadPhysicalTapeFile(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<PhysicalTape> readListener = new BaseReadByLineListener<PhysicalTape>() {
            @Override
            public void process(PhysicalTape physicalTape) {
                if (StringUtils.isEmpty(physicalTape.getName())) {
                    physicalTape.setName(physicalTape.getSn());
                }
                reserveDeviceService.insertPhysicalTape(physicalTape);
            }
        };
        return renderUploadExcelFileResult(file, readListener, PhysicalTape.class);
    }

    @PostMapping(value = "/uploadVirtualTapeFile")
    public JsonResult uploadVirtualTapeFile(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<VirtualTape> readListener = new BaseReadByLineListener<VirtualTape>() {
            @Override
            public void process(VirtualTape virtualTape) {
                if (StringUtils.isEmpty(virtualTape.getName())) {
                    virtualTape.setName(virtualTape.getSn());
                }
                reserveDeviceService.insertVirtualTape(virtualTape);
            }
        };
        return renderUploadExcelFileResult(file, readListener, VirtualTape.class);
    }

    @PutMapping(value = "/physicalTape")
    public JsonResult updatePhysicalType(@RequestBody PhysicalTape PhysicalTape) {
        reserveDeviceService.updatePhysicalTape(PhysicalTape);
        return renderSuccess();
    }

    @PutMapping(value = "/virtualTape")
    public JsonResult updateVirtualType(@RequestBody VirtualTape virtualTape) {
        reserveDeviceService.updateVirtualTape(virtualTape);
        return renderSuccess();
    }

    @DeleteMapping(value = "/physicalTape")
    public JsonResult deletePhysicalType(@RequestBody PhysicalTape PhysicalTape) {
        reserveDeviceService.deletePhysicalTape(PhysicalTape);
        return renderSuccess();
    }

    @DeleteMapping(value = "/virtualTape")
    public JsonResult deleteVirtualType(@RequestBody VirtualTape virtualTape) {
        reserveDeviceService.deleteVirtualTape(virtualTape);
        return renderSuccess();
    }

    @PostMapping(value = "/uploadReserveSafeByExcel")
    public JsonResult uploadReserveSafeByExcel(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<ReserveSafe> readListener = new BaseReadByLineListener<ReserveSafe>() {
            @Override
            public void process(ReserveSafe reserveSafe) {
                if (!reserveSafe.getName().equals("请给设备起个名字")) {
                    reserveDeviceService.uploadReserveSafeByExcel(reserveSafe);
                }
            }
        };
        return renderUploadExcelFileResult(file, readListener, ReserveSafe.class);
    }

    @GetMapping(value = "/getAllReserveSafeInstance")
    public TableDataInfo getAllReserveSafeInstance(ReserveSafe reserveSafe) {
        startPage();
        List<ReserveSafe> reserveSafeList = reserveDeviceService.getReserveSafe(reserveSafe);
        return getDataTable(reserveSafeList);
    }

    @PostMapping(value = "/addReserveSafe")
    public JsonResult addReserveSafe(@RequestBody ReserveSafe reserveSafe) {
        try {
            reserveDeviceService.addReserveSafe(reserveSafe);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("添加库存安全设备失败");
        }
        return renderSuccess("添加库存安全设备成功");
    }

    @PostMapping(value = "/updateReserveSafe")
    public JsonResult updateReserveSafe(@RequestBody List<ReserveSafe> reserveSafeList) {
        reserveDeviceService.updateReserveSafe(reserveSafeList);
        return renderSuccess("更新成功");
    }

    @PostMapping(value = "/deleteReserveSafe")
    public JsonResult deleteReserveSafe(@RequestBody List<ReserveSafe> reserveSafeList) {
        reserveDeviceService.deleteReserveSafe(reserveSafeList);
        return renderSuccess("删除成功");
    }

    @PostMapping(value = "/uploadReserveNetworkByExcel")
    public JsonResult uploadReserveNetworkByExcel(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<ReserveNetwork> readListener = new BaseReadByLineListener<ReserveNetwork>() {
            @Override
            public void process(ReserveNetwork reserveNetwork) {
                if (!StringUtils.isEmpty(reserveNetwork.getName()) && !reserveNetwork.getName().equals("设备名称")) {
                    reserveDeviceService.uploadReserveNetworkByExcel(reserveNetwork);
                }
            }
        };
        return renderUploadExcelFileResult(file, readListener, ReserveNetwork.class);
    }

    @GetMapping(value = "/getAllReserveNetworkInstance")
    public TableDataInfo getAllReserveNetworkInstance(ReserveNetwork reserveNetwork) {
        startPage();
        List<ReserveNetwork> reserveNetworkList = reserveDeviceService.getReserveNetwork(reserveNetwork);
        return getDataTable(reserveNetworkList);
    }

    @PostMapping(value = "/deleteReserveNetwork")
    public JsonResult deleteReserveNetwork(@RequestBody List<ReserveNetwork> reserveNetworkList) {
        reserveDeviceService.deleteReserveNetwork(reserveNetworkList);
        return renderSuccess("删除成功");
    }

    @PostMapping(value = "/updateReserveNetwork")
    public JsonResult updateReserveNetwork(@RequestBody List<ReserveNetwork> reserveNetworkList) {
        reserveDeviceService.updateReserveNetwork(reserveNetworkList);
        return renderSuccess("更新成功");
    }

    @GetMapping(value = "/getAllReserveNetworkCount")
    public JsonResult getAllReserveNetworkCount() {
        return renderSuccess(reserveDeviceService.getAllReserveNetworkCount());
    }

    @PostMapping(value = "/updateReserveSanByIds")
    public JsonResult updateReserveSanByIds(@RequestBody List<ReserveSan> reserveSanList) {
        reserveDeviceService.updateReserveSanByIds(reserveSanList);
        return renderSuccess();
    }

    @PostMapping(value = "/uploadSynchroReserveServerFile")
    public JsonResult uploadSynchroReserveServerFile(@RequestParam(value = "file") MultipartFile file, Boolean updateWhenDuplicateSn) {
        System.out.println(updateWhenDuplicateSn);
        BaseReadByLineListener<ReserveServer> readListener = new BaseReadByLineListener<ReserveServer>() {
            @Override
            public void process(ReserveServer reserveServer) {
                reserveDeviceService.uploadSynchroReserveServer(reserveServer, updateWhenDuplicateSn);
            }
        };
        return renderUploadExcelFileResult(file, readListener, ReserveServer.class);
    }

    @GetMapping(value = "/getReserveNetworkById")
    public JsonResult getReserveNetworkById(String resId) {
        ReserveNetwork reserveNetwork = reserveDeviceService.getReserveNetworkById(resId);
        return renderSuccess(reserveNetwork);
    }
}
