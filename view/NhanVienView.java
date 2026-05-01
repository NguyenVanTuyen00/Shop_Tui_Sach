package view;

import javax.swing.*;
import java.awt.*;

public class NhanVienView extends JPanel {
    public NhanVienView() {
        setLayout(new BorderLayout());
        
        JLabel lblTitle = new JLabel("Tính năng Quản Lý Nhân Viên đang được phát triển...", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.BLUE);
        
        add(lblTitle, BorderLayout.CENTER);
    }
}