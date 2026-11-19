package com.mycompany.doanjava_quanlyquancafe;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Douong {
    private SimpleIntegerProperty stt;
    private SimpleStringProperty tenDoUong;
    private SimpleDoubleProperty giaCoBan;
    private SimpleIntegerProperty sttDanhMuc;

    public Douong(int stt, String tenDoUong, double giaCoBan, int sttDanhMuc) {
        this.stt = new SimpleIntegerProperty(stt);
        this.tenDoUong = new SimpleStringProperty(tenDoUong);
        this.giaCoBan = new SimpleDoubleProperty(giaCoBan);
        this.sttDanhMuc = new SimpleIntegerProperty(sttDanhMuc);
    }

    public int getStt() { return stt.get(); }
    public String getTenDoUong() { return tenDoUong.get(); }
    public double getGiaCoBan() { return giaCoBan.get(); }
    public int getSttDanhMuc() { return sttDanhMuc.get(); }
}