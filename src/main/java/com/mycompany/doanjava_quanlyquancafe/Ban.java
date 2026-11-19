package com.mycompany.doanjava_quanlyquancafe;

public class Ban {
    private int stt;
    private String tenBan;
    private String trangThai;

    public Ban(int stt, String tenBan, String trangThai) {
        this.stt = stt;
        this.tenBan = tenBan;
        this.trangThai = trangThai;
    }

    public int getStt() { return stt; }
    public String getTenBan() { return tenBan; }
    public String getTrangThai() { return trangThai; }
    
    @Override
    public String toString() {
        return tenBan;
    }
}