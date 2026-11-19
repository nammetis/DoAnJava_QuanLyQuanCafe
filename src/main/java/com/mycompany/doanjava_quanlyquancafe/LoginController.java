package com.mycompany.doanjava_quanlyquancafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblMessage;

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            lblMessage.setText("Vui lòng nhập đầy đủ tài khoản và mật khẩu!");
            return;
        }
        
        try (Connection conn = KetnoiDB.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM tai_khoan WHERE Ten_dang_nhap = ? AND Mat_khau = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                
                ResultSet rs = pst.executeQuery();
                
                 if (rs.next()) {
                    String role = rs.getString("role");
                    try {
                        Stage stage = (Stage) txtUsername.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                        Parent root = loader.load();
                        MainController mainController = loader.getController();
                        mainController.setUserRole(role); 
                        
                        stage.setScene(new Scene(root, 1050, 650)); 
                        stage.setTitle("Hệ Thống Quản Lý Quán Cafe - Trang Chủ");
                        stage.centerOnScreen();
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                        lblMessage.setText("Lỗi: Không tìm thấy file main.fxml!");
                    }
                } else {
                    lblMessage.setText("Tài khoản hoặc mật khẩu không đúng!");
                }
            } else {
                lblMessage.setText("Lỗi kết nối Database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblMessage.setText("Đã xảy ra lỗi hệ thống!");
        }
    }
}