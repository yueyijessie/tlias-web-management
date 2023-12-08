package com.jessie.service.impl;

import com.jessie.mapper.DeptMapper;
import com.jessie.mapper.EmpMapper;
import com.jessie.pojo.Dept;
import com.jessie.pojo.Result;
import com.jessie.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

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
    // 交给spring进行事务管理，执行前开启事务，执行后提交事务，异常后回滚事务
    // 默认情况下，只有运行时异常才会被回滚，并不是所有异常
    // 如果想指定异常类型，可以定义rollbackFor属性
    @Transactional(rollbackFor = Exception.class) // 所有异常都会进行回滚
    public void deleteDeptByID(Integer id){
        deptMapper.deleteDeptByID(id); // 删除部门
        empMapper.deleteByDeptId(id); // 删除部门下的员工
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

    /**
     * 修改部门
     * @param dept
     */
    public void updateDept(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.UpdateDept(dept);
    }

}
