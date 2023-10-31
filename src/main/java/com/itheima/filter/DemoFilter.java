package com.itheima.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@ServletComponentScan //开启了servlet组件的支持
//@WebFilter(urlPatterns = "/login")
public class DemoFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("11111");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Demo执行的拦截操作");
        System.out.println("放行前的逻辑");
        chain.doFilter(request,response);
        System.out.println("放行后的逻辑");

    }

    @Override   //销毁方法，只调用一次
    public void destroy() {
        System.out.println("destroy 销毁方法执行了");
        Filter.super.destroy();
    }
}