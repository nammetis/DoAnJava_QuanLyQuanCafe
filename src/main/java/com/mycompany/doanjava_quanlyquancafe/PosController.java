package com.mycompany.doanjava_quanlyquancafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PosController {

    @FXML private TableView<Douong> tblDoUong;
    @FXML private TableColumn<Douong, Integer> colStt;
    @FXML private TableColumn<Douong, String> colTen;
    @FXML private TableColumn<Douong, Double> colGia;

    @FXML private TableView<Giohangvatpham> tblGioHang;
    @FXML private TableColumn<Giohangvatpham, String> colGioTen;
    @FXML private TableColumn<Giohangvatpham, Integer> colGioSL;
    @FXML private TableColumn<Giohangvatpham, Double> colGioGia;

    @FXML private Label lblTongTien;
    @FXML private ComboBox<Ban> cboBan;

    private ObservableList<Giohangvatpham> dsGioHang = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colStt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        colTen.setCellValueFactory(new PropertyValueFactory<>("tenDoUong"));
        colGia.setCellValueFactory(new PropertyValueFactory<>("giaCoBan"));
        tblDoUong.setItems(FXCollections.observableArrayList(new Douongdata().getAll()));

        colGioTen.setCellValueFactory(new PropertyValueFactory<>("tenDoUong"));
        colGioSL.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        colGioGia.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
        tblGioHang.setItems(dsGioHang);

        cboBan.setItems(FXCollections.observableArrayList(new Bandata().getAll()));

        ContextMenu contextMenu = new ContextMenu();
        MenuItem tangSL = new MenuItem("Tăng số lượng (+1)");
        tangSL.setOnAction(e -> {
            Giohangvatpham mon = tblGioHang.getSelectionModel().getSelectedItem();
            if (mon != null) {
                mon.setSoLuong(mon.getSoLuong() + 1);
                tblGioHang.refresh();
                tinhTongTien();
            }
        });

        MenuItem giamSL = new MenuItem("Giảm số lượng (-1)");
        giamSL.setOnAction(e -> {
            Giohangvatpham mon = tblGioHang.getSelectionModel().getSelectedItem();
            if (mon != null) {
                if (mon.getSoLuong() > 1) {
                    mon.setSoLuong(mon.getSoLuong() - 1);
                    tblGioHang.refresh();
                    tinhTongTien();
                } else {
                    dsGioHang.remove(mon);
                    tinhTongTien();
                }
            }
        });

        MenuItem xoaMon = new MenuItem("Xóa món này");
        xoaMon.setOnAction(e -> {
            Giohangvatpham mon = tblGioHang.getSelectionModel().getSelectedItem();
            if (mon != null) {
                dsGioHang.remove(mon);
                tinhTongTien();
            }
        });

        contextMenu.getItems().addAll(tangSL, giamSL, xoaMon);
        tblGioHang.setContextMenu(contextMenu);

        tblDoUong.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { 
                Douong monDuocChon = tblDoUong.getSelectionModel().getSelectedItem();
                if (monDuocChon != null) {
                    themVaoGioHang(monDuocChon);
                }
            }
        });
    }

    private void themVaoGioHang(Douong mon) {
        for (Giohangvatpham item : dsGioHang) {
            if (item.getDoUong().getStt() == mon.getStt()) {
                item.setSoLuong(item.getSoLuong() + 1);
                tblGioHang.refresh(); 
                tinhTongTien();
                return;
            }
        }
        dsGioHang.add(new Giohangvatpham(mon, 1));
        tinhTongTien();
    }

    private void tinhTongTien() {
        double tong = 0;
        for (Giohangvatpham item : dsGioHang) {
            tong += item.getThanhTien();
        }
        lblTongTien.setText(String.format("%,.0f VNĐ", tong));
    }

    @FXML
    private void handleXoaTatCa() {
        dsGioHang.clear();
        tinhTongTien();
    }

    @FXML
    private void handleThanhToan() {
        if (dsGioHang.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Giỏ hàng đang trống!");
            return;
        }
        if (cboBan.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn bàn trước khi thanh toán!");
            return;
        }

        double tongTien = 0;
        for (Giohangvatpham item : dsGioHang) {
            tongTien += item.getThanhTien();
        }

        try (Connection conn = KetnoiDB.getConnection()) {
            conn.setAutoCommit(false); 

            String sqlHD = "INSERT INTO hoa_don (stt_nhanvien, stt_ban, tong_tien, thanh_tien, phuong_thuc_thanh_toan) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstHD = conn.prepareStatement(sqlHD, Statement.RETURN_GENERATED_KEYS);
            pstHD.setInt(1, 1); 
            pstHD.setInt(2, cboBan.getValue().getStt());
            pstHD.setDouble(3, tongTien);
            pstHD.setDouble(4, tongTien);
            pstHD.setString(5, "Tiền mặt");
            pstHD.executeUpdate();

            ResultSet rsHD = pstHD.getGeneratedKeys();
            int maHD = 0;
            if (rsHD.next()) {
                maHD = rsHD.getInt(1);
            }

            String sqlCT = "INSERT INTO chi_tiet_hoa_don (stt_hoa_don, stt_do_uong, so_luong, don_gia, thanh_tien) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstCT = conn.prepareStatement(sqlCT);
            for (Giohangvatpham item : dsGioHang) {
                pstCT.setInt(1, maHD);
                pstCT.setInt(2, item.getDoUong().getStt());
                pstCT.setInt(3, item.getSoLuong());
                pstCT.setDouble(4, item.getDonGia());
                pstCT.setDouble(5, item.getThanhTien());
                pstCT.executeUpdate();
            }

            String sqlBan = "UPDATE ban SET Trang_thai = N'Có khách' WHERE stt = ?";
            PreparedStatement pstBan = conn.prepareStatement(sqlBan);
            pstBan.setInt(1, cboBan.getValue().getStt());
            pstBan.executeUpdate();

            conn.commit(); 
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Thanh toán thành công! Tổng tiền: " + String.format("%,.0f VNĐ", tongTien));
            
            dsGioHang.clear();
            tinhTongTien();
            cboBan.setItems(FXCollections.observableArrayList(new Bandata().getAll()));
            cboBan.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Có lỗi xảy ra trong quá trình thanh toán!");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}