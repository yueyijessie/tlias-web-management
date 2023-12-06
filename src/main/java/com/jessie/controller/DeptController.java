package com.jessie.controller;

import com.jessie.pojo.Dept;
import com.jessie.pojo.Result;
import com.jessie.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j // 引入Logger方便打印日志
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 引入Logger方便打印日志（固定代码）
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);


    /**
     * 查询部门
     * @return
     */
    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result getDepts(){
        log.info("查询部门数据");
        List<Dept> deptList = deptService.getDepts();
        return Result.success(deptList);
    }
}
