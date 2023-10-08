package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.CloudVolume;
import com.clic.ccdbaas.entity.CloudVolumeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CloudVolumeMapper extends BaseMapper {
    int countByExample(CloudVolumeExample example);

    int deleteByExample(CloudVolumeExample example);

    int deleteByPrimaryKey(String resid);

    int insert(CloudVolume record);

    int insertSelective(CloudVolume record);

    List<CloudVolume> selectByExample(CloudVolumeExample example);

    List<CloudVolume> getAllInstance(CloudVolume example);

    List<CloudVmare> getVolumeRelatedVm(String resId);

    List<CloudVolume> getVmVolumes(String resId);


    CloudVolume selectByPrimaryKey(String resid);

    int updateByExampleSelective(@Param("record") CloudVolume record, @Param("example") CloudVolumeExample example);

    int updateByExample(@Param("record") CloudVolume record, @Param("example") CloudVolumeExample example);

    int updateByPrimaryKeySelective(CloudVolume record);

    int updateByPrimaryKey(CloudVolume record);


}