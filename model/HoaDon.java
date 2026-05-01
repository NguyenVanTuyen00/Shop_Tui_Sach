package model;

import java.util.Date;

public class HoaDon {
    private String maHD;
    private Date ngayLap;
    private String maNV;
    private String maKH;
    private double tongTien;

    public HoaDon(String maHD, Date ngayLap, String maNV, String maKH, double tongTien) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
    }

    public String getMaHD() { return maHD; }
    public Date getNgayLap() { return ngayLap; }
    public String getMaNV() { return maNV; }
    public String getMaKH() { return maKH; }
    public double getTongTien() { return tongTien; }
}