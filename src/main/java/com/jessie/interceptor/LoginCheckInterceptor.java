package com.jessie.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jessie.pojo.Result;
import com.jessie.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// 定义interceptor
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标资源方法运行前运行，返回true是放行，false是不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        // 1-获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}",url);

        // 2-判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录操作，放行");
            return true;
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
            return false;
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
            return false;
        }

        // 6-放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override // 目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandle....");
    }

    @Override // 视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion....");
    }
}
