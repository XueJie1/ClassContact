package com.group8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchStudentDialog extends JDialog {
    private JTextField searchField;
    private JComboBox<String> searchTypeComboBox;

    public SearchStudentDialog(JFrame parent) {
        super(parent, "查询学生信息", true);
        setSize(400, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("查询类型:"));
        searchTypeComboBox = new JComboBox<>(new String[] { "按姓名", "按电话号码", "按班级" });
        panel.add(searchTypeComboBox);

        panel.add(new JLabel("查询内容:"));
        searchField = new JTextField();
        panel.add(searchField);

        JButton searchButton = new JButton("查询");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchType = (String) searchTypeComboBox.getSelectedItem();
                String searchText = searchField.getText();
                ((StudentManagementSystem) parent).searchStudent(searchType, searchText);
                dispose();
            }
        });

        panel.add(searchButton);

        add(panel);
    }
}
