package com.group8;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

public class AddStudentDialog extends JDialog {
    private JTextField idField, nameField, genderField, phoneField, clazzField, dormField, originField, photoField;
    private JComboBox<Integer> yearComboBox, monthComboBox, dayComboBox;
    private JButton browsePhotoButton;

    public AddStudentDialog(JFrame parent) {
        super(parent, "录入学生信息", true);
        setSize(400, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(11, 2));

        panel.add(new JLabel("学号:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        panel.add(nameField);

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

        panel.add(new JLabel("生日:"));
        JPanel birthdayPanel = new JPanel(new FlowLayout());
        yearComboBox = new JComboBox<>();
        monthComboBox = new JComboBox<>();
        dayComboBox = new JComboBox<>();
        populateDateComboboxes();
        birthdayPanel.add(yearComboBox);
        birthdayPanel.add(new JLabel("年"));
        birthdayPanel.add(monthComboBox);
        birthdayPanel.add(new JLabel("月"));
        birthdayPanel.add(dayComboBox);
        birthdayPanel.add(new JLabel("日"));
        panel.add(birthdayPanel);

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
                if (idField.getText().equals("") || nameField.getText().equals("") || genderField.getText().equals("") || phoneField.getText().equals("") || clazzField.getText().equals("") || dormField.getText().equals("") || originField.getText().equals("") || photoField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "部分内容尚未填写", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Student student = new Student();
                student.setId(idField.getText());
                student.setName(nameField.getText());
                student.setGender(genderField.getText());
                student.setPhone(phoneField.getText());
                student.setClazz(clazzField.getText());
                student.setDorm(dormField.getText());
                student.setOrigin(originField.getText());
                student.setPhotoPath(photoField.getText());

                int year = (int) yearComboBox.getSelectedItem();
                int month = (int) monthComboBox.getSelectedItem();
                int day = (int) dayComboBox.getSelectedItem();
                student.setBirthday(year + "-" + month + "-" + day);

                ((StudentManagementSystem) parent).addStudent(student);
                dispose();
            }
        });

        panel.add(saveButton);

        add(panel);
    }

    private void populateDateComboboxes() {
        // Populate year combobox
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= 1900; i--) {
            yearComboBox.addItem(i);
        }

        // Populate month combobox
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(i);
        }

        // Populate day combobox
        updateDayComboBox();

        // Add action listeners to year and month comboboxes to update the day combobox
        yearComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDayComboBox();
            }
        });

        monthComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDayComboBox();
            }
        });
    }

    private void updateDayComboBox() {
        int year = (int) yearComboBox.getSelectedItem();
        int month = (int) monthComboBox.getSelectedItem();
        int daysInMonth = getDaysInMonth(year, month);

        dayComboBox.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            dayComboBox.addItem(i);
        }
    }

    private int getDaysInMonth(int year, int month) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 31;
        }
    }

    private boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
