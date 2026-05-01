package controller;

import dao.NhanVienDAO;
import dao.SanPhamDAO;
import utils.SessionManager;
import view.LoginView;
import view.MainDashboard;

public class LoginController {
    private LoginView view;
    private NhanVienDAO dao;

    public LoginController(LoginView view) {
        this.view = view;
        this.dao = new NhanVienDAO();
        this.view.addLoginListener(e -> login());
    }

    private void login() {
        String user = view.getUsername();
        String pass = view.getPassword();
        String role = dao.checkLogin(user, pass);
        
        if (role != null) {
            SessionManager.setLoginSession(user, role); // Lưu phiên đăng nhập
            view.dispose();
            
            // Khởi tạo hệ thống sau khi đăng nhập thành công
            MainDashboard main = new MainDashboard();
            new BanHangController(main.getBanHangView());
            new SanPhamController(main.getSanPhamView(), new SanPhamDAO());
            main.setVisible(true);
        } else {
            view.showMessage("Sai tài khoản hoặc mật khẩu!");
        }
    }
}