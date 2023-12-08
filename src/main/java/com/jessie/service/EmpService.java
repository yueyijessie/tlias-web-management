package com.jessie.service;

import com.jessie.pojo.Emp;
import com.jessie.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    PageBean getEmps(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 删除多个或单个员工
     * @param ids
     */
    void deleteEmps(List<Integer> ids);

    /**
     * 添加员工
     * @param emp
     */
    void insertEmp(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Emp getEmpById(Integer id);

    /**
     * 修改员工
     * @param emp
     */
    void updateEmp(Emp emp);

    /**
     * 员工登录
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
