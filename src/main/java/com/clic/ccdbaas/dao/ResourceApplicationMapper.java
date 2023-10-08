package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.ResourceApplication;
import com.clic.ccdbaas.entity.kafka.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceApplicationMapper {
    List<ResourceApplication> getAllInstance(ResourceApplication resourceApplication);

    List<Map<String, Object>> getProducerSendMessage(String workOrder);

    List<ResourceApplication> selectResourceApplicationByWorkOrder(String workOrder);

    int insertResourceApplication(Map<String, Object> map);

    int updateResourceApplication(Map<String, Object> map);

    String selectResourceApplicationStatusByNativeId(String nativeId);

    void updateStatus(Map<String,String>  json);

    void updateCount(Map<String,String>  json);

    int checkStatusByworkOrder(String workOrder);

    int deleteResourceApplication(String workOrder);

    int insertSingleResourceApplicationMessage(ResourceApplication resourceApplication);

    int insertSingleResourceRecyclingMessage(ResourceRecycling resourceRecycling);

    ResourceApplication getResourceApplicationInfoByResId(String resId);

    ResourceRecycling getResourceRecyclingInfoByWorkOrder(ResourceRecycling resourceRecycling);
}
