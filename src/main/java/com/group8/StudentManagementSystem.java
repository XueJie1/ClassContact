package com.group8;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentManagementSystem extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Student> studentList = new ArrayList<>();
    private JComboBox<String> classComboBox;

    // private ImageIcon icon = new
    // ImageIcon(getClass().getResource("src/main/resources/images/"));

    public StudentManagementSystem() {
        setTitle("学生通讯录管理");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建主面板
        JPanel panel = new JPanel(new BorderLayout());

        // 创建表格模型和表格
        String[] columnNames = { "学号", "姓名", "生日", "性别", "电话", "班级", "宿舍", "籍贯", "照片" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 8) { // 照片列
                    return ImageIcon.class;
                } else {
                    return String.class;
                }
            }
        };

        int maxWidth = 100; // 定义照片列的最大宽度
        int maxHeight = 100; // 定义照片列的最大高度

        table.setRowHeight(maxHeight); // 设置表格行高
        table.getColumn("照片").setCellRenderer(new ImageRenderer(maxWidth, maxHeight));
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加录入按钮
        JButton addButton = new JButton("录入");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddStudentDialog(StudentManagementSystem.this).setVisible(true);
            }
        });

        // 添加删除按钮
        JButton deleteButton = new JButton("删除");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // 从学生列表中删除
                    studentList.remove(selectedRow);
                    // 从表格模型中删除行
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(StudentManagementSystem.this, "请选择要删除的行");
                }
            }
        });

        // 添加查询按钮
        JButton searchButton = new JButton("查询");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchStudentDialog(StudentManagementSystem.this).setVisible(true);
            }
        });

        // 添加导出到Excel按钮
        JButton exportExcelButton = new JButton("导出Excel");
        exportExcelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExportToExcelPic.export(studentList);
            }
        });

        // 添加导出到Word按钮
        JButton exportWordButton = new JButton("导出Word");
        exportWordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExportToWord.export(studentList);
            }
        });

        // 添加按班级显示学生信息的下拉框和按钮
        classComboBox = new JComboBox<>();
        classComboBox.addItem("显示所有班级"); // 添加 "显示所有班级" 选项
        JButton showClassButton = new JButton("显示班级学生");
        showClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classComboBox.getSelectedItem();
                if ("显示所有班级".equals(selectedClass)) {
                    displayAllStudents();
                } else {
                    searchStudent("按班级", selectedClass);
                }
            }
        });

        // 添加面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(exportExcelButton);
        buttonPanel.add(exportWordButton);
        buttonPanel.add(classComboBox);
        buttonPanel.add(showClassButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // 初始化数据
        initData();
    }

    private void initData() {
        String stu1PicPath = "src/main/resources/images/月下铸币大头.png";

        String stu2PicPath = "src/main/resources/images/琪亚娜铸币大头3.png";
        String stu3PicPath = "src/main/resources/images/观星铸币大头1.png";
        ImageIcon stu1Icon = new ImageIcon(stu1PicPath);

        ImageIcon stu2Icon = new ImageIcon(stu2PicPath);
        ImageIcon stu3Icon = new ImageIcon(stu3PicPath);
        // 示例学生数据
        Student student1 = new Student("001", "张三", "2000-01-01", "女", "1234567890", "软件工程", "101", "北京", stu1Icon,
                stu1PicPath);
        Student student2 = new Student("002", "李四", "2001-02-02", "女", "0987654321", "计算机科学", "102", "上海", stu2Icon,
                stu2PicPath);
        Student student3 = new Student("003", "王五", "2002-03-03", "女", "1122334455", "网络工程", "103", "广州", stu3Icon,
                stu3PicPath);

        // 添加学生到列表和表格
        addStudent(student1);
        addStudent(student2);
        addStudent(student3);
    }

    public void addStudent(Student student) {
        studentList.add(student);
        Object[] rowData = {
                student.getId(),
                student.getName(),
                student.getBirthday(),
                student.getGender(),
                student.getPhone(),
                student.getClazz(),
                student.getDorm(),
                student.getOrigin(),
                student.getPhoto()
        };
        tableModel.addRow(rowData);

        // 更新班级下拉框
        updateClassComboBox(student.getClazz());
    }

    private void updateClassComboBox(String newClass) {
        boolean classExists = false;
        for (int i = 0; i < classComboBox.getItemCount(); i++) {
            if (classComboBox.getItemAt(i).equals(newClass)) {
                classExists = true;
                break;
            }
        }
        if (!classExists) {
            classComboBox.addItem(newClass);
        }
    }

    private void displayAllStudents() {
        tableModel.setRowCount(0);
        for (Student student : studentList) {
            Object[] rowData = {
                    student.getId(),
                    student.getName(),
                    student.getBirthday(),
                    student.getGender(),
                    student.getPhone(),
                    student.getClazz(),
                    student.getDorm(),
                    student.getOrigin(),
                    student.getPhoto()
            };
            tableModel.addRow(rowData);
        }
    }

    public void searchStudent(String criteria, String value) {
        tableModel.setRowCount(0);
        for (Student student : studentList) {
            boolean matches = false;
            if ("按班级".equals(criteria) && student.getClazz().equals(value)) {
                matches = true;
            } else if ("按姓名".equals(criteria) && student.getName().contains(value)) {
                matches = true;
            } else if ("按电话号码".equals(criteria) && student.getPhone().contains(value)) {
                matches = true;
            }
            // 其他查询条件
            if (matches) {
                Object[] rowData = {
                        student.getId(),
                        student.getName(),
                        student.getBirthday(),
                        student.getGender(),
                        student.getPhone(),
                        student.getClazz(),
                        student.getDorm(),
                        student.getOrigin(),
                        student.getPhoto()
                };
                tableModel.addRow(rowData);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }
}
