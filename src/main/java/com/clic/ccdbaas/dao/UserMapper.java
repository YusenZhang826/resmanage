package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.SysUser;
import com.clic.ccdbaas.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    Map<String, Object> getUserByName(String userName);

    void insertUser(User user);

    long getUserId(User user);

    List<Map> getProductUserInfo(@Param("smIds") List smIds);

    void insertUserInfo(SysUser user);

    void insertUserRole(Map roleMap);

    List<String> selectAllSysUserEmpNo();

    Long selectUserIdByEmpNo(String employeeNo);

    int updateUserByEmpNo(SysUser user);

    Map selectUserByEmpNo(String empNo);

    List<Map> selectAllSysUserInfo();

    List<String> getSysUserEmpNoByUserName(String userName);
}
