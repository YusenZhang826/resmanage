package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.McPhysicalHost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface McPhysicalHostMapper extends BaseMapper<McPhysicalHost> {

    void deletePhysicalHostByIp(String mainIp);

    McPhysicalHost getPhysicalHostByIp(String mainIp);

    void addMcPhysicalHost(McPhysicalHost mc);
}
