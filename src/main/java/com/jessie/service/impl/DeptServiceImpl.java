package com.jessie.service.impl;

import com.jessie.mapper.DeptMapper;
import com.jessie.pojo.Dept;
import com.jessie.pojo.Result;
import com.jessie.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 部门列表查询
     * @return
     */
    public List<Dept> getDepts(){
        List<Dept> deptList = deptMapper.getDepts();
        return deptList;
    }

    /**
     * 根据id删除部门
     * @return
     */
    public void deleteDeptByID(Integer id){
        deptMapper.deleteDeptByID(id);
    }

    /**
     * 新增部门
     * @param dept
     */
    public void insertDept(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insertDept(dept);
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    public Dept getDeptById(Integer id){
        Dept dept = deptMapper.getDeptById(id);
        return dept;
    }
}
