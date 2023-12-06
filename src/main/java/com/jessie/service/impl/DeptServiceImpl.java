package com.jessie.service.impl;

import com.jessie.mapper.DeptMapper;
import com.jessie.pojo.Dept;
import com.jessie.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
