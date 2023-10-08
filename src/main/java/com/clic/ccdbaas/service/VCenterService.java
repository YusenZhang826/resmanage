package com.clic.ccdbaas.service;

import com.clic.ccdbaas.dao.VCenterMapper;
import com.clic.ccdbaas.entity.VCenter;
import com.clic.ccdbaas.utils.AESUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VCenterService {
    @Autowired
    VCenterMapper vCenterMapper;

    public void createVCenter(VCenter vCenter) {
        if (Strings.isBlank(vCenter.getSecret())) {
            String secret = AESUtils.encrypt(vCenter.getPassword());
            vCenter.setSecret(secret);
        }
        vCenterMapper.insert(vCenter);
    }
}
