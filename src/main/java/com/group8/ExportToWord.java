package com.group8;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportToWord {
    public static void export(List<Student> studentList) {
        XWPFDocument document = new XWPFDocument();

        for (Student student : studentList) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("学号: " + student.getId());
            run.addBreak();
            run.setText("姓名: " + student.getName());
            run.addBreak();
            run.setText("生日: " + student.getBirthday());
            run.addBreak();
            run.setText("性别: " + student.getGender());
            run.addBreak();
            run.setText("电话: " + student.getPhone());
            run.addBreak();
            run.setText("班级: " + student.getClazz());
            run.addBreak();
            run.setText("宿舍: " + student.getDorm());
            run.addBreak();
            run.setText("籍贯: " + student.getOrigin());
            run.addBreak();
            run.setText("照片路径: " + student.getPhotoPath());
            run.addBreak();
            run.addBreak();
        }

        try (FileOutputStream out = new FileOutputStream("students.docx")) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
