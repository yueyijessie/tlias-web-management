package com.jessie.controller;

import com.jessie.pojo.Emp;
import com.jessie.pojo.PageBean;
import com.jessie.pojo.Result;
import com.jessie.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    // RequestParam(defaultValue = "1") 设置默认值
    @GetMapping
    public Result getEmps(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                          String name, Short gender,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        PageBean pageBean = empService.getEmps(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }


    @DeleteMapping("/{ids}")
    public Result deleteEmps(@PathVariable List<Integer> ids){
        empService.deleteEmps(ids);
        return Result.success();
    }

    @PostMapping
    public Result insertEmp(@RequestBody Emp emp){
        empService.insertEmp(emp);
        return Result.success();
    }

}
