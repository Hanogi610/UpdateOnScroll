package com.example.projectfortesting;

public class TruyenKhamPha {
    private String tenTruyen,linkAnh,detailUrl;

    public TruyenKhamPha(String tenTruyen, String linkAnh,String detailUrl) {
        this.tenTruyen = tenTruyen;
        this.linkAnh = linkAnh;
        this.detailUrl = detailUrl;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTryen) {
        this.tenTruyen = tenTryen;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
