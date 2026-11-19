package com.mycompany.doanjava_quanlyquancafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Danhmucdata {
    public List<Danhmuc> getAll() {
        List<Danhmuc> list = new ArrayList<>();
        String sql = "SELECT * FROM danh_muc WHERE Con_hoat_dong = 1";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(new Danhmuc(rs.getInt("stt"), rs.getString("Ten_Danh_Muc")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}