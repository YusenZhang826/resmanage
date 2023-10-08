package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.ReserveStorage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReserveDeviceMapper {
    List<Map> getReserveStorageType1Count(@Param("isRunning") Boolean isRunning);

    List<Map> getReserveStorageType2Count(@Param("isRunning") Boolean isRunning);

    Integer getReserveStorageCount(@Param("isRunning") Boolean isRunning);

    Integer getReserveStorageNonTypeCount(@Param("isRunning") Boolean isRunning);

    List<Map> getReserveServerCount(@Param("isRunning") Boolean isRunning);

    Map<String, BigDecimal> getReserveStorageCapacitySum(ReserveStorage reserveStorage);

    List<Map> getReserveNetworkCount();
}
