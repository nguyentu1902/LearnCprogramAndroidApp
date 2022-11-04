package com.example.testfirebase.Object;

public class Course {
    public int id;
    public String tenBaiHoc;

    public Course() {
    }

    public Course(int id, String tenBaiHoc) {
        this.id = id;
        this.tenBaiHoc = tenBaiHoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBaiHoc() {
        return tenBaiHoc;
    }

    public void setTenBaiHoc(String tenBaiHoc) {
        this.tenBaiHoc = tenBaiHoc;
    }
}
