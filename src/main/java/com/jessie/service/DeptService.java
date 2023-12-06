package com.jessie.service;

import com.jessie.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
     List<Dept> getDepts();
}
