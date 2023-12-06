package com.jessie.controller;

import com.jessie.pojo.Dept;
import com.jessie.pojo.Result;
import com.jessie.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 引入Logger方便打印日志
@RestController
@RequestMapping("/depts")
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
    @GetMapping
    public Result getDepts(){
        log.info("查询部门数据");
        List<Dept> deptList = deptService.getDepts();
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result deleteDeptByID(@PathVariable Integer id){
        log.info("删除数据,{}", id);
        deptService.deleteDeptByID(id);
        return Result.success();
    }

    @PostMapping
    public Result insertDept(@RequestBody Dept dept){
        log.info("新增部门, {}", dept);
        deptService.insertDept(dept);
        return Result.success();
    }
}
