package com.mycompany.doanjava_quanlyquancafe;

public class Giohangvatpham {
    private Douong doUong;
    private int soLuong;
    private double thanhTien;

    public Giohangvatpham(Douong doUong, int soLuong) {
        this.doUong = doUong;
        this.soLuong = soLuong;
        this.thanhTien = doUong.getGiaCoBan() * soLuong;
    }

    public Douong getDoUong() { return doUong; }
    public String getTenDoUong() { return doUong.getTenDoUong(); }
    public double getDonGia() { return doUong.getGiaCoBan(); }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { 
        this.soLuong = soLuong; 
        this.thanhTien = this.doUong.getGiaCoBan() * soLuong;
    }
    public double getThanhTien() { return thanhTien; }
}