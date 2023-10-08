package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.FileStore;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface FileStoreMapper {
    void addFileStore(FileStore fileStore);

    List<FileStore> getAllFileStore(FileStore fileStore);

    FileStore getFileStoreByResId(String resId);

    void updateFileStore(FileStore fileStore);

    FileStore getFileStoreByNativeId(String nativeId);

    String getNFSRelationByFileIdAndName(HashMap<String, Object> map);

    void addNFSRelation(HashMap<String, Object> map);

    String getFileStoreResIdByNativeId(String nativeId);

    List<HashMap> getFileStoreRelationByResId(String resId);

    List<String> getIpbyFileId(String resId);

    void addSystemLink(HashMap map);

    List<HashMap> getSystemLinkByType(List<String> types);

    String getFileStoreResIdByNameAndPool(HashMap map);

    void deleteFileStoreByNativeId(String nativeId);

    int getFileStoreCount();

    List<HashMap> selectReserveStorageResIdByIp(String ip);

    void updateSystemLinkExtraSpec(HashMap link);

    String getSystemLinkByExtraSpec(String param);

    HashMap getReserveStorageById(String storageId);
}
