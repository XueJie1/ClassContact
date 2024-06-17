package com.group8;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
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

            // Add pic start
            try {
                File image = new File(student.getPhotoPath());
                if (image.exists()) {
                    FileInputStream imageData = new FileInputStream(image);
                    String imageFileName = image.getName().toLowerCase();
                    int imageType = getImageType(imageFileName);
                    int width = Units.toEMU(100); // Adjust width as needed
                    int height = Units.toEMU(100); // Adjust height as needed
                    run.addPicture(imageData, imageType, image.getName(), width, height);
                    imageData.close(); // Ensure to close the FileInputStream
                } else {
                    System.out.println("Image file not found: " + student.getPhotoPath());
                }
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Add pic end
        }

        try (FileOutputStream out = new FileOutputStream("students.docx")) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getImageType(String imageFileName) {
        if (imageFileName.endsWith(".emf"))
            return XWPFDocument.PICTURE_TYPE_EMF;
        else if (imageFileName.endsWith(".wmf"))
            return XWPFDocument.PICTURE_TYPE_WMF;
        else if (imageFileName.endsWith(".pict"))
            return XWPFDocument.PICTURE_TYPE_PICT;
        else if (imageFileName.endsWith(".jpeg") || imageFileName.endsWith(".jpg"))
            return XWPFDocument.PICTURE_TYPE_JPEG;
        else if (imageFileName.endsWith(".png"))
            return XWPFDocument.PICTURE_TYPE_PNG;
        else if (imageFileName.endsWith(".dib"))
            return XWPFDocument.PICTURE_TYPE_DIB;
        else if (imageFileName.endsWith(".gif"))
            return XWPFDocument.PICTURE_TYPE_GIF;
        else if (imageFileName.endsWith(".tiff"))
            return XWPFDocument.PICTURE_TYPE_TIFF;
        else if (imageFileName.endsWith(".eps"))
            return XWPFDocument.PICTURE_TYPE_EPS;
        else if (imageFileName.endsWith(".bmp"))
            return XWPFDocument.PICTURE_TYPE_BMP;
        else if (imageFileName.endsWith(".wpg"))
            return XWPFDocument.PICTURE_TYPE_WPG;
        else
            return XWPFDocument.PICTURE_TYPE_PNG;
    }
}
