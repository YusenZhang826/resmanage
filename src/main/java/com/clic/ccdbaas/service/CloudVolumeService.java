package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.CloudVolumeMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.CloudVolume;
import com.clic.ccdbaas.entity.CloudVolumeExample;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.model.ComparbleResult;
import com.clic.ccdbaas.utils.ComparbleUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/5/22 13:27
 * @email chenjianhua@bmsoft.com.cn
 */
@Service("CLOUD_VOLUME")
public class CloudVolumeService {
    @Autowired
    private CloudVolumeMapper cloudVolumeMapper;
    @Autowired
    private OcService ocService;
    private static final Logger logger = LoggerFactory.getLogger(CloudVolumeService.class);

    /**
     * 保存oc所有的硬盘信息
     */
    @XxlJob("saveAllCloudVolume")
    public void saveAllCloudVolume() {
        //获取hids所有应用信息
        JSONArray clusterArr = ocService.getAllInstanceInfo("CLOUD_VOLUME");
        List<CloudVolume> originalCloudVolumes = cloudVolumeMapper.selectByExample(new CloudVolumeExample());
        Map<String, CloudVolume> localCloudVolumeMap = originalCloudVolumes.stream().collect(Collectors.toMap(k -> k.getResId(), CloudVolume -> CloudVolume));
        //agentId跟name拼接成key
        List<CloudVolume> remoteCloudVolumeList = JSONObject.parseArray(clusterArr.toJSONString(), CloudVolume.class);

        //oc侧云硬盘列表
        Map<String, CloudVolume> remoteClusterMap = remoteCloudVolumeList.stream().collect(Collectors.toMap(k -> k.getResId(), CloudVolume -> CloudVolume));

        //遍历处理磁盘信息
        for (Map.Entry<String, CloudVolume> entry : remoteClusterMap.entrySet()) {
            String remoteResId = entry.getKey();
            CloudVolume remoteCloudVolume = entry.getValue();
            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localCloudVolumeMap.containsKey(remoteResId)) {

                CloudVolume localCloudVolume = localCloudVolumeMap.get(remoteResId);

                ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localCloudVolume, remoteCloudVolume);
                //有字段变更了才更新
                if (comparedResults.size() > 0)
                    cloudVolumeMapper.updateByPrimaryKey(remoteCloudVolume);
                //记录所有变更日志信息
                for (ComparbleResult singleResult : comparedResults) {
                    AsyncManager.me().execute(AsyncFactory.recordOper(remoteCloudVolume.getResId(), "CloudVolume", remoteCloudVolume.getName(),
                            "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                            singleResult.getNewFieldContent(), 0));
                }

            } else {

                cloudVolumeMapper.insert(remoteCloudVolume);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteCloudVolume.getResId(), "CloudVolume", remoteCloudVolume.getName(),
                        "resManage", 1, "All", "",
                        "", 0));
            }


        }


    }

    /**
     * 条件查询单个磁盘信息详情
     *
     * @param resId
     * @return
     */
    public CloudVolume vmDetailInfo(String resId) {
        CloudVolume cloudVolume = new CloudVolume();
        cloudVolume.setResId(resId);
        List<CloudVolume> allInstance = cloudVolumeMapper.getAllInstance(cloudVolume);
        if (allInstance.size() > 0) {
            CloudVolume resCloudVolume = allInstance.get(0);
            List<CloudVmare> volumeRelatedVmList = cloudVolumeMapper.getVolumeRelatedVm(resId);
            resCloudVolume.setCloudVmArr(volumeRelatedVmList);
            return resCloudVolume;
        } else {
            return new CloudVolume();
        }

    }


    /**
     * 条件查询所有的磁盘信息
     *
     * @param cloudVolume
     * @return
     */
    public List getAllCloudVolume(CloudVolume cloudVolume) {
        return cloudVolumeMapper.getAllInstance(cloudVolume);
    }


    /**
     * 条件查询所有的磁盘信息
     *
     * @param vmId
     * @return
     */
    public List getAllCloudVolume(String vmId) {
        return cloudVolumeMapper.getVmVolumes(vmId);
    }

    public int getResCount() {
        return cloudVolumeMapper.countByExample(null);
    }
}
