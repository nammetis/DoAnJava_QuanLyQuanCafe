package com.mycompany.doanjava_quanlyquancafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class App extends Application {

@Override
public void start(Stage stage) throws IOException{
FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login.fxml"));
Parent root = fxmlLoader.load();
Scene khunghinh = new Scene(root,1050,650);
stage.setTitle("Hệ Thống Quản Lý Quán Cafe");
Image icon = new Image(getClass().getResourceAsStream("/anh/anhlogoapp.png"));
stage.getIcons().add(icon);
stage.setScene(khunghinh);
stage.setResizable(true);
stage.show();
}

public static void main(String[]args){
    launch();
}
}
