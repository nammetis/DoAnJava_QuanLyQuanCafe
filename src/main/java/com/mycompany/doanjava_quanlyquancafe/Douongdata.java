package com.mycompany.doanjava_quanlyquancafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Douongdata {
    public List<Douong> getAll() {
        List<Douong> list = new ArrayList<>();
        String sql = "SELECT * FROM do_uong WHERE Con_hoat_dong = 1"; 
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(new Douong(
                    rs.getInt("stt"),
                    rs.getString("Ten_do_uong"),
                    rs.getDouble("Gia_co_ban"),
                    rs.getInt("stt_danh_muc")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(String ten, double gia, int maDM) {
        String sql = "INSERT INTO do_uong (Ten_do_uong, Gia_co_ban, stt_danh_muc) VALUES (?, ?, ?)";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, ten);
            pst.setDouble(2, gia);
            pst.setInt(3, maDM);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int stt, String ten, double gia, int maDM) {
        String sql = "UPDATE do_uong SET Ten_do_uong = ?, Gia_co_ban = ?, stt_danh_muc = ? WHERE stt = ?";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, ten);
            pst.setDouble(2, gia);
            pst.setInt(3, maDM);
            pst.setInt(4, stt);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int stt) {
        String sql = "UPDATE do_uong SET Con_hoat_dong = 0 WHERE stt = ?";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, stt);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}