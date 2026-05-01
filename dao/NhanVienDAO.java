package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NhanVienDAO {
    // Trả về vai trò nếu đăng nhập thành công
    public String checkLogin(String username, String password) {
        String sql = "SELECT VaiTro FROM NhanVien WHERE MaNV = ? AND MatKhau = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getString("VaiTro");
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}