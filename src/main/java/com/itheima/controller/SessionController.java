package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class SessionController {

    @GetMapping("/c1")
    public Result ccokie1(HttpServletResponse response){
        response.addCookie(new Cookie("login_username","itheima"));
        return Result.success();
    }

    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getValue());
        }
        return Result.success();
    }

    @GetMapping("/s1")
    public Result session1(HttpSession httpSession){
        log.info("httpSession-s1:{}",httpSession.hashCode());

        httpSession.setAttribute("loginUser","tom");
        return Result.success();
    }

    @GetMapping("/s2")
    public Result session2(HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("HttpSession-s2:{}",session.hashCode());

        Object loginUser = session.getAttribute("loginUser");
        log.info("loginUser:{}",loginUser);
        return Result.success(loginUser);
    }
}
