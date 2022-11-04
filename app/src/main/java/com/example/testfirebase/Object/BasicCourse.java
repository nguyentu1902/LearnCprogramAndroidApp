package com.example.testfirebase.Object;

public class BasicCourse {
    private int reSourceImg;
    private String nameOfBasicCourse;

    public BasicCourse(int reSourceImg, String nameOfBasicCourse) {
        this.reSourceImg = reSourceImg;
        this.nameOfBasicCourse = nameOfBasicCourse;
    }

    public BasicCourse() {
    }

    public int getReSourceImg() {
        return reSourceImg;
    }

    public void setReSourceImg(int reSourceImg) {
        this.reSourceImg = reSourceImg;
    }

    public String getNameOfBasicCourse() {
        return nameOfBasicCourse;
    }

    public void setNameOfBasicCourse(String nameOfBasicCourse) {
        this.nameOfBasicCourse = nameOfBasicCourse;
    }
}
