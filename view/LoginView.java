package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Đăng Nhập Hệ Thống");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JPanel pnlUser = new JPanel();
        pnlUser.add(new JLabel("Tài Khoản:"));
        txtUsername = new JTextField(15);
        pnlUser.add(txtUsername);

        JPanel pnlPass = new JPanel();
        pnlPass.add(new JLabel("Mật Khẩu:"));
        txtPassword = new JPasswordField(15);
        pnlPass.add(txtPassword);

        JPanel pnlBtn = new JPanel();
        btnLogin = new JButton("Đăng Nhập");
        pnlBtn.add(btnLogin);

        add(pnlUser); add(pnlPass); add(pnlBtn);
    }

    public String getUsername() { return txtUsername.getText(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }
    public void addLoginListener(ActionListener listener) { btnLogin.addActionListener(listener); }
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}