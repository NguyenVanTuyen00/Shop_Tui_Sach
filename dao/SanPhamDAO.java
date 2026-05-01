package dao;

import model.SanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new SanPham(rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("MaLoai"),
                    rs.getDouble("Gia"), rs.getInt("SoLuong"), rs.getString("ChatLieu"), rs.getString("MauSac")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean addSanPham(SanPham sp) {
        String sql = "INSERT INTO SanPham VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sp.getMaSP()); pstmt.setString(2, sp.getTenSP()); pstmt.setString(3, sp.getMaLoai());
            pstmt.setDouble(4, sp.getGia()); pstmt.setInt(5, sp.getSoLuong()); 
            pstmt.setString(6, sp.getChatLieu()); pstmt.setString(7, sp.getMauSac());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean updateSanPham(SanPham sp) {
        String sql = "UPDATE SanPham SET TenSP = ?, MaLoai = ?, Gia = ?, SoLuong = ?, ChatLieu = ?, MauSac = ? WHERE MaSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sp.getTenSP());
            pstmt.setString(2, sp.getMaLoai());
            pstmt.setDouble(3, sp.getGia());
            pstmt.setInt(4, sp.getSoLuong());
            pstmt.setString(5, sp.getChatLieu());
            pstmt.setString(6, sp.getMauSac());
            pstmt.setString(7, sp.getMaSP());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSanPham(String maSP) {
        String sql = "DELETE FROM SanPham WHERE MaSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maSP);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}