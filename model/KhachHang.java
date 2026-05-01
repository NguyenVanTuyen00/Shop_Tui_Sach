package model;

public class KhachHang {
    private String maKH, hoTen, sdt, diaChi;

    public KhachHang(String maKH, String hoTen, String sdt, String diaChi) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public String getMaKH() { return maKH; }
    public String getHoTen() { return hoTen; }
    public String getSdt() { return sdt; }
    public String getDiaChi() { return diaChi; }
}