package com.mycompany.doanjava_quanlyquancafe;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ThongKeController {

    @FXML private Label lblDoanhThuNgay;
    @FXML private Label lblDoanhThuThang;
    @FXML private BarChart<String, Number> barChart;
    @FXML private TableView<ThongKeVatLieu> tblTop5;
    @FXML private TableColumn<ThongKeVatLieu, String> colTenMon;
    @FXML private TableColumn<ThongKeVatLieu, Integer> colSoLuong;

    @FXML
    public void initialize() {
        Thongkedata dao = new Thongkedata();

        double doanhThuNgay = dao.getDoanhThuHomNay();
        double doanhThuThang = dao.getDoanhThuThangNay();
        lblDoanhThuNgay.setText(String.format("%,.0f VNĐ", doanhThuNgay));
        lblDoanhThuThang.setText(String.format("%,.0f VNĐ", doanhThuThang));

        colTenMon.setCellValueFactory(new PropertyValueFactory<>("tenMon"));
        colSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        
        var top5List = dao.getTop5MonBanChay();
        tblTop5.setItems(FXCollections.observableArrayList(top5List));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Số lượng bán");
        for (ThongKeVatLieu item : top5List) {
            series.getData().add(new XYChart.Data<>(item.getTenMon(), item.getSoLuong()));
        }
        barChart.getData().add(series);
    }
}