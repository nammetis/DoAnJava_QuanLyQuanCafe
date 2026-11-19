package com.mycompany.doanjava_quanlyquancafe;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class QuanLyBanController {

    @FXML
    private FlowPane paneBan;

    @FXML
    public void initialize() {
        loadDanhSachBan();
    }

    private void loadDanhSachBan() {
        paneBan.getChildren().clear();
        Bandata dao = new Bandata();
        
        for (Ban ban : dao.getAll()) {
            Button btnBan = new Button(ban.getTenBan() + "\n(" + ban.getTrangThai() + ")");
btnBan.setPrefSize(140, 120); 
btnBan.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);");

    if (ban.getTrangThai().equals("Trống")) {
    btnBan.setStyle(btnBan.getStyle() + "-fx-background-color: linear-gradient(to bottom right, #66BB6A, #43A047); -fx-text-fill: white;");
    } else {
    btnBan.setStyle(btnBan.getStyle() + "-fx-background-color: linear-gradient(to bottom right, #EF5350, #E53935); -fx-text-fill: white;");
    }
            btnBan.setOnAction(e -> {
                String trangThaiMoi = ban.getTrangThai().equals("Trống") ? "Có khách" : "Trống";
                
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Xác nhận");
                confirm.setHeaderText(null);
                confirm.setContentText("Chuyển trạng thái " + ban.getTenBan() + " sang: " + trangThaiMoi + "?");
                
                confirm.showAndWait().ifPresent(response -> {
                    if (response == javafx.scene.control.ButtonType.OK) {
                        dao.updateTrangThai(ban.getStt(), trangThaiMoi);
                        loadDanhSachBan(); // Load lại giao diện để cập nhật màu
                    }
                });
            });

            paneBan.getChildren().add(btnBan);
        }
    }
}