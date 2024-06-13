package com.group8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        panel.add(new JLabel("学号:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("生日:"));
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

        panel.add(new JLabel("电话:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("班级:"));
        classField = new JTextField();
        panel.add(classField);

        panel.add(new JLabel("宿舍:"));
        dormField = new JTextField();
        panel.add(dormField);

        panel.add(new JLabel("籍贯:"));
        originField = new JTextField();
        panel.add(originField);

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
                dispose();
            }
        });

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
    }
}
