package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import com.clic.ccdbaas.utils.sql.CountByGroupSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceSearchMapper {
    List<Map<String, Object>> selectByAdvancedSearch(AdvancedSearch advancedSearch);

    List<Map<String, Object>> countByGroupSearch(CountByGroupSearch countByGroupSearch);

    Integer countByAdvancedSearch(AdvancedSearch advancedSearch);
}
