package com.clic.ccdbaas.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clic.ccdbaas.dao.ObjectStorageMapper;
import com.clic.ccdbaas.entity.ObjectStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service("OBJECT_STORAGE")
public class ObjectStorageService {
    @Value("${obs.ak}")
    private String ak;
    @Value("${obs.sk}")
    private String sk;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ObjectStorageService.class);
    @Autowired
    private ObjectStorageMapper objectStorageMapper;
    private static final ExecutorService pool = Executors.newFixedThreadPool(30);

    public void addObjectStorage2DB() throws Exception {
        /**
         HashMap<String, String> locationMap = new HashMap<>();
         HashMap<String, String> map = new HashMap<>();
         locationMap.put("bj-kjy-12", "北京-测试信创1区");
         locationMap.put("北京-测试信创1区", "obs.bj-kjy-12.cloud.clic");
         locationMap.put("bj-kjy-50", "北京-股份信创1区");
         locationMap.put("北京-股份信创1区", "obs.bj-kjy-50.cloud.clic");
         locationMap.put("bj-kjy-60", "北京-成员信创1区");
         locationMap.put("北京-成员信创1区", "obs.bj-kjy-60.cloud.clic");
         locationMap.put("sh-ky-70", "上海-股份信创1区");
         locationMap.put("上海-股份信创1区", "obs.sh-ky-70.cloud.clic");
         List<ObjectStorage> storages = new ArrayList<>();
         ObsClient obsClient = new ObsClient(ak, sk, "obs.cloud.clic");
         ListBucketsRequest request = new ListBucketsRequest();
         request.setQueryLocation(true);
         List<ObsBucket> buckets = obsClient.listBuckets(request);
         for (ObsBucket bucket : buckets) {
         ObjectStorage temp = new ObjectStorage();
         temp.setName(bucket.getBucketName());
         temp.setCreateDate(bucket.getCreationDate());
         temp.setBizRegionName(locationMap.get(bucket.getLocation()));
         storages.add(temp);

         }
         for (ObjectStorage storage : storages) {
         try {
         //获取桶配额
         String endPoint = locationMap.get(storage.getBizRegionName());
         obsClient = new ObsClient(ak, sk, endPoint);
         BucketStorageInfo storageInfo = obsClient.getBucketStorageInfo(storage.getName());
         storage.setObjectNumber((int) storageInfo.getObjectNumber());
         storage.setUsedSize(BigInteger.valueOf(storageInfo.getSize() / 1024));
         BucketQuota quota = obsClient.getBucketQuota(storage.getName());
         storage.setSize((int) (quota.getBucketQuota() / 1048576));
         } catch (ObsException e) {
         System.out.println("Response Code: " + e.getResponseCode());
         System.out.println("Error Message: " + e.getErrorMessage());
         System.out.println("Error Code:" + e.getErrorCode());
         System.out.println("Request ID:" + e.getErrorRequestId());
         System.out.println("Host ID:" + e.getErrorHostId());
         }
         //通过桶名+资源集查询数据库中是否已存在
         LambdaQueryWrapper<ObjectStorage> wrapper = Wrappers.lambdaQuery();
         wrapper.eq(ObjectStorage::getBizRegionName, storage.getBizRegionName()).eq(ObjectStorage::getName, storage.getName());
         ObjectStorage tempStorage = objectStorageMapper.selectOne(wrapper);
         if (tempStorage == null) {
         storage.setResId(IdUtils.generatedUUID());
         objectStorageMapper.insert(storage);
         } else {
         storage.setResId(tempStorage.getResId());
         if (!tempStorage.equals(storage)) {
         QueryWrapper<ObjectStorage> objectWrapper = new QueryWrapper();
         objectWrapper.eq("resId", storage.getResId());
         objectStorageMapper.update(storage, wrapper);
         }
         }
         }
         if (obsClient != null) {
         try {
         obsClient.close();
         } catch (IOException e) {
         }
         }
         **/
    }

    //获取全部对象存储实例
    public List<ObjectStorage> getAllInstance(ObjectStorage objectStorage) {
        QueryWrapper<ObjectStorage> queryWrapper = new QueryWrapper();
        if (objectStorage.getName() != null) {
            queryWrapper.like("name", objectStorage.getName());
        }
        if (objectStorage.getBizRegionName() != null) {
            queryWrapper.like("bizRegionName", objectStorage.getBizRegionName());
        }
        List<ObjectStorage> instances = objectStorageMapper.selectList(queryWrapper);
        return instances;
    }

    public ObjectStorage getInstanceDetail(String resId) {
        QueryWrapper<ObjectStorage> queryWrapper = new QueryWrapper();
        queryWrapper.eq("resId", resId);
        return objectStorageMapper.selectOne(queryWrapper);
    }

    public int getResCount() {
        QueryWrapper<ObjectStorage> queryWrapper = new QueryWrapper();
        return objectStorageMapper.selectCount(queryWrapper);
    }
}
