package com.group8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD

public class AddStudentDialog extends JDialog {
    private JTextField idField;
    private JTextField nameField;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> genderComboBox;
    private JTextField phoneField;
    private JTextField classField;
    private JTextField dormField;
    private JTextField originField;
    private JLabel photoLabel;
    private String photoPath;

    public AddStudentDialog(JFrame parent) {
        super(parent, "添加学生", true);
        setSize(400, 450);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(11, 2));
=======
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
>>>>>>> a081628556944732175c0138f70c0c36571ba841

        panel.add(new JLabel("学号:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("生日:"));
<<<<<<< HEAD
        yearComboBox = new JComboBox<>(getYearOptions());
        monthComboBox = new JComboBox<>(getMonthOptions());
        dayComboBox = new JComboBox<>(getDayOptions());
        JPanel birthdayPanel = new JPanel(new FlowLayout());
        birthdayPanel.add(yearComboBox);
        birthdayPanel.add(new JLabel("年"));
        birthdayPanel.add(monthComboBox);
        birthdayPanel.add(new JLabel("月"));
        birthdayPanel.add(dayComboBox);
        birthdayPanel.add(new JLabel("日"));
        panel.add(birthdayPanel);

        panel.add(new JLabel("性别:"));
        genderComboBox = new JComboBox<>(new String[] { "男", "女" });
        panel.add(genderComboBox);
=======
        birthdayField = new JTextField();
        panel.add(birthdayField);

        panel.add(new JLabel("性别:"));
        genderField = new JTextField();
        panel.add(genderField);
>>>>>>> a081628556944732175c0138f70c0c36571ba841

        panel.add(new JLabel("电话:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("班级:"));
<<<<<<< HEAD
        classField = new JTextField();
        panel.add(classField);
=======
        clazzField = new JTextField();
        panel.add(clazzField);
>>>>>>> a081628556944732175c0138f70c0c36571ba841

        panel.add(new JLabel("宿舍:"));
        dormField = new JTextField();
        panel.add(dormField);

        panel.add(new JLabel("籍贯:"));
        originField = new JTextField();
        panel.add(originField);

<<<<<<< HEAD
        panel.add(new JLabel("照片:"));
        photoLabel = new JLabel();
        JButton browseButton = new JButton("浏览");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(AddStudentDialog.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    photoPath = fileChooser.getSelectedFile().getAbsolutePath();
                    ImageIcon photoIcon = new ImageIcon(
                            new ImageIcon(photoPath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    photoLabel.setIcon(photoIcon);
                }
            }
        });
        JPanel photoPanel = new JPanel(new BorderLayout());
        photoPanel.add(photoLabel, BorderLayout.CENTER);
        photoPanel.add(browseButton, BorderLayout.EAST);
        panel.add(photoPanel);

        add(panel, BorderLayout.CENTER);

        JButton addButton = new JButton("添加");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idField.getText().isEmpty() || nameField.getText().isEmpty() || phoneField.getText().isEmpty()
                        || classField.getText().isEmpty() || dormField.getText().isEmpty()
                        || originField.getText().isEmpty()
                        || yearComboBox.getSelectedItem() == null || monthComboBox.getSelectedItem() == null
                        || dayComboBox.getSelectedItem() == null || genderComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(AddStudentDialog.this, "请填写所有输入框");
                    return;
                }

                String id = idField.getText();
                String name = nameField.getText();
                String birthday = yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "-"
                        + dayComboBox.getSelectedItem();
                String gender = (String) genderComboBox.getSelectedItem();
                String phone = phoneField.getText();
                String clazz = classField.getText();
                String dorm = dormField.getText();
                String origin = originField.getText();
                ImageIcon photo = (ImageIcon) photoLabel.getIcon();
                ((StudentManagementSystem) parent).addStudent(
                        new Student(id, name, birthday, gender, phone, clazz, dorm, origin, photo, photoPath));
=======
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
>>>>>>> a081628556944732175c0138f70c0c36571ba841
                dispose();
            }
        });

<<<<<<< HEAD
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String[] getYearOptions() {
        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(1925 + i);
        }
        return years;
    }

    private String[] getMonthOptions() {
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.valueOf(i + 1);
        }
        return months;
    }

    private String[] getDayOptions() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        return days;
=======
        panel.add(saveButton);

        add(panel);
>>>>>>> a081628556944732175c0138f70c0c36571ba841
    }
}
