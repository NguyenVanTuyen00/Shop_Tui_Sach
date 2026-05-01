package controller;

import dao.HoaDonDAO;
import dao.SanPhamDAO;
import model.ChiTietHoaDon;
import model.SanPham;
import utils.FormatCurrency;
import view.BanHangView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class BanHangController {
    private BanHangView view;
    private SanPhamDAO spDao;
    private HoaDonDAO hdDao;
    private List<ChiTietHoaDon> gioHang;
    private double tongTien = 0;

    public BanHangController(BanHangView view) {
        this.view = view;
        this.spDao = new SanPhamDAO();
        this.hdDao = new HoaDonDAO();
        this.gioHang = new ArrayList<>();

        loadDanhSachSanPham();

        // Lắng nghe sự kiện từ giao diện
        this.view.addCartListener(e -> themVaoGioHang());
        this.view.addCheckoutListener(e -> thanhToan());
    }

    // Đổ dữ liệu sản phẩm từ DB lên bảng
    private void loadDanhSachSanPham() {
        DefaultTableModel model = view.getModSanPham();
        model.setRowCount(0);
        List<SanPham> list = spDao.getAllSanPham();
        for (SanPham sp : list) {
            model.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getGia(), sp.getSoLuong()
            });
        }
    }

    // Xử lý khi bấm nút "Thêm vào giỏ"
    private void themVaoGioHang() {
        int row = view.getTblSanPham().getSelectedRow();
        if (row == -1) {
            view.showMessage("Vui lòng chọn 1 chiếc túi xách để thêm vào giỏ!");
            return;
        }

        String maSP = view.getModSanPham().getValueAt(row, 0).toString();
        String tenSP = view.getModSanPham().getValueAt(row, 1).toString();
        double gia = Double.parseDouble(view.getModSanPham().getValueAt(row, 2).toString());
        int tonKho = Integer.parseInt(view.getModSanPham().getValueAt(row, 3).toString());

        String inputSl = JOptionPane.showInputDialog(view, "Nhập số lượng mua:");
        if (inputSl == null || inputSl.trim().isEmpty()) return;

        try {
            int slMua = Integer.parseInt(inputSl);
            if (slMua <= 0 || slMua > tonKho) {
                view.showMessage("Số lượng không hợp lệ hoặc không đủ hàng trong kho!");
                return;
            }

            // Kiểm tra xem túi đã có trong giỏ chưa
            for (ChiTietHoaDon ct : gioHang) {
                if (ct.getMaSP().equals(maSP)) {
                    view.showMessage("Túi xách này đã có trong giỏ. Vui lòng thanh toán hoặc xóa giỏ để chọn lại!");
                    return;
                }
            }

            ChiTietHoaDon ct = new ChiTietHoaDon(maSP, tenSP, slMua, gia);
            gioHang.add(ct);
            capNhatGioHang();

        } catch (NumberFormatException ex) {
            view.showMessage("Vui lòng nhập số nguyên!");
        }
    }

    // Tính lại tiền và hiển thị lên bảng Giỏ hàng
    private void capNhatGioHang() {
        DefaultTableModel model = view.getModGioHang();
        model.setRowCount(0);
        tongTien = 0;
        
        for (ChiTietHoaDon ct : gioHang) {
            double thanhTien = ct.getThanhTien();
            tongTien += thanhTien;
            model.addRow(new Object[]{
                ct.getMaSP(), ct.getTenSP(), ct.getSoLuong(), 
                FormatCurrency.formatVND(ct.getDonGia()), 
                FormatCurrency.formatVND(thanhTien)
            });
        }
        // Gọi hàm format tiền tệ vừa tạo ở utils
        view.setTongTien(FormatCurrency.formatVND(tongTien)); 
    }

    // Xử lý khi bấm nút "Thanh toán"
    private void thanhToan() {
        if (gioHang.isEmpty()) {
            view.showMessage("Giỏ hàng đang trống, không thể thanh toán!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, 
            "Xác nhận thanh toán hóa đơn với " + FormatCurrency.formatVND(tongTien) + "?", 
            "Check Out", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            // Gọi DAO để tạo hóa đơn và trừ tồn kho
            if (hdDao.thanhToan(tongTien, gioHang)) {
                view.showMessage("Thanh toán thành công! Đã tạo hóa đơn.");
                gioHang.clear(); // Làm trống giỏ hàng
                capNhatGioHang(); // Reset giao diện giỏ hàng
                loadDanhSachSanPham(); // Cập nhật lại số tồn kho trên bảng
            } else {
                view.showMessage("Lỗi hệ thống: Không thể thanh toán (Rollback đã được kích hoạt)!");
            }
        }
    }
}