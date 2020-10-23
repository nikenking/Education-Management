package com.dell.springboot.configer;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object root = httpServletRequest.getSession().getAttribute("root");
        Object sId = httpServletRequest.getSession().getAttribute("sId");
        Object tId = httpServletRequest.getSession().getAttribute("tId");
        if (root!=null||sId!=null||tId!=null){
            return true;
        }else{
            httpServletRequest.setAttribute("ErrorMsg","没有权限，请先登录");
            httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest,httpServletResponse);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
