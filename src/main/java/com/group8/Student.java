package com.group8;

import javax.swing.ImageIcon;

public class Student {
    private String id;
    private String name;
    private String birthday;
    private String gender;
    private String phone;
    private String clazz;
    private String dorm;
    private String origin;
<<<<<<< HEAD
    private ImageIcon photo;
    private String photoPath; // 添加照片路径字段

    public Student(String id, String name, String birthday, String gender, String phone, String clazz, String dorm,
            String origin, ImageIcon photo, String photoPath) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.clazz = clazz;
        this.dorm = dorm;
        this.origin = origin;
        this.photo = photo;
        this.photoPath = photoPath; // 初始化照片路径字段
    }

    // Getter 和 Setter 方法
=======
    private String photoPath;
    private ImageIcon photo;

    // Getters and Setters
>>>>>>> a081628556944732175c0138f70c0c36571ba841
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

<<<<<<< HEAD
    public ImageIcon getPhoto() {
        return photo;
    }

    public void setPhoto(ImageIcon photo) {
        this.photo = photo;
    }

    public String getPhotoPath() {
        return photoPath; // 添加照片路径的 getter 方法
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath; // 添加照片路径的 setter 方法
=======
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        this.photo = new ImageIcon(photoPath);
    }

    public ImageIcon getPhoto() {
        return photo;
>>>>>>> a081628556944732175c0138f70c0c36571ba841
    }
}
