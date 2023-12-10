package com.jessie.aop;


import com.alibaba.fastjson.JSONObject;
import com.jessie.mapper.OperateLogMapper;
import com.jessie.pojo.OperateLog;
import com.jessie.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component // 交给ioc管理
@Aspect // 声明为切面类
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    // 声明待重复执行的逻辑
    @Around("@annotation(com.jessie.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        // 获取信息
        // 当前人的id = 请求头的jwt令牌，解析
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 操作类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        // 方法开始时间
        long begin = System.currentTimeMillis();

        // 调用原始目标方法运行
        Object result = proceedingJoinPoint.proceed();

        // 方法返回值
        String returnValue = JSONObject.toJSONString(result);
        // 方法结束时间
        long end = System.currentTimeMillis();
        // 操作耗时
        Long costTime = end - begin;


        // 记录操作日志(全参构造赋值)
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("Aop记录操作日志:{}", operateLog);

        return result;

    }
}
