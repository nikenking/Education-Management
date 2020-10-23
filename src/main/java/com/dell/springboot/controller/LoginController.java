package com.dell.springboot.controller;

import com.dell.springboot.Service.LoginService;
import com.dell.springboot.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    LoginService ls;
    @Autowired
    TeacherService ts;

    @RequestMapping({"/loginCheck"})
    public String LoginCheck(Map<String, String> msg,
                             @RequestParam("uname") String uname,
                             @RequestParam("upass") String upass,
                             HttpSession session,HttpServletRequest req,HttpServletResponse res) {
        if (ls.LoginCheck(uname, upass)) {
            session.setAttribute("root",uname);
            return "redirect:/";
        } else if (ls.StudentLoginCheck(uname,upass)!=null){
            session.setAttribute("sId",uname);
            return "redirect:/s";
        }else if (ls.TeacherLoginCheck(uname,upass)!=null){
            session.setAttribute("tId",uname);
            session.setAttribute("sort",2);
            session.setAttribute("classChoose",ts.getAllClass(uname).get(0));
            return "redirect:/t_main";
        }else
            msg.put("ErrorMsg", "账号或密码错误请重试");
        return "login";
    }

    @RequestMapping(value = {"/login"})
    public String ToLogin(HttpSession session,HttpServletRequest req,HttpServletResponse res) {
//        if (req.getCookies()!=null&&req.getCookies().length>0){
//            return "redirect:/";
//        }
        return "login";
    }

    @RequestMapping(value = {"/exit"})
    public String exit(HttpSession session){
        session.removeAttribute("root");
        session.removeAttribute("sId");
        return "login";
    }


}


























//    @Autowired
//    private HttpServletRequest req;
//    @Autowired
//    private HttpServletResponse res;
//@ResponseBody
//    @GetMapping(value = {"/getAjax"})
//    public String something() throws IOException {
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setHeader("Access-Control-Allow-Headers", "Content-Type,Token");
//        res.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
//
//        return "hello ajax";
//    }