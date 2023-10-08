package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.IpManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface IpManageMapper extends BaseMapper<IpManage> {

    List<HashMap<String, String>> getIpsFromVm(String ip);

    List<HashMap<String, String>> getIpsFromPhysicalMachine(String ip);

    List<HashMap<String, String>> getIpsFromHost(String ip);

    List<HashMap<String, String>> selectIpsFromVm();

    List<HashMap<String, String>> selectIpsFromHost();

    List<HashMap<String, String>> selectIpsFromPhysicalMachine();

    List<HashMap> getAllInstance(IpManage ipManage);

    HashMap selectIpByResId(String resId);

    void deleteIp(String resId);

    void updateIp(IpManage ipManage);

    void addIp(IpManage ipManage);

    int getIpCount();

    void updateByIp(IpManage ipManage);

    List<IpManage> getAllIp(IpManage ipManage);

    List<HashMap> getAllocateStatusCount();

    List<HashMap> getExploratoryStateCount();

    List<HashMap> getUseOrgCount();
}