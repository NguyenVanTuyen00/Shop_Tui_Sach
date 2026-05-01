package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;

public class SanPhamView extends JPanel {
    private JTextField txtMaSP, txtTenSP, txtMaLoai, txtGia, txtSoLuong, txtChatLieu, txtMauSac;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;

    public SanPhamView() {
        // Sử dụng BorderLayout cho toàn bộ Panel
        setLayout(new BorderLayout());

        // --- PANEL INPUT (Phía trên) ---
        JPanel panelInput = new JPanel(new GridLayout(7, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        panelInput.add(new JLabel("Mã Sản Phẩm:")); 
        txtMaSP = new JTextField(); 
        panelInput.add(txtMaSP);

        panelInput.add(new JLabel("Tên Sản Phẩm:")); 
        txtTenSP = new JTextField(); 
        panelInput.add(txtTenSP);

        panelInput.add(new JLabel("Mã Loại:")); 
        txtMaLoai = new JTextField(); 
        panelInput.add(txtMaLoai);

        panelInput.add(new JLabel("Giá bán (VNĐ):")); 
        txtGia = new JTextField(); 
        panelInput.add(txtGia);

        panelInput.add(new JLabel("Số Lượng:")); 
        txtSoLuong = new JTextField(); 
        panelInput.add(txtSoLuong);

        panelInput.add(new JLabel("Chất Liệu:")); 
        txtChatLieu = new JTextField(); 
        panelInput.add(txtChatLieu);

        panelInput.add(new JLabel("Màu Sắc:")); 
        txtMauSac = new JTextField(); 
        panelInput.add(txtMauSac);

        add(panelInput, BorderLayout.NORTH);

        // --- PANEL BUTTONS (Ở giữa) ---
        JPanel panelButtons = new JPanel();
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnClear = new JButton("Làm Mới");

        panelButtons.add(btnAdd); 
        panelButtons.add(btnUpdate); 
        panelButtons.add(btnDelete); 
        panelButtons.add(btnClear);
        add(panelButtons, BorderLayout.CENTER);

        // --- PANEL TABLE (Phía dưới) ---
        tableModel = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Mã Loại", "Giá", "Số Lượng", "Chất Liệu", "Màu Sắc"}, 0);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách túi xách"));
        add(scrollPane, BorderLayout.SOUTH);
    }

    // ==========================================
    // CÁC HÀM GETTER ĐỂ CONTROLLER LẤY DỮ LIỆU
    // ==========================================
    public String getMaSP() { return txtMaSP.getText().trim(); }
    public String getTenSP() { return txtTenSP.getText().trim(); }
    public String getMaLoai() { return txtMaLoai.getText().trim(); }
    public String getGia() { return txtGia.getText().trim(); }
    public String getSoLuong() { return txtSoLuong.getText().trim(); }
    public String getChatLieu() { return txtChatLieu.getText().trim(); }
    public String getMauSac() { return txtMauSac.getText().trim(); }

    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getTable() { return table; }

    // ==========================================
    // CÁC HÀM TIỆN ÍCH CHO GIAO DIỆN
    // ==========================================
    public void clearForm() {
        txtMaSP.setText(""); txtTenSP.setText(""); txtMaLoai.setText("");
        txtGia.setText(""); txtSoLuong.setText(""); txtChatLieu.setText(""); txtMauSac.setText("");
        txtMaSP.requestFocus(); // Đưa con trỏ chuột về ô đầu tiên
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void fillFormFromSelectedRow() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaSP.setText(tableModel.getValueAt(row, 0).toString());
            txtTenSP.setText(tableModel.getValueAt(row, 1).toString());
            txtMaLoai.setText(tableModel.getValueAt(row, 2).toString());
            txtGia.setText(tableModel.getValueAt(row, 3).toString());
            txtSoLuong.setText(tableModel.getValueAt(row, 4).toString());
            txtChatLieu.setText(tableModel.getValueAt(row, 5).toString());
            txtMauSac.setText(tableModel.getValueAt(row, 6).toString());
        }
    }

    // ==========================================
    // CÁC HÀM GÁN SỰ KIỆN CHO NÚT BẤM
    // ==========================================
    public void addAddListener(ActionListener listener) { btnAdd.addActionListener(listener); }
    public void addUpdateListener(ActionListener listener) { btnUpdate.addActionListener(listener); }
    public void addDeleteListener(ActionListener listener) { btnDelete.addActionListener(listener); }
    public void addClearListener(ActionListener listener) { btnClear.addActionListener(listener); }
    public void addTableSelectionListener(ListSelectionListener listener) { table.getSelectionModel().addListSelectionListener(listener); }
}