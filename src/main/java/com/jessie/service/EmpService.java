package com.jessie.service;

import com.jessie.pojo.PageBean;

public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean getEmps(Integer page, Integer pageSize);
}
