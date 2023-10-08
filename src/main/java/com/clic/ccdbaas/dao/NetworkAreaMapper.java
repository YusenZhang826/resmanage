package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.NetworkArea;
import com.clic.ccdbaas.entity.NetworkVlanRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface NetworkAreaMapper {
    int getNetworkAreaCount();

    List<NetworkArea> getAllNetworkAreaInstance(NetworkArea networkArea);

    NetworkArea getNetworkAreaById(String id);

    void deleteNetworkArea(String resId);

    void updateNetworkAreaById(NetworkArea networkArea);

    void addNetworkArea(NetworkArea networkAreaNew);

    String getNetworkVlanRelation(NetworkVlanRelation relation);

    String getNetworkAreaByName(String areaName);

    NetworkVlanRelation getVlanRelation(String vlanId);

    void deleteAreaVlanRelation(String resId);

    void addNetworkVlanRelation(NetworkVlanRelation relation);

    String getNetworkAreaNameById(String resId);

    NetworkVlanRelation getAreaRelationByVlanId(String vlanId);

    void updateAreaNameById(HashMap<String, String> map);

    List<HashMap<String, Object>> getDeployEnvGroupCount();

    List<HashMap<String, Object>> getUserOrgGroupCount();
}
