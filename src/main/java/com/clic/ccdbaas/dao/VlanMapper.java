package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.Vlan;
import com.clic.ccdbaas.entity.VlanPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface VlanMapper {
    List<Vlan> getAllVlan();

    int getVlanCount();

    VlanPlan getVlanInfoByResId(String resId);

    List<VlanPlan> getAllVlanInstance(VlanPlan vlanPlan);

    int insertSingleVlanInfo(VlanPlan vlanPlan);

    int deleteSingleVlanInfo(String resId);

    int updateSingleVlanInfo(VlanPlan vlanPlan);

    String getVlanByName(String vlanName);

    String getVlanNameById(String vlanId);

    VlanPlan getVlanInfoByName(String name);

    int updateSingleVlanInfoByName(VlanPlan vlanPlan);

    List<HashMap> getVlanByAreaName(String areaName);

    HashMap getVlanAndAreaInfoByResId(String resId);

    List<VlanPlan> getAllVlanNameAndIpCount(VlanPlan vlanPlan);
}
