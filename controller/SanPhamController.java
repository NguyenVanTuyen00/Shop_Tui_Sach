package controller;

import dao.SanPhamDAO;
import model.SanPham;
import view.SanPhamView;

import java.util.List;

public class SanPhamController {
    private SanPhamView view;
    private SanPhamDAO dao;

    public SanPhamController(SanPhamView view, SanPhamDAO dao) {
        this.view = view;
        this.dao = dao;

        // 1. Load dữ liệu lần đầu khi vừa mở tab lên
        loadTableData();

        // 2. Gắn sự kiện cho các nút trên giao diện
        this.view.addAddListener(e -> addSanPham());
        this.view.addUpdateListener(e -> updateSanPham());
        this.view.addDeleteListener(e -> deleteSanPham());
        this.view.addClearListener(e -> view.clearForm());
        
        // 3. Gắn sự kiện click vào 1 dòng trong bảng
        this.view.addTableSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                view.fillFormFromSelectedRow();
            }
        });
    }

    // Đọc danh sách từ Database và nhét vào bảng
    private void loadTableData() {
        view.getTableModel().setRowCount(0); // Xóa sạch dữ liệu cũ trên bảng
        List<SanPham> list = dao.getAllSanPham();
        for (SanPham sp : list) {
            view.getTableModel().addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getMaLoai(), 
                sp.getGia(), sp.getSoLuong(), sp.getChatLieu(), sp.getMauSac()
            });
        }
    }

    // Xử lý nút Thêm
    private void addSanPham() {
        try {
            // Validate sơ bộ
            if(view.getMaSP().isEmpty() || view.getTenSP().isEmpty()) {
                view.showMessage("Vui lòng nhập đủ Mã và Tên sản phẩm!");
                return;
            }

            double gia = Double.parseDouble(view.getGia());
            int soLuong = Integer.parseInt(view.getSoLuong());

            // Đóng gói thành object SanPham
            SanPham sp = new SanPham(view.getMaSP(), view.getTenSP(), view.getMaLoai(), 
                                     gia, soLuong, view.getChatLieu(), view.getMauSac());
            
            // Gọi DAO để insert vào DB
            if (dao.addSanPham(sp)) {
                view.showMessage("Đã thêm túi xách thành công!");
                loadTableData();
                view.clearForm();
            } else {
                view.showMessage("Lỗi: Không thể thêm (Mã SP có thể bị trùng)!");
            }
        } catch (NumberFormatException e) {
            view.showMessage("Lỗi: Giá và Số lượng phải là số hợp lệ!");
        }
    }

    // Xử lý nút Sửa
    private void updateSanPham() {
        try {
            if(view.getMaSP().isEmpty()) {
                view.showMessage("Vui lòng chọn sản phẩm cần sửa!");
                return;
            }

            double gia = Double.parseDouble(view.getGia());
            int soLuong = Integer.parseInt(view.getSoLuong());

            SanPham sp = new SanPham(view.getMaSP(), view.getTenSP(), view.getMaLoai(), 
                                     gia, soLuong, view.getChatLieu(), view.getMauSac());
            
            if (dao.updateSanPham(sp)) {
                view.showMessage("Cập nhật thông tin thành công!");
                loadTableData();
            } else {
                view.showMessage("Lỗi khi cập nhật vào cơ sở dữ liệu!");
            }
        } catch (NumberFormatException e) {
            view.showMessage("Lỗi định dạng: Kiểm tra lại Giá và Số lượng!");
        }
    }

    // Xử lý nút Xóa
    private void deleteSanPham() {
        String maSP = view.getMaSP();
        if (maSP.isEmpty()) {
            view.showMessage("Vui lòng chọn sản phẩm cần xóa trên bảng.");
            return;
        }
        
        // Hộp thoại xác nhận (tránh xóa nhầm)
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, "Bạn có chắc chắn muốn xóa túi xách mã " + maSP + "?", 
                "Xác nhận xóa", javax.swing.JOptionPane.YES_NO_OPTION);
                
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            if (dao.deleteSanPham(maSP)) {
                view.showMessage("Đã xóa xong!");
                loadTableData();
                view.clearForm();
            } else {
                view.showMessage("Lỗi: Không thể xóa (Sản phẩm này có thể đã nằm trong hóa đơn)!");
            }
        }
    }
}