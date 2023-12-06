package com.jessie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jessie.mapper.EmpMapper;
import com.jessie.pojo.Emp;
import com.jessie.pojo.PageBean;
import com.jessie.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    public PageBean getEmps(Integer page, Integer pageSize){
        // 传统方法
        // Long total = empMapper.getTotal();
        // List<Emp> emplist = empMapper.getEmps((page-1)*pageSize, pageSize);
        //
        // PageBean pageBean = new PageBean();
        // pageBean.setTotal(total);
        // pageBean.setRows(emplist);

        // pageHelper方法

        // 设置分页参数
        PageHelper.startPage(page,pageSize);

        // 执行查询
        List<Emp> empList = empMapper.getEmps();
        Page<Emp> p = (Page<Emp>) empList;

        // 封装pagebean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }
}
