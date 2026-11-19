package com.mycompany.doanjava_quanlyquancafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Bandata {
    public List<Ban> getAll() {
        List<Ban> list = new ArrayList<>();
        String sql = "SELECT * FROM ban";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(new Ban(rs.getInt("stt"), rs.getString("Ten_ban"), rs.getString("Trang_thai")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        public void updateTrangThai(int sttBan, String trangThai) {
        String sql = "UPDATE ban SET Trang_thai = ? WHERE stt = ?";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, trangThai);
            pst.setInt(2, sttBan);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}