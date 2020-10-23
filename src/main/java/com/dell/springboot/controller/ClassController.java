package com.dell.springboot.controller;

import com.dell.springboot.Service.ClassService;
import com.dell.springboot.Service.CourseService;
import com.dell.springboot.Service.TeacherService;
import com.dell.springboot.entities.*;
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

@Controller
public class ClassController {
    @Autowired
    TeacherService ts;
    @Autowired
    ClassService cs;
    @Autowired
    CourseService css;

    //默认跳转到第一页
    @RequestMapping({"/cl", "/cl/"})
    public String toMain() {
        return "redirect:cl/1";
    }

    //跳转到第n页
    @RequestMapping({"/cl/{page}"})
    public String toMain1(Model model, @PathVariable("page") Integer page, HttpSession session, Map<String, String> map) {//分页
        List<String> source = cs.getAllClass();
        List<Classes> teacher = new ArrayList<>();
        for (int i = (page - 1) * 10; i < 10 * page; i++) {//计算当前页能放下的数量
            if (i >= source.size() || i < 0) break;
            //教室集合，每个教室有自己的id和课程集合
            teacher.add(new Classes(Integer.parseInt(source.get(i)),cs.getAllCourses(Integer.parseInt(source.get(i)))));
        }
        if (session.getAttribute("show_msg") != null && session.getAttribute("show_msg") != "") {//弹出事件管理
            map.put("show_msg", (String) session.getAttribute("show_msg"));
            session.removeAttribute("show_msg");
        }
        model.addAttribute("classes", teacher);//传递当前页能容纳的班级集合
        model.addAttribute("page", page);//当前页
        if (source.size() % 10 != 0) {//通过班级总数计算最大页有多少
            model.addAttribute("maxPage", (source.size() / 10) + 1);
        } else model.addAttribute("maxPage", source.size() / 10);
        return "main_cl";//使用$符号来获取对象好吗，不要像憨批一样使用""
    }

    //跳转到查询界面
    @RequestMapping(value = {"/cl_search"})
    public String toMain2(@RequestParam("search_val") String value,Model model){//查询
        List<Classes> teacher;
        teacher = cs.getClassesById(value);
        for (Classes classes : teacher) {
            classes.setCourses(cs.getAllCourses(classes.getcId()));
        }
        model.addAttribute("classes", teacher);
        model.addAttribute("page",1);
        model.addAttribute("maxPage",1);
        return "main_cl";
    }

    //跳转到添加界面
    @RequestMapping({"/addClasses.html"})
    public String Jump1/*添加学生*/(Model model){
        List<Course> allCourse = css.getAllCourse();
        model.addAttribute("course",allCourse);
        model.addAttribute("myCourse",new ArrayList<>());
        model.addAttribute("teacher",cs.getAllClass().get(0));
        model.addAttribute("change",0);
        return "addClass";
    }
    //跳转到修改界面
    @RequestMapping(value = {"changeClass/{id}"})
    public String toChange(@PathVariable("id") String id, Model model){
        Classes classes = cs.getClassesById(id).get(0);
        model.addAttribute("classid",classes.getcId());//获取班级对象
        model.addAttribute("myCourse",cs.getAllCourseId(Integer.valueOf(id)));
        model.addAttribute("course",css.getAllCourse());
        model.addAttribute("change",1);
        return "addClass";
    }

    //修改班级
    @RequestMapping(value = {"changeClasses"})
    public String changeStudent(@RequestParam("id") String id,
                                @RequestParam("choices") List<String> choices,
                                HttpSession session){
        cs.deleteClass(id);
        cs.deleteClassToCourse(id);
        for (String choice : choices) {
            cs.addRelation(new ClassToCourse(cs.getMaxRelationId().get(0)+1,id,choice));
        }
        cs.addClasses(new Classes(Integer.parseInt(id)));
        session.setAttribute("show_msg","修改成功");
        return "redirect:/cl/1";
    }
    //添加学生
    @RequestMapping({"/addClasses"})
    public String addStudent/*添加学生*/(@RequestParam("id") String id,
                                     @RequestParam("choices") List<String> choices,
                                     HttpSession session){
        if (cs.getClassesById(String.valueOf(id)).size()==0){
            for (String choice : choices) {
                cs.addRelation(new ClassToCourse(cs.getMaxRelationId().get(0)+1,id,choice));
            }
            cs.addClasses(new Classes(Integer.parseInt(id)));
            session.setAttribute("show_msg","班级:"+id+",添加成功");
        }else session.setAttribute("show_msg","此班级已存在！添加失败");
        return "redirect:/cl/1";
    }

    //删除班级
    @RequestMapping(value = {"delClasses/{id}"})
    public String deleteTeacher(@PathVariable("id") String id,HttpSession session){
        System.out.println("删除id="+id);
        cs.deleteClass(id);
        cs.deleteClassToCourse(id);
        session.setAttribute("show_msg","删除成功");
        return "redirect:/cl/1";
    }
}
