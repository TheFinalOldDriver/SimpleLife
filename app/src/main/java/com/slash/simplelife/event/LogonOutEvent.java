package com.slash.simplelife.event;

public class LogonOutEvent {
    public String bgImg;

    public LogonOutEvent(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }
}
