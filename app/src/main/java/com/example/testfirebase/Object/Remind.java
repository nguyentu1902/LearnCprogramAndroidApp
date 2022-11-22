package com.example.testfirebase.Object;

public class Remind {
    int resourceImg;
    String nameRemind;

    public Remind(int resourceImg, String nameRemind) {
        this.resourceImg = resourceImg;
        this.nameRemind = nameRemind;
    }

    public Remind() {
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(int resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getNameRemind() {
        return nameRemind;
    }

    public void setNameRemind(String nameRemind) {
        this.nameRemind = nameRemind;
    }
}
