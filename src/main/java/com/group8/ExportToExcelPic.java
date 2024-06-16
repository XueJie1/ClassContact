package com.group8;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExportToExcelPic {
    public static void export(List<Student> studentList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息");

        String[] columnHeadings = { "学号", "姓名", "生日", "性别", "电话", "班级", "宿舍", "籍贯", "照片" };
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
            row.createCell(8).setCellValue(student.getPhotoPath());

            try (InputStream inputStream1 = new FileInputStream(student.getPhotoPath())) {
                byte[] inputImageBytes1 = IOUtils.toByteArray(inputStream1);
                int inputImagePictureID1 = workbook.addPicture(inputImageBytes1, Workbook.PICTURE_TYPE_PNG);
                XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();

                // 使用 XSSFClientAnchor 调整图片大小，保持1:1比例
                XSSFClientAnchor picAnchor = new XSSFClientAnchor(0, 0, 255, 255, 9, rowNum - 1, 10, rowNum);
                drawing.createPicture(picAnchor, inputImagePictureID1);

                // 调整列宽和行高以适应1:1的图片比例
                sheet.setColumnWidth(9, 256 * 15); // 设置列宽
                row.setHeightInPoints(80); // 设置行高
            } catch (IOException e) {
                e.printStackTrace();
                if (e instanceof FileNotFoundException) {
                    JOptionPane.showMessageDialog(null, "文件被占用", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } finally {
                System.out.println("Image processing completed for student: " + student.getName());
            }

        }

        try (FileOutputStream fileOut = new FileOutputStream("students.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
