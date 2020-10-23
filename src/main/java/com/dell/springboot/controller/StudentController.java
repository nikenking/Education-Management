package com.dell.springboot.controller;

import com.dell.springboot.Service.ClassService;
import com.dell.springboot.Service.LoginService;
import com.dell.springboot.Service.StudentService;
import com.dell.springboot.Util.CheckInput;
import com.dell.springboot.entities.Student;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class StudentController {
    @Autowired
    StudentService ss;
    @Autowired
    ClassService cs;
    //默认跳转到第一页
    @RequestMapping({"/"})
    public String toMain(){
        return "redirect:/1";
    }
    //跳转到第n页
    @RequestMapping({"/{page}"})
    public String toMain1(Model model, @PathVariable("page")Integer page, HttpSession session, Map<String,String> map){//分页
        if (session.getAttribute("show_msg")!=null&&session.getAttribute("show_msg")!=""){
            map.put("show_msg", (String) session.getAttribute("show_msg"));
            session.removeAttribute("show_msg");
        }
        List<Student> source = ss.getAllStudent();
        List<Student> students = new ArrayList<>();
        for (int i = (page-1)*10; i < 10*page; i++) {
            if (i>=source.size()||i<0)break;
            students.add(source.get(i));
        }
        model.addAttribute("students", students);
        model.addAttribute("page",page);
        if (source.size()%10!=0){
            model.addAttribute("maxPage",(source.size()/10)+1);
        }else model.addAttribute("maxPage",source.size()/10);
        return "main";
    }
    //跳转到查询界面
    @RequestMapping(value = {"/search"})
    public String toMain2(@RequestParam("search_val") String value,Model model){//查询
        List<Student> student = null;
        if (Pattern.matches("\\d+",value)){
            student = ss.getStudentById(value).size()!=0?
                    ss.getStudentById(value):null;
        }else if (Pattern.matches("[\\u4e00-\\u9fa5]+",value)){
            student = ss.getStudentByName(value).size()!=0?
                    ss.getStudentByName(value):null;
        }
        model.addAttribute("students", student);
        model.addAttribute("page",1);
        model.addAttribute("maxPage",1);
        return "main";
    }
    //跳转到添加界面
    @RequestMapping({"/addStudent.html"})
    public String Jump1/*添加学生*/(Model model){
        List<String> source = cs.getAllClass();
        model.addAttribute("classes",source);
        model.addAttribute("student",ss.getAllStudent().get(0));
        model.addAttribute("change",0);
        return "addStudent";
    }
    //添加学生
    @RequestMapping({"/addStudent"})
    public String addStudent/*添加学生*/(@RequestParam("id") String id,
                                     @RequestParam("name") String name,
                                     @RequestParam("gender") Integer gender,
                                     @RequestParam("depId") Integer depId,HttpSession session){
        if (ss.getStudentByIdExac(id).size()==0){
            if (CheckInput.checkStudent(id,name)){
                ss.addStudent(new Student(id,name,gender,depId,"123456"));
                session.setAttribute("show_msg","学生:"+name+"，添加成功");
            }else session.setAttribute("show_msg","学号或姓名格式出错，添加失败");
        }else session.setAttribute("show_msg","此学号已被占用，添加失败");
        return "redirect:/";
    }
    //跳转到修改界面
    @RequestMapping(value = {"changeStudent/{id}"})
    public String toChange(@PathVariable("id") String id, Model model){
        model.addAttribute("student",ss.getStudentByIdExac(id).get(0));
        System.out.println(ss.getStudentById(id).get(0).getsClassId());
        System.out.println(ss.getStudentById(id).get(0));
        List<String> source = cs.getAllClass();
        model.addAttribute("classes",source);
        model.addAttribute("change",1);
        return "addStudent";
    }
    //修改学生
    @RequestMapping(value = {"changeStudent"})
    public String changeStudent(@RequestParam("id") String id,
                                @RequestParam("name") String name,
                                @RequestParam("gender") Integer gender,
                                @RequestParam("depId") Integer depId,
                                HttpSession session){
        if (ss.getStudentByIdExac(id).size()==1){
            if (CheckInput.checkStudent(id,name)){
                ss.changeStudent(new Student(id,name,gender,depId,"123456"));
                session.setAttribute("show_msg","修改成功");
            }else  session.setAttribute("show_msg","学号或姓名格式出错，修改失败");
        }else session.setAttribute("show_msg","此学号已被占用，修改失败");
        return "redirect:/";
    }
    //删除学生
    @RequestMapping(value = {"delStudent/{id}"})
    public String deleteStudent(@PathVariable("id") String id,HttpSession session){
        System.out.println("删除id="+id);
        ss.deleteStudent(id);
        session.setAttribute("show_msg","删除成功");
        return "redirect:/";
    }
}
