package com.example.projectfortesting;

public class ChapTruyen {
    private String tenChap,linkChap;

    public ChapTruyen(String tenChap, String linkChap) {
        this.tenChap = tenChap;
        this.linkChap = linkChap;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkChap() {
        return linkChap;
    }

    public void setLinkChap(String linkChap) {
        this.linkChap = linkChap;
    }
}
