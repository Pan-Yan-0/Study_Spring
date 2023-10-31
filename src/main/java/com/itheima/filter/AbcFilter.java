package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Abc拦截到了请求...放行逻辑");

        //放行
        chain.doFilter(request,response);

        System.out.println("拦截到了请求...放行后的逻辑");
    }
}
