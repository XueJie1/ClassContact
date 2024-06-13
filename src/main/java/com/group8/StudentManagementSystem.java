package com.group8;

import javax.swing.*;
<<<<<<< HEAD
=======
import javax.swing.table.DefaultTableCellRenderer;
>>>>>>> a081628556944732175c0138f70c0c36571ba841
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentManagementSystem extends JFrame {
<<<<<<< HEAD
    private DefaultTableModel tableModel;
    private JTable table;
    private java.util.List<Student> studentList = new ArrayList<>();
=======
    private ArrayList<Student> studentList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
>>>>>>> a081628556944732175c0138f70c0c36571ba841
    private JComboBox<String> classComboBox;

    public StudentManagementSystem() {
        setTitle("学生信息管理系统");
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

<<<<<<< HEAD
        int maxWidth = 100; // 定义照片列的最大宽度
        int maxHeight = 100; // 定义照片列的最大高度

        table.setRowHeight(maxHeight); // 设置表格行高
        table.getColumn("照片").setCellRenderer(new ImageRenderer(maxWidth, maxHeight));
=======
        table.setRowHeight(60);
        table.getColumn("照片").setCellRenderer(new ImageRenderer());
>>>>>>> a081628556944732175c0138f70c0c36571ba841
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
<<<<<<< HEAD
                    // 从学生列表中删除
                    studentList.remove(selectedRow);
                    // 从表格模型中删除行
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(StudentManagementSystem.this, "请选择要删除的行");
=======
                    studentList.remove(selectedRow);
                    tableModel.removeRow(selectedRow);
>>>>>>> a081628556944732175c0138f70c0c36571ba841
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
                ExportToExcel.export(studentList);
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
<<<<<<< HEAD
        classComboBox.addItem("显示所有班级"); // 添加 "显示所有班级" 选项
=======
>>>>>>> a081628556944732175c0138f70c0c36571ba841
        JButton showClassButton = new JButton("显示班级学生");
        showClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classComboBox.getSelectedItem();
<<<<<<< HEAD
                if ("显示所有班级".equals(selectedClass)) {
                    displayAllStudents();
                } else {
                    searchStudent("按班级", selectedClass);
                }
=======
                searchStudent("按班级", selectedClass);
>>>>>>> a081628556944732175c0138f70c0c36571ba841
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
    }

    public void addStudent(Student student) {
        studentList.add(student);
<<<<<<< HEAD
        Object[] rowData = {
=======
        tableModel.addRow(new Object[] {
>>>>>>> a081628556944732175c0138f70c0c36571ba841
                student.getId(),
                student.getName(),
                student.getBirthday(),
                student.getGender(),
                student.getPhone(),
                student.getClazz(),
                student.getDorm(),
                student.getOrigin(),
                student.getPhoto()
<<<<<<< HEAD
        };
        tableModel.addRow(rowData);
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
=======
        });

        // 更新班级下拉框
        if (((DefaultComboBoxModel<String>) classComboBox.getModel()).getIndexOf(student.getClazz()) == -1) {
            classComboBox.addItem(student.getClazz());
        }
    }

    public void searchStudent(String searchType, String searchText) {
        DefaultTableModel newTableModel = new DefaultTableModel(
                new String[] { "学号", "姓名", "生日", "性别", "电话", "班级", "宿舍", "籍贯", "照片" }, 0);

        for (Student student : studentList) {
            boolean matches = false;
            switch (searchType) {
                case "按姓名":
                    if (student.getName().contains(searchText)) {
                        matches = true;
                    }
                    break;
                case "按电话号码":
                    if (student.getPhone().contains(searchText)) {
                        matches = true;
                    }
                    break;
                case "按班级":
                    if (student.getClazz().contains(searchText)) {
                        matches = true;
                    }
                    break;
            }

            if (matches) {
                newTableModel.addRow(new Object[] {
>>>>>>> a081628556944732175c0138f70c0c36571ba841
                        student.getId(),
                        student.getName(),
                        student.getBirthday(),
                        student.getGender(),
                        student.getPhone(),
                        student.getClazz(),
                        student.getDorm(),
                        student.getOrigin(),
                        student.getPhoto()
<<<<<<< HEAD
                };
                tableModel.addRow(rowData);
            }
        }
=======
                });
            }
        }

        table.setModel(newTableModel);
        table.getColumn("照片").setCellRenderer(new ImageRenderer());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }
}

class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (value instanceof ImageIcon) {
            return new JLabel((ImageIcon) value);
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
>>>>>>> a081628556944732175c0138f70c0c36571ba841
    }
}
