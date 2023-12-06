package com.jessie.service;

import com.jessie.pojo.Dept;
import com.jessie.pojo.Result;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
     List<Dept> getDepts();

    /**
     * 根据id删除部门
     * @return
     */
    void deleteDeptByID(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void insertDept(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Dept getDeptById(Integer id);

    /**
     * 修改部门
     * @param dept
     */
    void updateDept(Dept dept);
}
