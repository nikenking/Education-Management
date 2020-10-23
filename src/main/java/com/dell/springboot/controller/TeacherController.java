package com.dell.springboot.controller;

import com.dell.springboot.Service.ClassService;
import com.dell.springboot.Service.CourseService;
import com.dell.springboot.Service.StudentService;
import com.dell.springboot.Service.TeacherService;
import com.dell.springboot.Util.CheckInput;
import com.dell.springboot.entities.Course;
import com.dell.springboot.entities.Student;
import com.dell.springboot.entities.Teacher;
import org.omg.CORBA.INTERNAL;
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
public class TeacherController {
    @Autowired
    TeacherService ts;
    @Autowired
    ClassService cs;
    @Autowired
    CourseService css;
    //默认跳转到第一页
    @RequestMapping({"/t","/t/"})
    public String toMain(){
        return "redirect:t/1";
    }
    //跳转到第n页
    @RequestMapping({"/t/{page}"})
    public String toMain1(Model model, @PathVariable("page")Integer page,HttpSession session,Map<String,String> map){//分页
        List<Teacher> source = ts.getAllTeacher();
        List<Teacher> teacher = new ArrayList<>();
        for (int i = (page-1)*10; i < 10*page; i++) {
            if (i>=source.size()||i<0)break;
            teacher.add(source.get(i));
        }
        if (session.getAttribute("show_msg")!=null&&session.getAttribute("show_msg")!=""){
            map.put("show_msg", (String) session.getAttribute("show_msg"));
            session.removeAttribute("show_msg");
        }
        model.addAttribute("teachers", teacher);
        model.addAttribute("page",page);
        if (source.size()%10!=0){
            model.addAttribute("maxPage",(source.size()/10)+1);
        }else model.addAttribute("maxPage",source.size()/10);
        return "main_t";
    }
    //跳转到查询界面
    @RequestMapping(value = {"/t_search"})
    public String toMain2(@RequestParam("search_val") String value,Model model){//查询
        List<Teacher> teacher = null;
        if (Pattern.matches("\\d+",value)){
            teacher = ts.getTeacherById(value).size()!=0?
                    ts.getTeacherById(value):null;
        }else if (Pattern.matches("[\\u4e00-\\u9fa5]+",value)){
            teacher = ts.getTeacherByName(value).size()!=0?
                    ts.getTeacherByName(value):null;
        }
        model.addAttribute("teachers", teacher);
        model.addAttribute("page",1);
        model.addAttribute("maxPage",1);
        return "main_t";
    }
    //跳转到添加界面
    @RequestMapping({"/addTeacher.html"})
    public String Jump1/*添加学生*/(Model model){
        List<Course> allCourse = css.getAllCourse();
        model.addAttribute("course",allCourse);
        model.addAttribute("teacher",ts.getAllTeacher().get(0));
        model.addAttribute("change",0);
        return "addTeacher";
    }
    //添加教师
    @RequestMapping({"/addTeacher"})
    public String addStudent/*添加学生*/(@RequestParam("name") String name,
                                     @RequestParam("gender") Integer gender,
                                     @RequestParam("depId") Integer courseid,
                                     HttpSession session){
        List<Teacher> allTeacher = ts.getAllTeacher();
        int i = allTeacher.get(allTeacher.size()-1).gettId()+1;
        if (ts.getTeacherById(String.valueOf(i)).size()==0){
            if (CheckInput.checkTeacher(name)){
                ts.addTeacher(new Teacher(i,name,gender,"123456",courseid));
                session.setAttribute("show_msg","教师:"+name+",添加成功");
            }else session.setAttribute("show_msg","姓名格式有误！添加失败");
        }else session.setAttribute("show_msg","此教师号已存在！添加失败");
        return "redirect:/t/1";
    }
    //跳转到修改界面
    @RequestMapping(value = {"changeTeacher/{id}"})
    public String toChange(@PathVariable("id") String id, Model model){
        model.addAttribute("teacher",ts.getTeacherById(id).get(0));
        List<Course> allCourse = css.getAllCourse();
        model.addAttribute("course",allCourse);
        model.addAttribute("change",1);
        return "addTeacher";
    }
    //删除学生
    @RequestMapping(value = {"delTeacher/{id}"})
    public String deleteTeacher(@PathVariable("id") String id,HttpSession session){
        System.out.println("删除id="+id);
        ts.deleteTeacher(id);
        session.setAttribute("show_msg","删除成功");
        return "redirect:/t/1";
    }
    //修改学生
    @RequestMapping(value = {"changeTeacher"})
    public String changeTeacher(@RequestParam("id") Integer id,
                                @RequestParam("name") String name,
                                @RequestParam("gender") Integer gender,
                                @RequestParam("depId") Integer courseId, HttpSession session){
        if (CheckInput.checkTeacher(name)){
            ts.changeTeacher(new Teacher(id,name,gender,"123456",courseId));
            session.setAttribute("show_msg","修改成功");
        }else session.setAttribute("show_msg","姓名格式有误！修改失败");

        return "redirect:/t/1";
    }

}
