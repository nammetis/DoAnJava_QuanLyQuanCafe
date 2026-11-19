package com.mycompany.doanjava_quanlyquancafe;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {

    @FXML private TableView<Douong> tblMenu;
    @FXML private TableColumn<Douong, Integer> colMStt;
    @FXML private TableColumn<Douong, String> colMTen;
    @FXML private TableColumn<Douong, Double> colMGia;

    @FXML
    public void initialize() {
        colMStt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        colMTen.setCellValueFactory(new PropertyValueFactory<>("tenDoUong"));
        colMGia.setCellValueFactory(new PropertyValueFactory<>("giaCoBan"));
        loadData();
    }

    private void loadData() {
        tblMenu.setItems(FXCollections.observableArrayList(new Douongdata().getAll()));
    }

    @FXML
    private void handleThem() {
        moDialog(null);
    }

    @FXML
    private void handleSua() {
        Douong monChon = tblMenu.getSelectionModel().getSelectedItem();
        if (monChon == null) {
            showAlert("Vui lòng chọn một món để sửa!");
            return;
        }
        moDialog(monChon);
    }

    @FXML
    private void handleXoa() {
        Douong monChon = tblMenu.getSelectionModel().getSelectedItem();
        if (monChon == null) {
            showAlert("Vui lòng chọn một món để xóa!");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc muốn xóa món " + monChon.getTenDoUong() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                new Douongdata().delete(monChon.getStt());
                loadData();
            }
        });
    }

        private void moDialog(Douong mon) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menudialog.fxml"));
            Parent root = loader.load();
            
            MenuDialogController controller = loader.getController();
            if (mon != null) {
                controller.setMonDangSua(mon);
            }

            // TẠO CỬA SỔ MỚI (POPUP)
            Stage dialogStage = new Stage();
            dialogStage.setTitle(mon == null ? "Thêm Món Mới" : "Sửa Món");
            
            // Chặn tương tác với màn hình chính khi đang mở popup
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(tblMenu.getScene().getWindow());
            
            // Gắn giao diện vào cửa sổ mới và hiển thị
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
            
            // Load lại bảng sau khi đóng popup
            loadData();
            
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể mở cửa sổ");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void showAlert(String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING, msg);
        a.show();
    }
}