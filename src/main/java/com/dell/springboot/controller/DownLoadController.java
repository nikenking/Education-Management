package com.dell.springboot.controller;

import com.dell.springboot.Service.StudentService;
import com.dell.springboot.entities.Student;
import com.dell.springboot.entities.Teacher;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class DownLoadController {
//    @Autowired
//    StudentService ss;
//    @RequestMapping(value = {"UserExcelDownloads"})
//    public String downloadAllClassmate(HttpServletResponse response) throws IOException {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("成绩表");
//        List<Student> students =  ss.getAllStudent();
//        String fileName = "score"  + ".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
//        int rowNum = 1;
//
//        String[] headers = { "学号", "姓名", "成绩", "性别"};
//        //headers表示excel表中第一行的表头
//
//        HSSFRow row = sheet.createRow(0);
//        //在excel表中添加表头
//
//        for(int i=0;i<headers.length;i++){
//            HSSFCell cell = row.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text);
//        }
//
//        //在表中存放查询到的数据放入对应的列
//        for (Student student : students) {
//            HSSFRow row1 = sheet.createRow(rowNum);
//            row1.createCell(0).setCellValue(student.getsId());
//            row1.createCell(1).setCellValue(student.getsName());
//            row1.createCell(2).setCellValue(student.getsPass());
//            row1.createCell(3).setCellValue(student.getsGender().equals(0)?"女":"男");
//            rowNum++;
//        }
//
////        response.setContentType("application/octet-stream");
////        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
////        response.flushBuffer();
////        workbook.write(response.getOutputStream());
////        workbook.close();
//        return "redirect:/downloadingScore";
//    }
}
