package com.group8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddStudentDialog extends JDialog {
    private JTextField idField, nameField, birthdayField, genderField, phoneField, clazzField, dormField, originField,
            photoField;
    private JButton browsePhotoButton;

    public AddStudentDialog(JFrame parent) {
        super(parent, "录入学生信息", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(10, 2));

        panel.add(new JLabel("学号:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("生日:"));
        birthdayField = new JTextField();
        panel.add(birthdayField);

        panel.add(new JLabel("性别:"));
        genderField = new JTextField();
        panel.add(genderField);

        panel.add(new JLabel("电话:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("班级:"));
        clazzField = new JTextField();
        panel.add(clazzField);

        panel.add(new JLabel("宿舍:"));
        dormField = new JTextField();
        panel.add(dormField);

        panel.add(new JLabel("籍贯:"));
        originField = new JTextField();
        panel.add(originField);

        panel.add(new JLabel("照片路径:"));
        photoField = new JTextField();
        panel.add(photoField);

        browsePhotoButton = new JButton("浏览本地照片");
        browsePhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(AddStudentDialog.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    photoField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        panel.add(browsePhotoButton);

        JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                student.setId(idField.getText());
                student.setName(nameField.getText());
                student.setBirthday(birthdayField.getText());
                student.setGender(genderField.getText());
                student.setPhone(phoneField.getText());
                student.setClazz(clazzField.getText());
                student.setDorm(dormField.getText());
                student.setOrigin(originField.getText());
                student.setPhotoPath(photoField.getText());

                ((StudentManagementSystem) parent).addStudent(student);
                dispose();
            }
        });

        panel.add(saveButton);

        add(panel);
    }
}
