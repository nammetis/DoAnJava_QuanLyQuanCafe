package com.mycompany.doanjava_quanlyquancafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetnoiDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyQuanCafe;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
     public static void main(String[] args) {
        getConnection();
    }
}