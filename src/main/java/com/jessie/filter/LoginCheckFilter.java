package com.jessie.filter;

import com.alibaba.fastjson.JSONObject;
import com.jessie.pojo.Result;
import com.jessie.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 强制转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 1-获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}",url);

        // 2-判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        // 3-获取请求头中的令牌token
        String jwt = req.getHeader("token");


        // 4-判断令牌是否存在，不存在则返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)){
            // 不存在
            log.info("token为空");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换 对象转为json
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin); // 响应未登录的结果给浏览器
            return;
        }

        // 5-解析令牌，解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) { // jwt解析失败
            e.printStackTrace();
            log.info("解析失败，返回错误结果（未登录）");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换 对象转为json
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin); // 响应未登录的结果给浏览器
            return;
        }


        // 6-放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
