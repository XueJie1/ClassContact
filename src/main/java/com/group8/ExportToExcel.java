package com.group8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportToExcel {
    public static void export(List<Student> studentList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息");

<<<<<<< HEAD
        // String[] columnHeadings = { "学号", "姓名", "生日", "性别", "电话", "班级", "宿舍", "籍贯",
        // "照片" };
        String[] columnHeadings = { "学号", "姓名", "生日", "性别", "电话", "班级", "宿舍", "籍贯" };
=======
        String[] columnHeadings = { "学号", "姓名", "生日", "性别", "电话", "班级", "宿舍", "籍贯", "照片" };
>>>>>>> a081628556944732175c0138f70c0c36571ba841
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columnHeadings.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeadings[i]);
        }

        int rowNum = 1;
        for (Student student : studentList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getBirthday());
            row.createCell(3).setCellValue(student.getGender());
            row.createCell(4).setCellValue(student.getPhone());
            row.createCell(5).setCellValue(student.getClazz());
            row.createCell(6).setCellValue(student.getDorm());
            row.createCell(7).setCellValue(student.getOrigin());
<<<<<<< HEAD
            // row.createCell(8).setCellValue(student.getPhotoPath());
=======
            row.createCell(8).setCellValue(student.getPhotoPath());
>>>>>>> a081628556944732175c0138f70c0c36571ba841
        }

        try (FileOutputStream fileOut = new FileOutputStream("students.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
