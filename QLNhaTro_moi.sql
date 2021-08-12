USE master


CREATE DATABASE QLNT_DA1
GO

USE QLNT_DA1
GO

CREATE TABLE NguoiDung
(
 Username NVARCHAR(20) PRIMARY KEY,
 Pass NVARCHAR(20),
 FullName NVARCHAR(20),
 VaiTro BIT
);


CREATE TABLE ThietBi
(
 MaTB NVARCHAR(10) PRIMARY KEY,
 TenTB NVARCHAR(50),
 MoTa NVARCHAR(250),
);

CREATE TABLE PhongTro
(
 MaPT NVARCHAR(10) PRIMARY KEY,
 Gia FLOAT,
 TinhTrang NVARCHAR(10),
 MoTa NVARCHAR(250),

);


CREATE TABLE HopDong
(
 MaHopDong NVARCHAR(10) PRIMARY KEY,
 MaPT NVARCHAR(10),
 NgayThue DATE,
 NgayTra DATE,
 SoNguoi INT,
 MoTa NVARCHAR(200) NULL

 FOREIGN KEY(MaPT) REFERENCES dbo.PhongTro(MaPT) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE KhachHang
(
 MaKH NVARCHAR(10) PRIMARY KEY,
 MaHopDong NVARCHAR(10),
 TenKH NVARCHAR(50),
 GioiTinh BIT,
 QueQuan NVARCHAR(50),
 SDT NVARCHAR(12),
 CMND NVARCHAR(20),
 NgaySinh DATE,
 VaiTro BIT,

 FOREIGN KEY(MaHopDong) REFERENCES dbo.HopDong(MaHopDong) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE DichVu
(
 MaDV NVARCHAR(10) PRIMARY KEY,
 TenDV NVARCHAR(50),
 Gia FLOAT,
);

CREATE TABLE CTPT_DichVu
(
 MaHopDong NVARCHAR(10),
 MaDV NVARCHAR(10),

 PRIMARY KEY CLUSTERED (MaHopDong ASC, MaDV ASC),
 FOREIGN KEY(MaHopDong) REFERENCES dbo.HopDong(MaHopDong) ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY(MaDV) REFERENCES dbo.DichVu(MaDV) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE HoaDon
(
 MaHD NVARCHAR(10) PRIMARY KEY,
 MaHopDong NVARCHAR(10),
 ThangNam DATE DEFAULT GETDATE() NOT NULL,
 TongTien FLOAT DEFAULT 0 NULL,
 TinhTrang BIT,

 FOREIGN KEY(MaHopDong) REFERENCES dbo.HopDong(MaHopDong) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE CTHD
(
 MaHoaDon NVARCHAR(10),
 TenDV NVARCHAR(50),
 Gia FLOAT,
 SoLuong INT,
 ThanhTien FLOAT NULL,
 SoDienMoi INT NULL,
 SoDienCu INT NULL,

 --FOREIGN KEY(MaHoaDon) REFERENCES dbo.HoaDon(MaHĐ) ON DELETE CASCADE ON UPDATE CASCADE,
);


CREATE TABLE TrangBi
(
 MaTB NVARCHAR(10),
 MaPT NVARCHAR(10),

 PRIMARY KEY CLUSTERED (MaPT ASC, MaTB ASC),
 FOREIGN KEY(MaTB) REFERENCES dbo.ThietBi(MaTB) ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY(MaPT) REFERENCES dbo.PhongTro(MaPT) ON DELETE CASCADE ON UPDATE CASCADE
);
go

--PROC
--sp thống kê doanh thu
create proc [dbo].[sp_ThongKeDoanThu] (@year int)
as begin
	select 
		distinct
		SUM(TongTien) TongTien,
		AVG(TongTien) doanhThuTB,
		MAX(TongTien) doanhthuMax,
		MIN(TongTien) doanhThuMin
	FROM dbo.HoaDon
	where YEAR(ThangNam)=@year and TinhTrang=1
end
exec sp_ThongKeDoanThu 2020
go

--sp thống kê số lượng
create proc sp_thongKeSL (@yearDay varchar(7))
as begin
	select 
	SUM(SoNguoi) tongSL,
	AVG(SoNguoi) SLTB,
	MAX(SoNguoi) SLMax,
	MIN(SoNguoi) SLMin
	from HopDong where NgayThue like @yearDay+'%'
end

exec sp_thongKeSL '2019-01'

-- in hóa đơn
CREATE view CTHoaDon
as 
SELECT TenKH, TenDV, Gia, SoLuong, ThanhTien, SoDienMoi, SoDienCu, HoaDon.MaHopDong, ThangNam, TongTien, MaPT, MaHoaDon
FROM dbo.HoaDon JOIN dbo.CTHD ON MaHoaDon = MaHD
JOIN dbo.HopDong ON HopDong.MaHopDong = HoaDon.MaHopDong
JOIN dbo.KhachHang ON KhachHang.MaHopDong = HopDong.MaHopDong
WHERE VaiTro = 1

SELECT TenKH, TenDV, Gia, SoLuong, ThanhTien, SoDienMoi, SoDienCu, MaHopDong, ThangNam, TongTien, MaPT, MaHoaDon
FROM CTHoaDon WHERE MaHoaDon = N'MHD004'



-- THÊM DỮ LIỆU

-- NguoiDung
INSERT dbo.NguoiDung ( Username, Pass, FullName, VaiTro ) VALUES  ( N'HangTT',N'123456', N'Trần Thúy Hằng', 1);

-- ThietBi
INSERT dbo.ThietBi( MaTB, TenTB, MoTa ) VALUES  ( N'TL', N'Tủ lạnh',N'Dung lượng 2 lít, 2 ngăn, màu trắng');
INSERT dbo.ThietBi( MaTB, TenTB, MoTa ) VALUES  ( N'DH', N'Điều hóa',N'Màu trắng');
INSERT dbo.ThietBi( MaTB, TenTB, MoTa ) VALUES  ( N'NL', N'Nóng lạnh',N'Dung lượng 1 lít, màu xám');
INSERT dbo.ThietBi( MaTB, TenTB, MoTa ) VALUES  ( N'TQA', N'Tủ quần áo',N'2 ngăn, màu nâu');

-- Phong Tro
INSERT dbo.PhongTro( MaPT, Gia, TinhTrang, MoTa ) VALUES  (N'P101', 3200000, N'Còn trống', N'Rộng 32m2, có cửa sổ và ban công');
INSERT dbo.PhongTro( MaPT, Gia, TinhTrang, MoTa ) VALUES  (N'P102', 2800000, N'Đã thuê', N'Rộng 25m2, có cửa sổ');
INSERT dbo.PhongTro( MaPT, Gia, TinhTrang, MoTa ) VALUES  (N'P201', 2700000, N'Đang sửa', N'Rộng 22m2, có cửa sổ');
INSERT dbo.PhongTro( MaPT, Gia, TinhTrang, MoTa ) VALUES  (N'P202', 2800000, N'Đã thuê', N'Rộng 25m2, có cửa sổ');
INSERT dbo.PhongTro( MaPT, Gia, TinhTrang, MoTa ) VALUES  (N'P301', 3200000, N'Đã thuê', N'Rộng 32m2, có cửa sổ và ban công');

-- Hợp đồng
INSERT dbo.HopDong ( MaHopDong  , MaPT ,NgayThue ,NgayTra ,SoNguoi, MoTa )
VALUES  ( N'HD001'  , N'P102' ,'01-10-2019' ,'01-10-2020' ,  4 , N'ki ' )
INSERT dbo.HopDong ( MaHopDong  , MaPT ,NgayThue ,NgayTra ,SoNguoi, MoTa )
VALUES  ( N'HD002'  , N'P301' ,'05-30-2019' ,'05-30-2020' ,  3 , N'ki ' )
INSERT dbo.HopDong ( MaHopDong  , MaPT ,NgayThue ,NgayTra ,SoNguoi , MoTa)
VALUES  ( N'HD003'  , N'P202' ,'10-24-2018','01-12-2020' ,  3, N'jk'  )

-- Khách hàng
INSERT dbo.KhachHang ( MaKH ,MaHopDong, TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH001' ,N'HD001', N'Nguyễn Thị Vân' , 1 , N'Hà Nội' , N'0126584585' , N'187960316' ,'02/25/1997' , 1 )
INSERT dbo.KhachHang ( MaKH ,MaHopDong, TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH002' ,N'HD001', N'Hoàng Văn Hải' , 0 , N'Thanh Hóa' , N'0122154562' , N'183524705' ,'07-12-1999' , 0 )
INSERT dbo.KhachHang ( MaKH ,MaHopDong,TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH003' ,N'HD001', N'Lương Minh Khánh' , 0 , N'Hải Phòng' , N'0120215486' , N'182024698' ,'12-23-2001' , 0 )
INSERT dbo.KhachHang ( MaKH ,MaHopDong,TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH004' ,N'HD001', N'Nguyễn Thị Khánh Linh' , 1 , N'Điện Biên' , N'012325147' , N'182547269' ,'06-10-2001' , 0 )
INSERT dbo.KhachHang ( MaKH ,MaHopDong,TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH005' ,N'HD002', N'Lê Thị Mai' , 1 , N'Hà Nam' , N'0123021548' , N'182547269' ,'05-18-1999' , 0 )
INSERT dbo.KhachHang ( MaKH ,MaHopDong,TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH005' ,N'HD002', N'Lê Thị Trang' , 1 , N'Hà Nam' , N'0123021548' , N'182547269' ,'05-18-1999' , 0 )
INSERT dbo.KhachHang ( MaKH ,MaHopDong,TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH005' ,N'HD003', N'Lê Thị Vân' , 1 , N'Hà Nam' , N'0123021548' , N'182547269' ,'05-18-1999' , 0 )
INSERT dbo.KhachHang ( MaKH ,MaHopDong,TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH006' ,N'HD003', N'Lê Văn Dương' , 0 , N'Nghệ An' , N'0122548036' , N'182033549' ,'09-21-1998' , 0 )
INSERT dbo.KhachHang ( MaKH ,MaHopDong,TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH007' ,N'HD002', N'Nguyễn Hoàng Luân' , 0 , N'Hà Nội' , N'012154652' , N'182554206' ,'10-30-2001', 1)
INSERT dbo.KhachHang ( MaKH ,MaHopDong,TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) 
VALUES  ( N'KH008' ,N'HD003', N'Phạm Thị Như Quỳnh' , 1 , N'ninh Bình' , N'012655251' , N'182547620' ,'01-15-1997' , 1)

-- dịch vụ
INSERT dbo.DichVu ( MaDV, TenDV, Gia ) VALUES  ( N'DV001', N'Điện', 4.0 )
INSERT dbo.DichVu ( MaDV, TenDV, Gia ) VALUES  ( N'DV002', N'Nước', 100.0 )
INSERT dbo.DichVu ( MaDV, TenDV, Gia ) VALUES  ( N'DV003', N'Wifi', 100.0 )
INSERT dbo.DichVu ( MaDV, TenDV, Gia ) VALUES  ( N'DV004', N'Giữ xe', 50.0 )
INSERT dbo.DichVu ( MaDV, TenDV, Gia ) VALUES  ( N'DV005', N'Thang máy', 50.0 )
INSERT dbo.DichVu ( MaDV, TenDV, Gia ) VALUES  ( N'DV006', N'Vệ sinh', 20.0)

-- CTPT dịch vụ
INSERT dbo.CTPT_DichVu (  MaHopDong, MaDV ) VALUES  (  N'HD001', N'DV001' )
INSERT dbo.CTPT_DichVu (  MaHopDong, MaDV ) VALUES  (  N'HD002', N'DV001' )
INSERT dbo.CTPT_DichVu (  MaHopDong, MaDV ) VALUES  (  N'HD001', N'DV002' )
INSERT dbo.CTPT_DichVu (  MaHopDong, MaDV ) VALUES  (  N'HD002', N'DV002' )
INSERT dbo.CTPT_DichVu (  MaHopDong, MaDV ) VALUES  (  N'HD003', N'DV002' )
INSERT dbo.CTPT_DichVu (  MaHopDong, MaDV ) VALUES  ( N'HD003', N'DV001' )

-- hóa đơn
INSERT dbo.HoaDon ( MaHD ,MaHopDong, ThangNam ,TongTien , TinhTrang)VALUES  ( N'MHD004' ,N'HD002', '10-03-2020'  ,  3200650 , 0)
INSERT dbo.HoaDon ( MaHD ,MaHopDong, ThangNam ,TongTien , TinhTrang)VALUES  ( N'MHD006' ,N'HD003', '08-06-2020' ,    2800500 , 0)

-- CTHĐ
INSERT dbo.CTHD ( MaHoaDon , TenDV , Gia ,SoLuong , ThanhTien ,SoDienMoi,SoDienCu ) 
VALUES  ( N'MHD004' , N'Điện' , 4.0 ,50 , 200 , 150 , 200 )
INSERT dbo.CTHD ( MaHoaDon , TenDV , Gia ,SoLuong , ThanhTien ,SoDienMoi,SoDienCu ) 
VALUES  ( N'MHD004' , N'Nước' , 100.0 ,3 , 300 , 0 , 0 )
INSERT dbo.CTHD ( MaHoaDon , TenDV , Gia ,SoLuong , ThanhTien ,SoDienMoi,SoDienCu ) 
VALUES  ( N'MHD004' , N'Tiền phòng' , 3200000 ,1 , 3200000 , 0 , 0)
INSERT dbo.CTHD ( MaHoaDon , TenDV , Gia ,SoLuong , ThanhTien ,SoDienMoi,SoDienCu ) 
VALUES  ( N'MHD004' , N'Wifi' , 100.0 ,1 , 100 , 0 , 0 )
INSERT dbo.CTHD ( MaHoaDon , TenDV , Gia ,SoLuong , ThanhTien ,SoDienMoi,SoDienCu ) 
VALUES  ( N'MHD004' , N'Thang máy' , 50.0 ,1 , 50 , 0 , 0 )

INSERT dbo.CTHD ( MaHoaDon , TenDV , Gia ,SoLuong , ThanhTien ,SoDienMoi,SoDienCu ) 
VALUES  ( N'MHD006' , N'Điện' , 4.0 ,50 , 200 , 150 , 200 )
INSERT dbo.CTHD ( MaHoaDon , TenDV , Gia ,SoLuong , ThanhTien ,SoDienMoi,SoDienCu ) 
VALUES  ( N'MHD006' , N'Nước' ,  100.0 ,3 , 300 , 0 , 0  )
INSERT dbo.CTHD ( MaHoaDon , TenDV , Gia ,SoLuong , ThanhTien ,SoDienMoi,SoDienCu ) 
VALUES  ( N'MHD006' , N'Tiền phòng' , 2800000 ,1 , 2800000 , 0 , 0 )
-- trang bị
INSERT dbo.TrangBi ( MaTB, MaPT ) VALUES  ( N'DH', N'P101' )
INSERT dbo.TrangBi ( MaTB, MaPT ) VALUES  ( N'NL', N'P101' )
INSERT dbo.TrangBi ( MaTB, MaPT ) VALUES  ( N'TL', N'P101' )
INSERT dbo.TrangBi ( MaTB, MaPT ) VALUES  ( N'TQA', N'P201' )
INSERT dbo.TrangBi ( MaTB, MaPT ) VALUES  ( N'DH', N'P201' )
INSERT dbo.TrangBi ( MaTB, MaPT ) VALUES  ( N'TL', N'P102' )

-- Các câu lệnh truy vấn

-- Xem hợp đồng theo phòng 
SELECT MaHopDong, HopDong.MaPT, NgayThue, NgayTra, SoNguoi
FROM dbo.PhongTro JOIN dbo.HopDong ON HopDong.MaPT = PhongTro.MaPT
WHERE PhongTro.MaPT = ?

-- Xem thiết bị theo phòng
SELECT ThietBi.MaTB, TenTB
FROM dbo.ThietBi JOIN dbo.TrangBi ON TrangBi.MaTB = ThietBi.MaTB 
JOIN dbo.PhongTro ON PhongTro.MaPT = TrangBi.MaPT
WHERE PhongTro.MaPT = ?

-- Xem người thuê theo hợp đồng
SELECT MaKH, TenKH, GioiTinh, QueQuan, SDT, CMND, NgaySinh, VaiTro
FROM dbo.KhachHang JOIN dbo.HopDong ON HopDong.MaHopDong = dbo.KhachHang.MaHopDong
WHERE dbo.HopDong.MaHopDong = N'HD002';

-- đếm số người
SELECT COUNT(MaKH) AS N'SoLuong'
FROM dbo.KhachHang
WHERE MaHopDong = N'HD001';

UPDATE dbo.HopDong  SET SoNguoi

SELECT * FROM dbo.CTHĐ
SELECT * FROM dbo.HoaDon 
SELECT * FROM dbo.HopDong
SELECT * FROM dbo.PhongTro

-- đổ dl lên bảng hóa đơn
SELECT MaHĐ, MaPT,TenKH, ThangNam, TongTien
FROM dbo.HoaDon JOIN dbo.HopDong ON HopDong.MaHopDong = HoaDon.MaHopDong
JOIN dbo.KhachHang ON HopDong.MaHopDong = dbo.KhachHang.MaHopDong
WHERE VaiTro = 1

--Đổ lên cbb trong CTHĐ
SELECT TenDV
FROM dbo.HoaDon JOIN dbo.HopDong ON HopDong.MaHopDong = HoaDon.MaHopDong
JOIN dbo.CTPT_DichVu ON CTPT_DichVu.MaHopDong = HopDong.MaHopDong 
JOIN dbo.DichVu ON DichVu.MaDV = CTPT_DichVu.MaDV
WHERE CTPT_DichVu.MaHopDong = N'HD001';

SELECT TenDV
FROM dbo.HopDong JOIN dbo.CTPT_DichVu ON CTPT_DichVu.MaHopDong = HopDong.MaHopDong
JOIN dbo.DichVu ON DichVu.MaDV = CTPT_DichVu.MaDV
WHERE CTPT_DichVu.MaHopDong = N'HD001';


-- lấy số lượng người 
SELECT SoNguoi
FROM dbo.PhongTro JOIN dbo.HopDong ON HopDong.MaPT = PhongTro.MaPT
WHERE MaHopDong = N'HD001'

-- lấy giá dịch vụ
SELECT Gia FROM dbo.DichVu WHERE TenDV = N'Nước'

-- tính tổng hóa đơn
SELECT SUM(ThanhTien)  AS N'TongTien'
FROM dbo.CTHĐ
WHERE MaHoaDon = N'MHD001';

-- hiển thị thông tin chi tiết HĐ
SELECT TenDV, Gia, SoLuong, SoDienMoi, SoDienCu
FROM dbo.CTHĐ
WHERE TenDV = N'Nước';

SELECT TenDV, Gia, SoLuong, ThanhTien FROM dbo.CTHĐ WHERE MaHoaDon = N'MHD001'

SELECT gia
FROM dbo.PhongTro WHERE MaPT = N'P102';

SELECT HopDong.MaHopDong, MaPT,TenKH, NgayThue, NgayTra, SoNguoi
FROM dbo.HopDong JOIN dbo.KhachHang ON KhachHang.MaHopDong = HopDong.MaHopDong
WHERE VaiTro = 1

SELECT MaHopDong, MaPT, SoNguoi, NgayThue, NgayTra
FROM dbo.HopDong WHERE MaHopDong = ?

select * from KhachHang where TenKH LIKE ?

-- dịch vụ đã thuê
SELECT  TenDV, Gia
FROM dbo.HopDong JOIN dbo.CTPT_DichVu ON CTPT_DichVu.MaHopDong = HopDong.MaHopDong
JOIN dbo.DichVu ON DichVu.MaDV = CTPT_DichVu.MaDV
WHERE CTPT_DichVu.MaHopDong = N'HD001'

SELECT MaDV, TenDV, Gia from DichVu

DELETE dbo.CTPT_DichVu WHERE MaHopDong =? AND MaDV =?

SELECT MaDV FROM dbo.DichVu WHERE TenDV =?

SELECT TenDV, Gia, SoDienMoi, SoDienCu, SoLuong, ThanhTien
FROM dbo.HoaDon JOIN dbo.CTHĐ ON MaHoaDon = MaHĐ
WHERE MaHĐ = N'HD0253'

SELECT TenKH, ThangNam, MaPT, TongTien
FROM dbo.HoaDon JOIN dbo.HopDong ON HopDong.MaHopDong = HoaDon.MaHopDong
JOIN dbo.KhachHang ON KhachHang.MaHopDong = HopDong.MaHopDong
WHERE MaHĐ = N'HD0253' AND VaiTro = 1

--đếm số phòng trống
create view phongTrong
as 
select COUNT( MaPT) as'soPhongTrong' from PhongTro where MaPT not in (select MaPT from HopDong)

select * from phongTrong
--đếm tổng số phòng
create view tongPhong
as 
select COUNT(MaPT) as 'SoPhong' from PhongTro

select * from tongPhong
--đếm số khách hàng
create view tongKhachHang
as 
select COUNT(MaKH) as 'tongKhachHang' from KhachHang

select * from tongKhachHang
--tổng doanh thu
create proc [dbo].[sp_ThongKeDoanThu]
as begin
	select 
		distinct
		SUM(TongTien) TongTien,
		AVG(TongTien) doanhThuTB
	FROM dbo.HoaDon
	where  TinhTrang=1
END

exec sp_ThongKeDoanThu 
go

select * from HoaDon
--

SELECT * FROM dbo.KhachHang
SELECT * FROM dbo.PhongTro
SELECT * FROM dbo.HopDong
SELECT * FROM dbo.HoaDon

SELECT COUNT(MaPT)FROM dbo.PhongTro WHERE TinhTrang = N'Còn trống';

SELECT DISTINCT YEAR(NgayThue) FROM dbo.HopDong

SELECT COUNT(MaKH) 
FROM dbo.KhachHang JOIN dbo.HopDong ON HopDong.MaHopDong = KhachHang.MaHopDong
WHERE YEAR(NgayThue) = 2019 AND MONTH(NgayThue) = 1

SELECT DISTINCT YEAR(ThangNam) FROM dbo.HoaDon

SELECT SUM(TongTien) 
FROM dbo.HoaDon 
WHERE YEAR(ThangNam) = 2020 AND MONTH(ThangNam) = 10 AND TinhTrang = 1;