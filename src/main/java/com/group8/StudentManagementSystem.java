package com.group8;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StudentManagementSystem extends JFrame {
    private ArrayList<Student> studentList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> classComboBox;

    public StudentManagementSystem() {
        setTitle("班级通讯录系统");
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

        table.setRowHeight(60);
        table.getColumn("照片").setCellRenderer(new ImageRenderer());
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
                    studentList.remove(selectedRow);
                    tableModel.removeRow(selectedRow);
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
    }

    public void addStudent(Student student) {
        studentList.add(student);
        tableModel.addRow(new Object[]{
                student.getId(),
                student.getName(),
                student.getBirthday(),
                student.getGender(),
                student.getPhone(),
                student.getClazz(),
                student.getDorm(),
                student.getOrigin(),
                student.getPhoto()
        });

        // 更新班级下拉框，避免重复添加班级
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) classComboBox.getModel();
        if (model.getIndexOf(student.getClazz()) == -1) {
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
                        student.getId(),
                        student.getName(),
                        student.getBirthday(),
                        student.getGender(),
                        student.getPhone(),
                        student.getClazz(),
                        student.getDorm(),
                        student.getOrigin(),
                        student.getPhoto()
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
    private void displayAllStudents() {
        DefaultTableModel newTableModel = new DefaultTableModel(new String[]{"学号", "姓名", "生日", "性别", "电话", "班级", "宿舍", "籍贯", "照片"}, 0);
        for (Student student : studentList) {
            newTableModel.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getBirthday(),
                    student.getGender(),
                    student.getPhone(),
                    student.getClazz(),
                    student.getDorm(),
                    student.getOrigin(),
                    student.getPhoto()
            });
        }
        table.setModel(newTableModel);
        table.getColumn("照片").setCellRenderer(new ImageRenderer());
    }

}



class ImageRenderer extends DefaultTableCellRenderer {
    private int maxWidth;
    private int maxHeight;

    public ImageRenderer(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            ImageIcon originalIcon = (ImageIcon) value;
            Image scaledImage = getScaledImage(originalIcon.getImage(), maxWidth, maxHeight);
            return new JLabel(new ImageIcon(scaledImage));
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    private Image getScaledImage(Image srcImg, int maxWidth, int maxHeight) {
        BufferedImage resizedImg = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        // Compute scaling factor
        double scaleWidth = (double) maxWidth / srcImg.getWidth(null);
        double scaleHeight = (double) maxHeight / srcImg.getHeight(null);
        double scale = Math.min(scaleWidth, scaleHeight);

        // Compute scaled dimensions
        int width = (int) (srcImg.getWidth(null) * scale);
        int height = (int) (srcImg.getHeight(null) * scale);

        // Draw the scaled image
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();

        return resizedImg;
    }
}
