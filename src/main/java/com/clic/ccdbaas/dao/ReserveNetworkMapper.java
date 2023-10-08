package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.ReserveNetwork;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReserveNetworkMapper extends BaseMapper<ReserveNetwork> {
    List<ReserveNetwork> getAllInstance(ReserveNetwork reserveNetwork);
}