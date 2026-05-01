package model;

public class NhanVien {
    private String maNV, hoTen, matKhau, vaiTro;

    public NhanVien(String maNV, String hoTen, String matKhau, String vaiTro) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public String getMaNV() { return maNV; }
    public String getHoTen() { return hoTen; }
    public String getVaiTro() { return vaiTro; }
}