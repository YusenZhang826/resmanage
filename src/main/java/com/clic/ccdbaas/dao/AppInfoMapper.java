package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.AppInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AppInfoMapper {
    int deleteByPrimaryKey(String resId);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);
    List<AppInfo> getAllInstance(AppInfo record);

    AppInfo selectByPrimaryKey(String resId);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);
}