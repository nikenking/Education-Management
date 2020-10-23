package com.dell.springboot.controller;


import com.dell.springboot.Service.ClassService;
import com.dell.springboot.Service.CourseService;
import com.dell.springboot.Service.StudentService;
import com.dell.springboot.Service.TeacherService;
import com.dell.springboot.Util.FileDownload;
import com.dell.springboot.entities.Student;
import com.dell.springboot.entities.StudentToCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class TeacherMainController {
    @Autowired
    TeacherService ts;
    @Autowired
    ClassService cs;
    @Autowired
    CourseService css;
    @Autowired
    StudentService ss;

    @RequestMapping(value = {"/t_main", "/t_main/"})
    public String toMain() {
        return "redirect:/t_main/1";
    }

    @RequestMapping(value = {"/t_main/{page}"})
    public String toMain1(Model model, @PathVariable("page") Integer page, HttpSession session, Map<String, String> map) {//分页
        if (session.getAttribute("show_msg") != null && session.getAttribute("show_msg") != "") {//弹出事件管理
            map.put("show_msg", (String) session.getAttribute("show_msg"));
            session.removeAttribute("show_msg");
        }
        List<String> allClass = ts.getAllClass((String) session.getAttribute("tId"));//拿到所有班级String列表
        List<Student> source = new ArrayList<>();
        List<Student> teacher = new ArrayList<>();//将当前页的学生集合添加到teacher
        Integer courseId = ts.getTeacherById(String.valueOf(session.getAttribute("tId"))).get(0).getCourseId();//拿到当前老师对应的课程id
        System.out.println("老师当前课程id:" + courseId);
        source = ts.getClassStudent((String) session.getAttribute("classChoose"));//通过前端选择的班级来查询当前班级的所有学生
        System.out.println(source);
        for (Student student : source) {//遍历当前班级的所有学生
            String oneScore = ss.getOneScore(new StudentToCourse(student.getsId(), courseId));
            if (oneScore!=null&& !oneScore.equals("")){
                student.setOneScore(oneScore);//通过一个包含有学号和课程号的类查询当前课程的成绩添加到学生类中
            }else student.setOneScore("0");
            System.out.println(student);
        }
        //通过select选择提交到表单，存入session，进行排序，之后还要将结果返回到前端中
        switch ((Integer) session.getAttribute("sort")) {
            case 0:
                source.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getsId().compareTo(o2.getsId());
                    }
                });
                break;
            case 1:
                source.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return -o1.getsId().compareTo(o2.getsId());
                    }
                });
                break;
            case 2:
                source.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return -o1.getOneScore().compareTo(o2.getOneScore());
                    }
                });
                break;
            default:
                source.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getOneScore().compareTo(o2.getOneScore());
                    }
                });
                break;
        }
        for (int i = (page - 1) * 10; i < 10 * page; i++) {
            if (i >= source.size() || i < 0) break;
            teacher.add(source.get(i));
        }//将当前页的学生集合添加到teacher
        model.addAttribute("page", page);
        if (source.size() % 10 != 0) {
            model.addAttribute("maxPage", (source.size() / 10) + 1);
        } else model.addAttribute("maxPage", source.size() / 10);
        model.addAttribute("classes", allClass);
        model.addAttribute("student", teacher);
        return "t_main";//使用$符号来获取对象好吗，不要像憨批一样使用""
    }

    @RequestMapping(value = {"/t_main/change"})
    public String getChange(@RequestParam("classChoice") String classChoice,
                            @RequestParam("sort") Integer sort,
                            HttpSession session) {
        System.out.println("classChoice = " + classChoice + "sort = " + sort);
        session.setAttribute("classChoose", classChoice);
        session.setAttribute("sort", sort);
        return "redirect:/t_main";
    }


    @RequestMapping(value = {"/t_main/search"})
    public String getSearch(@RequestParam("search_val") String value, Model model, HttpSession session, Map<String, String> map) {
        List<Student> source= new ArrayList<>();
        List<Student> teacher = new ArrayList<>();
        if (Pattern.matches("\\d+", value)) {
            source = ss.getStudentById(value).size() != 0 ?
                    ss.getStudentById(value) : new ArrayList<>();
        } else if (Pattern.matches("[\\u4e00-\\u9fa5]+", value)) {
            source = ss.getStudentByName(value).size() != 0 ?
                    ss.getStudentByName(value) : new ArrayList<>();
        }
        if (session.getAttribute("show_msg") != null && session.getAttribute("show_msg") != "") {//弹出事件管理
            map.put("show_msg", (String) session.getAttribute("show_msg"));
            session.removeAttribute("show_msg");
        }
        List<String> allClass = ts.getAllClass((String) session.getAttribute("tId"));//拿到所有班级String列表
        Integer courseId = ts.getTeacherById(String.valueOf(session.getAttribute("tId"))).get(0).getCourseId();//拿到当前老师对应的课程id
        System.out.println("老师当前课程id:" + courseId);
        for (Student student : source) {//遍历当前班级的所有学生
            student.setOneScore(ss.getOneScore(new StudentToCourse(student.getsId(), courseId)));//通过一个包含有学号和课程号的类查询当前课程的成绩添加到学生类中
            System.out.println(student);
        }
        //通过select选择提交到表单，存入session，进行排序，之后还要将结果返回到前端中
        switch ((Integer) session.getAttribute("sort")) {
            case 0:
                source.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getsId().compareTo(o2.getsId());
                    }
                });
                break;
            case 1:
                source.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return -o1.getsId().compareTo(o2.getsId());
                    }
                });
                break;
            case 2:
                source.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return -o1.getOneScore().compareTo(o2.getOneScore());
                    }
                });
                break;
            default:
                source.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getOneScore().compareTo(o2.getOneScore());
                    }
                });
                break;
        }
        int page = 1;
        for (int i = (page - 1) * 10; i < 10 * page; i++) {
            if (i >= source.size() || i < 0) break;
            teacher.add(source.get(i));
        }//将当前页的学生集合添加到teacher
        model.addAttribute("page", page);
        if (source.size() % 10 != 0) {
            model.addAttribute("maxPage", (source.size() / 10) + 1);
        } else model.addAttribute("maxPage", source.size() / 10);
        model.addAttribute("classes", allClass);
        model.addAttribute("student", teacher);
        return "t_main";//使用$符号来获取对象好吗，不要像憨批一样使用""
    }

    @RequestMapping(value = {"/UserExcelDownloads"})
    public String downLoad(HttpServletResponse res, HttpSession session) throws IOException {
        File file = new File(session.getAttribute("classChoose")+"班"+
                css.getCourseNameById(ts.getTeacherById((String) session.getAttribute("tId")).get(0).getCourseId())+"成绩.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("学号,姓名,成绩,性别\n");
        List<Student> source = ts.getClassStudent((String) session.getAttribute("classChoose"));//通过前端选择的班级来查询当前班级的所有学生
        for (Student student : source) {//遍历当前班级的所有学生
            student.setOneScore(ss.getOneScore(new StudentToCourse(student.getsId(),
                    ts.getTeacherById((String) session.getAttribute("tId")).get(0).getCourseId())));//通过一个包含有学号和课程号的类查询当前课程的成绩添加到学生类中
            bw.write(student.getsId() + "," + student.getsName() + "," +
                    student.getOneScore() +","+ (student.getsGender().equals(0) ? "女\n" : "男\n"));
        }
        bw.close();
        String filename = session.getAttribute("classChoose")+"班"+
                css.getCourseNameById(ts.getTeacherById((String) session.getAttribute("tId")).get(0).getCourseId())+"成绩.csv";
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type", type);
        // 设置编码
        String coding = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + coding);
        FileDownload.download(filename, response);
        return "redirect:/t_main";
    }
}
