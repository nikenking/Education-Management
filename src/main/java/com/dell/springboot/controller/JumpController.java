package com.dell.springboot.controller;

import com.dell.springboot.Service.StudentService;
import com.dell.springboot.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class JumpController {
    @Autowired
    StudentService ss;

}
