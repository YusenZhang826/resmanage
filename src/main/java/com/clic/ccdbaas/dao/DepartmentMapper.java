package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<Department> selectDeptAll();

    Department selectDeptById(Long id);

    void insertDept(Department dept);

    Long selectDeptIdByGroupAndParent(Long parentId, String group);

    String selectDeptNameById(String deptId);
}
