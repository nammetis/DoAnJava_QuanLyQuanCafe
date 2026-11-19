package com.mycompany.doanjava_quanlyquancafe;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ThongKeVatLieu {
    private SimpleStringProperty tenMon;
    private SimpleIntegerProperty soLuong;

    public ThongKeVatLieu(String tenMon, int soLuong) {
        this.tenMon = new SimpleStringProperty(tenMon);
        this.soLuong = new SimpleIntegerProperty(soLuong);
    }

    public String getTenMon() { return tenMon.get(); }
    public int getSoLuong() { return soLuong.get(); }
}