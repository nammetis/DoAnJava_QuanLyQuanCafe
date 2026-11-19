package com.mycompany.doanjava_quanlyquancafe;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class MainController {

    @FXML private StackPane mainContent;
    @FXML private Button btnQuanLyMenu;
    @FXML private Button btnQuanLyBan;
    @FXML private Button btnThongKe;
    
    public void setUserRole(String role) {
        if (role == null || !role.equals("ADMIN")) {
            btnQuanLyMenu.setVisible(false);
            btnQuanLyBan.setVisible(false);
            btnThongKe.setVisible(false);
            btnQuanLyMenu.setManaged(false);
            btnQuanLyBan.setManaged(false);
            btnThongKe.setManaged(false);
        }
    }

    @FXML
    private void handleBanHang() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pos.fxml"));
            Parent root = loader.load();
            mainContent.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            mainContent.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleQuanLyMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            Parent root = loader.load();
            mainContent.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            mainContent.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @FXML
    private void handleQuanLyBan() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/quanlyban.fxml"));
            Parent root = loader.load();
            mainContent.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            mainContent.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @FXML
    private void handleThongKe() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/thongke.fxml"));
            Parent root = loader.load();
            mainContent.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            mainContent.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) mainContent.getScene().getWindow();
            currentStage.setScene(new Scene(root, 1050, 650)); 
            currentStage.setTitle("Hệ Thống Quản Lý Quán Cafe");
            currentStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
