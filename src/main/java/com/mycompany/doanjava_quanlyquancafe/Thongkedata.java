package com.mycompany.doanjava_quanlyquancafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Thongkedata {
    public double getDoanhThuHomNay() {
        double tong = 0;
        String sql = "SELECT SUM(thanh_tien) FROM hoa_don WHERE CAST(ngay_lap AS DATE) = CAST(GETDATE() AS DATE)";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) tong = rs.getDouble(1);
        } catch (Exception e) { e.printStackTrace(); }
        return tong;
    }

    public double getDoanhThuThangNay() {
        double tong = 0;
        String sql = "SELECT SUM(thanh_tien) FROM hoa_don WHERE MONTH(ngay_lap) = MONTH(GETDATE()) AND YEAR(ngay_lap) = YEAR(GETDATE())";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) tong = rs.getDouble(1);
        } catch (Exception e) { e.printStackTrace(); }
        return tong;
    }

    public List<ThongKeVatLieu> getTop5MonBanChay() {
        List<ThongKeVatLieu> list = new ArrayList<>();
        String sql = "SELECT TOP 5 d.Ten_do_uong, SUM(ct.so_luong) as TongSL " +
                     "FROM chi_tiet_hoa_don ct " +
                     "JOIN do_uong d ON ct.stt_do_uong = d.stt " +
                     "GROUP BY d.Ten_do_uong " +
                     "ORDER BY TongSL DESC";
        try (Connection conn = KetnoiDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(new ThongKeVatLieu(rs.getString("Ten_do_uong"), rs.getInt("TongSL")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}