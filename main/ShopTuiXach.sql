CREATE DATABASE IF NOT EXISTS ShopTuiXach;
USE ShopTuiXach;

CREATE TABLE NhanVien (
    MaNV VARCHAR(20) PRIMARY KEY,
    HoTen VARCHAR(100),
    MatKhau VARCHAR(50),
    VaiTro VARCHAR(20)
);

CREATE TABLE SanPham (
    MaSP VARCHAR(20) PRIMARY KEY,
    TenSP VARCHAR(100),
    MaLoai VARCHAR(20),
    Gia DOUBLE,
    SoLuong INT,
    ChatLieu VARCHAR(50),
    MauSac VARCHAR(50)
);

CREATE TABLE HoaDon (
    MaHD VARCHAR(50) PRIMARY KEY,
    NgayLap DATETIME,
    MaNV VARCHAR(20),
    MaKH VARCHAR(20),
    TongTien DOUBLE,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

CREATE TABLE ChiTietHoaDon (
    MaHD VARCHAR(50),
    MaSP VARCHAR(20),
    SoLuong INT,
    DonGia DOUBLE,
    PRIMARY KEY (MaHD, MaSP),
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

-- Dữ liệu mẫu
INSERT INTO NhanVien VALUES ('admin', 'Quản Trị Viên', '123', 'Admin');
INSERT INTO NhanVien VALUES ('nv01', 'Nguyễn Văn A', '123', 'Nhân viên');

INSERT INTO SanPham VALUES ('SP01', 'Túi Gucci Diana', 'L01', 5000000, 10, 'Da', 'Đen');
INSERT INTO SanPham VALUES ('SP02', 'Túi LV Capucines', 'L01', 12000000, 5, 'Da cao cấp', 'Hồng');