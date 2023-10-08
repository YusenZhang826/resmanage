package com.clic.ccdbaas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/6/25 10:50
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class TimerService {
    @Autowired
    private RelationDbService relationDbService;
    @Autowired
    private ObTenantService obTenantService;
    @Autowired
    private ObClusterService obClusterService;
    @Autowired
    private CloudVolumeService cloudVolumeService;
    @Autowired
    private CloudVmareService cloudVmareService;
    @Autowired
    private RegistrationProductService productService;

    public void doTask() {
        relationDbService.saveAllRelationDb();
        obTenantService.saveTenInfo();
        obClusterService.saveObCluster();
        cloudVolumeService.saveAllCloudVolume();
        cloudVmareService.getCloudVolumeRelationsInstance();
        productService.updateACSProductInfo();
    }


}
