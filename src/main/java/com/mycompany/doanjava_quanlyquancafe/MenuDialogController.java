package com.mycompany.doanjava_quanlyquancafe;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuDialogController {

    @FXML private TextField txtTenMon;
    @FXML private TextField txtGiaMon;
    @FXML private ComboBox<Danhmuc> cboDanhMuc;

    private Douong monDangSua = null; 

    @FXML
    public void initialize() {
        cboDanhMuc.setItems(FXCollections.observableArrayList(new Danhmucdata().getAll()));
    }

    public void setMonDangSua(Douong mon) {
        this.monDangSua = mon;
        txtTenMon.setText(mon.getTenDoUong());
        txtGiaMon.setText(String.valueOf(mon.getGiaCoBan()));
        
        for (Danhmuc dm : cboDanhMuc.getItems()) {
            if (dm.getStt() == mon.getSttDanhMuc()) {
                cboDanhMuc.setValue(dm);
                break;
            }
        }
    }

    @FXML
    private void handleLuu() {
        String ten = txtTenMon.getText().trim();
        String giaStr = txtGiaMon.getText().trim();
        Danhmuc dm = cboDanhMuc.getValue();

        if (ten.isEmpty() || giaStr.isEmpty() || dm == null) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Vui lòng nhập đầy đủ thông tin!");
            a.show();
            return;
        }

        try {
            double gia = Double.parseDouble(giaStr);
            Douongdata dao = new Douongdata();

            if (monDangSua == null) {
                dao.insert(ten, gia, dm.getStt());
            } else {
                dao.update(monDangSua.getStt(), ten, gia, dm.getStt());
            }

            Stage stage = (Stage) txtTenMon.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Giá bán phải là số!");
            a.show();
        }
    }

    @FXML
    private void handleHuy() {
        Stage stage = (Stage) txtTenMon.getScene().getWindow();
        stage.close();
    }
}