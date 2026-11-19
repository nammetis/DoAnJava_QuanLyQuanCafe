package com.mycompany.doanjava_quanlyquancafe;

public class Danhmuc {
    private int stt;
    private String tenDanhMuc;

    public Danhmuc(int stt, String tenDanhMuc) {
        this.stt = stt;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getStt() { return stt; }
    public String getTenDanhMuc() { return tenDanhMuc; }

    @Override
    public String toString() {
        return tenDanhMuc; 
    }
}