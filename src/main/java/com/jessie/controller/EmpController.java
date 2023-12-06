package com.jessie.controller;

import com.jessie.pojo.Emp;
import com.jessie.pojo.PageBean;
import com.jessie.pojo.Result;
import com.jessie.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    // @PathVariable String name, @PathVariable Short gender, @PathVariable LocalDate begin, @PathVariable LocalDate end,
    // RequestParam(defaultValue = "1") 设置默认值
    @GetMapping
    public Result getEmps(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = empService.getEmps(page, pageSize);
        return Result.success(pageBean);
    }
}
