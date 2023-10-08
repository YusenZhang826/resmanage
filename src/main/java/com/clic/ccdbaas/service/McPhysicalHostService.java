package com.clic.ccdbaas.service;

import com.clic.ccdbaas.dao.McPhysicalHostMapper;
import com.clic.ccdbaas.entity.McPhysicalHost;
import com.clic.ccdbaas.entity.PhysicalHost;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class McPhysicalHostService {
    @Autowired
    private McPhysicalHostMapper mcPhysicalHostMapper;

    public void deletePhysicalHost(PhysicalHost physicalHost) {
        McPhysicalHost mc = mcPhysicalHostMapper.getPhysicalHostByIp(physicalHost.getMainIp());
        mcPhysicalHostMapper.deletePhysicalHostByIp(physicalHost.getMainIp());
        mc.setResId(IdUtils.generatedUUID());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (mc.getLast_Modified() != null) {
            try {
                mc.setLast_Modified(sdf.format(Long.parseLong(mc.getLast_Modified())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mc.setLast_Modified(sdf.format(new Date()));
        }
        mc.setClass_Name("MC_PhysicalHost");
        mcPhysicalHostMapper.addMcPhysicalHost(mc);
    }
}
