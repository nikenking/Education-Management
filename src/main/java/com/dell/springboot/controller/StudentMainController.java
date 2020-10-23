package com.dell.springboot.controller;

import com.dell.springboot.Service.ClassService;
import com.dell.springboot.Service.CourseService;
import com.dell.springboot.Service.StudentService;
import com.dell.springboot.Service.TeacherService;
import com.dell.springboot.entities.Course;
import com.dell.springboot.entities.Score;
import com.dell.springboot.entities.Student;
import com.dell.springboot.entities.TeacherToCourse;
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
public class StudentMainController {
    @Autowired
    TeacherService ts;
    @Autowired
    ClassService cs;
    @Autowired
    CourseService css;
    @Autowired
    StudentService ss;
    @RequestMapping({"/s"})
    public String toMain1(Model model, HttpSession session, Map<String, String> map) {//分页
        model.addAttribute("student",ss.getStudentById((String) session.getAttribute("sId")).get(0));
        if (session.getAttribute("show_msg") != null && session.getAttribute("show_msg") != "") {//弹出事件管理
            map.put("show_msg", (String) session.getAttribute("show_msg"));
            session.removeAttribute("show_msg");
        }
        return "s_main";//使用$符号来获取对象好吗，不要像憨批一样使用""
    }
    @RequestMapping(value = {"/s/score"})
    public String toScore(Model model,HttpSession session,Map<String,String> map){
        List<Score> scores = ss.getScore((String) session.getAttribute("sId"));
        for (Score score : scores) {
            score.setsName(css.getCourseNameById(score.getCourseId()));
            System.out.println(score);
        }
        model.addAttribute("score",scores);
        return "s_main_score";
    }
    @RequestMapping(value = {"/s/class"})
    public String toClass(Model model,HttpSession session,Map<String,String> map){
        Student student = ss.getStudentById((String) session.getAttribute("sId")).get(0);
        List<Course> courses = cs.getAllCourses(student.getsClassId());
        List<TeacherToCourse> teacherToCourses = new ArrayList<>();
        for (Course cours : courses) {
            teacherToCourses.add(new TeacherToCourse(css.getTeacherName(cours.getcId_s()).get(0),cours.getcName()));
            System.out.println(teacherToCourses);
        }
        model.addAttribute("classId",student.getsClassId());
        model.addAttribute("teacherToCourse",teacherToCourses);
        return "s_main_class";
    }
}
