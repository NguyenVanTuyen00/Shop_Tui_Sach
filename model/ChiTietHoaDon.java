package model;

public class ChiTietHoaDon {
    private String maSP, tenSP; // tenSP dùng để hiển thị trên bảng giỏ hàng
    private int soLuong;
    private double donGia;

    public ChiTietHoaDon(String maSP, String tenSP, int soLuong, double donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }
    public double getThanhTien() { return soLuong * donGia; }
}