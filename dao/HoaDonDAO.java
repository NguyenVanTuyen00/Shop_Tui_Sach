package dao;

import model.ChiTietHoaDon;
import utils.SessionManager;
import java.sql.*;
import java.util.List;
import java.util.UUID;

public class HoaDonDAO {
    public boolean thanhToan(double tongTien, List<ChiTietHoaDon> gioHang) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Bắt đầu Transaction

            String maHD = "HD" + System.currentTimeMillis(); // Tạo mã HD tự động
            
            // 1. Lưu Hóa Đơn
            String sqlHD = "INSERT INTO HoaDon (MaHD, NgayLap, MaNV, MaKH, TongTien) VALUES (?, NOW(), ?, NULL, ?)";
            try (PreparedStatement pstHD = conn.prepareStatement(sqlHD)) {
                pstHD.setString(1, maHD);
                pstHD.setString(2, SessionManager.getMaNV()); // Lấy nhân viên đang trực
                pstHD.setDouble(3, tongTien);
                pstHD.executeUpdate();
            }

            // 2. Lưu Chi tiết & Trừ Tồn Kho
            String sqlCT = "INSERT INTO ChiTietHoaDon (MaHD, MaSP, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
            String sqlUpdateSP = "UPDATE SanPham SET SoLuong = SoLuong - ? WHERE MaSP = ?";
            
            try (PreparedStatement pstCT = conn.prepareStatement(sqlCT);
                 PreparedStatement pstSP = conn.prepareStatement(sqlUpdateSP)) {
                for (ChiTietHoaDon ct : gioHang) {
                    pstCT.setString(1, maHD);
                    pstCT.setString(2, ct.getMaSP());
                    pstCT.setInt(3, ct.getSoLuong());
                    pstCT.setDouble(4, ct.getDonGia());
                    pstCT.addBatch();

                    pstSP.setInt(1, ct.getSoLuong());
                    pstSP.setString(2, ct.getMaSP());
                    pstSP.addBatch();
                }
                pstCT.executeBatch();
                pstSP.executeBatch();
            }
            conn.commit(); // Xác nhận nếu mọi thứ OK
            return true;
        } catch (Exception e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) {} // Lỗi thì Rollback
            return false;
        } finally {
            try { if (conn != null) { conn.setAutoCommit(true); conn.close(); } } catch (SQLException e) {}
        }
    }
}