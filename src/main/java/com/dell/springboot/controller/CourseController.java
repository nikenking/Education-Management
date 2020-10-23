package com.dell.springboot.controller;

import com.dell.springboot.Service.ClassService;
import com.dell.springboot.Service.CourseService;
import com.dell.springboot.Service.TeacherService;
import com.dell.springboot.entities.ClassToCourse;
import com.dell.springboot.entities.Classes;
import com.dell.springboot.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class CourseController {
    @Autowired
    TeacherService ts;
    @Autowired
    ClassService cs;
    @Autowired
    CourseService css;

    //默认跳转到第一页
    @RequestMapping({"/cs", "/cs/"})
    public String toMain() {
        return "redirect:cs/1";
    }

    //跳转到第n页
    @RequestMapping({"/cs/{page}"})
    public String toMain1(Model model, @PathVariable("page") Integer page, HttpSession session, Map<String, String> map) {//分页
        List<Course> source = css.getAllCourse();
        List<Course> teacher = new ArrayList<>();
        for (int i = (page - 1) * 10; i < 10 * page; i++) {//计算当前页能放下的数量
            if (i >= source.size() || i < 0) break;
            //课程集合
            teacher.add(source.get(i));
        }
        if (session.getAttribute("show_msg") != null && session.getAttribute("show_msg") != "") {//弹出事件管理
            map.put("show_msg", (String) session.getAttribute("show_msg"));
            session.removeAttribute("show_msg");
        }
        model.addAttribute("course", teacher);//传递当前页能容纳的班级集合
        model.addAttribute("page", page);//当前页
        if (source.size() % 10 != 0) {//通过班级总数计算最大页有多少
            model.addAttribute("maxPage", (source.size() / 10) + 1);
        } else model.addAttribute("maxPage", source.size() / 10);
        return "main_cs";//使用$符号来获取对象好吗，不要像憨批一样使用""
    }

    //跳转到查询界面
    @RequestMapping(value = {"/cs_search"})
    public String toMain2(@RequestParam("search_val") String value,Model model){//查询
        List<Course> course = null;
        if (Pattern.matches("\\d+",value)){
            course = css.getCourseById(Integer.parseInt(value)).size()!=0?
                    css.getCourseById(Integer.parseInt(value)):null;
        }else if (Pattern.matches("[\\u4e00-\\u9fa5|a-zA-Z]+",value)){
            course = css.getCourseByName(value).size()!=0?
                    css.getCourseByName(value):null;
        }
        model.addAttribute("course", course);
        model.addAttribute("page",1);
        model.addAttribute("maxPage",1);
        return "main_cs";
    }

    //跳转到添加界面
    @RequestMapping({"/addCourse.html"})
    public String Jump1/*添加学生*/(Model model){
        model.addAttribute("course",new Course(1,"java"));//
        model.addAttribute("change",0);
        return "addCourse";
    }
    //跳转到修改界面
    @RequestMapping(value = {"changeCourse/{id}"})
    public String toChange(@PathVariable("id") String id, Model model){
        Course course = css.getCourseById(Integer.valueOf(id)).get(0);//拿到修改的course对象
        model.addAttribute("course",course);
        model.addAttribute("change",1);
        return "addCourse";
    }

    //修改课程
    @RequestMapping(value = {"changeCourse"})
    public String changeStudent(@RequestParam("id") String id,
                                @RequestParam("name") String name,
                                HttpSession session){
        css.changeCourse(new Course(Integer.parseInt(id),name));
        session.setAttribute("show_msg","修改成功");
        return "redirect:/cs/1";
    }
    //添加课程
    @RequestMapping({"/addCourse"})
    public String addStudent/*添加学生*/(@RequestParam("name") String name,
                                     HttpSession session){
            int id = css.getMaxRelationId().get(0)+1;
            css.addCourse(new Course(id,name));
            session.setAttribute("show_msg","班级:"+id+",添加成功");
        return "redirect:/cs/1";
    }

    //删除班级
    @RequestMapping(value = {"delCourse/{id}"})
    public String deleteTeacher(@PathVariable("id") String id,HttpSession session){
        System.out.println("删除id="+id);
        css.delCourse(new Course(Integer.parseInt(id),""));
        session.setAttribute("show_msg","删除成功");
        return "redirect:/cs/1";
    }
}
