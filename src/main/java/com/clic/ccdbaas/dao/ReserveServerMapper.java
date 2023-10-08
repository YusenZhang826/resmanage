package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.ReserveServer;
import com.clic.ccdbaas.entity.example.ReserveServerExample;
import com.clic.ccdbaas.entity.kafka.ReserveServerKafka;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveServerMapper extends BaseMapper<ReserveServer> {
    int countByExample(ReserveServerExample example);

    int deleteByExample(ReserveServerExample example);

    int deleteByPrimaryKey(String resId);

    int insert(ReserveServer record);

    int insertSelective(ReserveServer record);

    List<ReserveServer> selectByExample(ReserveServerExample example);

    ReserveServer selectByPrimaryKey(String resId);

    int updateByExampleSelective(@Param("record") ReserveServer record, @Param("example") ReserveServerExample example);

    int updateByExample(@Param("record") ReserveServer record, @Param("example") ReserveServerExample example);

    int updateByPrimaryKeySelective(ReserveServer record);

    int updateByPrimaryKey(ReserveServer record);

    int updateReserveServerKafka(ReserveServerKafka reserveServerKafka);

    ReserveServerKafka getSingleReserveServerInstanceBySn(String sn);

    int updateReserveServerResourceRecycling(ReserveServerKafka reserveServerKafka);
}