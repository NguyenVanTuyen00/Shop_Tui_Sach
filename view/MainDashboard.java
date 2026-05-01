package view;

import utils.SessionManager;
import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    private BanHangView banHangView;
    private SanPhamView sanPhamView;

    public MainDashboard() {
        setTitle("Shop Túi Xách MVC - Nhân viên: " + SessionManager.getMaNV());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        
        banHangView = new BanHangView();
        tabbedPane.addTab("🛒 Bán Hàng", banHangView);
        
        sanPhamView = new SanPhamView();
        tabbedPane.addTab("👜 Quản Lý Sản Phẩm", sanPhamView);

        // Phân quyền Admin
        if ("Admin".equals(SessionManager.getVaiTro())) {
            tabbedPane.addTab("👥 Quản Lý Nhân Viên", new JPanel()); // Stub
            tabbedPane.addTab("📈 Thống Kê", new JPanel()); // Stub
        }

        add(tabbedPane, BorderLayout.CENTER);
    }

    public BanHangView getBanHangView() { return banHangView; }
    public SanPhamView getSanPhamView() { return sanPhamView; }
}