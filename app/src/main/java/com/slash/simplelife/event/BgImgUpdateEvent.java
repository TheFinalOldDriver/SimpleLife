package com.slash.simplelife.event;

public class BgImgUpdateEvent {
    public String bgImg;

    public BgImgUpdateEvent(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }
}
