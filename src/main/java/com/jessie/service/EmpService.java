package com.jessie.service;

import com.jessie.pojo.PageBean;

import java.time.LocalDate;

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
}
