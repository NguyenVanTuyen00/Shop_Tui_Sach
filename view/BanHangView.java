package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class BanHangView extends JPanel {
    private JTable tblSanPham, tblGioHang;
    private DefaultTableModel modSanPham, modGioHang;
    private JButton btnAddCart, btnCheckout;
    private JLabel lblTongTien;

    public BanHangView() {
        setLayout(new BorderLayout());

        // Bảng chọn sản phẩm
        modSanPham = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Giá", "Tồn kho"}, 0);
        tblSanPham = new JTable(modSanPham);
        
        // Bảng Giỏ hàng
        modGioHang = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành Tiền"}, 0);
        tblGioHang = new JTable(modGioHang);

        JPanel pnlCenter = new JPanel(new GridLayout(1, 2));
        pnlCenter.add(new JScrollPane(tblSanPham));
        pnlCenter.add(new JScrollPane(tblGioHang));
        add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel();
        btnAddCart = new JButton("Thêm vào giỏ");
        btnCheckout = new JButton("Thanh Toán");
        lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 16));
        lblTongTien.setForeground(Color.RED);

        pnlBottom.add(btnAddCart); pnlBottom.add(btnCheckout); pnlBottom.add(lblTongTien);
        add(pnlBottom, BorderLayout.SOUTH);
    }

    public DefaultTableModel getModSanPham() { return modSanPham; }
    public DefaultTableModel getModGioHang() { return modGioHang; }
    public JTable getTblSanPham() { return tblSanPham; }
    public void setTongTien(String tien) { lblTongTien.setText("Tổng tiền: " + tien); }
    
    public void addCartListener(ActionListener listener) { btnAddCart.addActionListener(listener); }
    public void addCheckoutListener(ActionListener listener) { btnCheckout.addActionListener(listener); }
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}