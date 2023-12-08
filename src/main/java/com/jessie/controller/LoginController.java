package com.jessie.controller;

import com.jessie.pojo.Emp;
import com.jessie.pojo.Result;
import com.jessie.service.EmpService;
import com.jessie.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录,{}", emp);
        Emp e = empService.login(emp);

        if (e != null) {
            // 登录成功，生成令牌并下发
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims); // jwt包含当前登录的与昂工信息
            return Result.success(jwt);
        } else {
            // 登录失败，返回错误信息
            return Result.error("用户名或密码错误");
        }
    }


}
