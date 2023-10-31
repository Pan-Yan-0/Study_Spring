package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //1.获取请求url
        String url = req.getRequestURI().toString();
        log.info("请求的url:{}",url);

        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")){
            log.info("登录操作，放行......");
            chain.doFilter(request,response);
            return;
        }

        //3.获取请求头中的令牌（token）
        String token = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在，返回错误结构（未登录）

        if (!StringUtils.hasLength(token)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("Not_Login");
            //手动转化  对象--JSON ------> fastJSON
            String s = JSONObject.toJSONString(error);
            resp.getWriter().write(s);
            return;
        }


        //5.解析token，如果解析失败，返回错误结构（未登录）

        try {
            JwtUtils.parseJWT(token);

        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录的信息");
            Result error = Result.error("Not_Login");
            //手动转化  对象--JSON ------> fastJSON
            String s = JSONObject.toJSONString(error);
            resp.getWriter().write(s);
            return;
        }

        //6.放行
        log.info("令牌合法，放行");
        chain.doFilter(request,response);
    }
}
