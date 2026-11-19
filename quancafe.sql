CREATE DATABASE QuanLyQuanCafe;
use QuanLyQuanCafe;

create table danh_muc(
stt int identity (1,1) primary key,
Ten_Danh_Muc nvarchar(150) not null,
Mo_ta nvarchar(255),
Con_hoat_dong bit default 1
);

create table chon_size(
stt int identity (1,1) primary key,
Ten_size nvarchar(15) not null,
He_so_gia_lua_chon decimal(5,2) not null default 1.0
);

create table topping(
stt int identity (1,1) primary key,
Ten_topping nvarchar(150) not null,
Gia_ban decimal(10,2) not null default 0,
Con_hoat_dong bit default 1
);

create table do_uong(
stt int identity(1,1) primary key,
Ten_do_uong nvarchar(150) not null,
stt_danh_muc int null,
Gia_co_ban decimal(15,2) not null,
Mo_ta nvarchar(500),
Hinh_anh nvarchar(255),
Con_hoat_dong bit default 1,
Ngay_tao datetime default getdate(),
constraint fk_douong_danhmuc foreign key(stt_danh_muc)
references danh_muc(stt) on delete set null
);

create table ban(
stt int identity(1,1) primary key,
Ten_ban nvarchar(100) not null,
Khu_vuc nvarchar(100),
Trang_thai nvarchar(50) default N'Trống'
);

create table khach_hang(
stt int Identity(1,1) primary key,
ho_ten nvarchar(150) not null,
so_dien_thoai varchar(20) unique,
dia_chi nvarchar(150),
diem_tich_luy int default 0,
ngay_dang_ki datetime default getdate()
);

create table nhan_vien(
stt int identity(1,1) primary key,
ho_ten nvarchar(150) not null,
chuc_vu nvarchar(100),
so_dien_thoai varchar(20),
ngay_vao_lam date
);

create table tai_khoan(
stt int identity(1,1) primary key,
Ten_dang_nhap varchar(100) unique not null,
Mat_khau varchar(255) not null,
stt_nhan_vien int null,
role nvarchar(50) default 'nhanvien',
trang_thai bit default 1,
ngay_tao datetime default getdate(),
constraint fk_Tendangnhap_nhanvien foreign key (stt_nhan_vien)
references nhan_vien(stt) on delete set null
);

create table hoa_don(
stt int identity (1,1) primary key,
ma_hoa_don varchar(50) unique,
stt_khach int null,
stt_nhanvien int null,
stt_ban int null,
ngay_lap datetime default getdate(),
tong_tien decimal(18,2) default 0,
giam_gia decimal(18,2) default 0,
thanh_tien decimal(18,2) default 0,
phuong_thuc_thanh_toan nvarchar (50) default N'Tiền mặt',
trang_thai nvarchar(50) default'Hoàn thành',
ghi_chu nvarchar(300),
constraint fk_hoadon_khachhang foreign key (stt_khach)
references khach_hang(stt) on delete set null,
constraint fk_hoadon_nhanvien foreign key (stt_nhanvien)
references nhan_vien(stt) on delete set null,
constraint fk_hoadon_ban foreign key (stt_ban)
references ban(stt) on delete set null
);

create table chi_tiet_hoa_don(
stt int identity(1,1) primary key,
stt_hoa_don int not null,
stt_do_uong int not null,
stt_size int null,
so_luong int not null default 1,
don_gia decimal (18,2) not null,
ghi_chu nvarchar(255),
thanh_tien decimal(18,2) not null,
constraint fk_chitiethd_hoadon foreign key (stt_hoa_don)
references hoa_don(stt) on delete cascade,
constraint fk_chitiethd_douong foreign key (stt_do_uong)
references do_uong(stt) on delete cascade,
constraint fk_chitiethd_size foreign key (stt_size)
references chon_size(stt) on delete set null
);

create table chi_tiet_topping(
stt int identity(1,1) primary key,
stt_chi_tiet_hoa_don int not null,
stt_topping int not null,
so_luong int default 1,
gia decimal (12,2) not null,
constraint fk_chitiettp_chitiethd foreign key (stt_chi_tiet_hoa_don)
references chi_tiet_hoa_don(stt) on delete cascade,
constraint fk_chitiettp_topping foreign key (stt_topping)
references topping(stt)
);
INSERT INTO danh_muc (Ten_Danh_Muc, Mo_ta) VALUES
(N'Cà phê', N'Các loại cà phê truyền thống'),
(N'Trà sữa', N'Trà sữa các vị'),
(N'Trà trái cây', N'Trà đào, trà chanh tươi mát'),
(N'Đá xay', N'Sinh tố, đá xay'),
(N'Nước ép & Sinh tố', N'Các loại nước ép trái cây và sinh tố');

INSERT INTO chon_size (Ten_size, He_so_gia_lua_chon) VALUES
('S', 1.0),
('M', 1.2),
('L', 1.5);

INSERT INTO topping (Ten_topping, Gia_ban) VALUES
(N'Trân châu đen', 5000),
(N'Thạch trái cây', 5000),
(N'Pudding trứng', 7000),
(N'Kem phô mai', 8000),
(N'Trân châu trắng', 5000),
(N'Nha đam', 5000);

INSERT INTO do_uong (Ten_do_uong, stt_danh_muc, Gia_co_ban, Mo_ta) VALUES
(N'Cà phê đen', 1, 20000, N'Cà phê nguyên chất đậm vị'),
(N'Cà phê sữa', 1, 25000, N'Cà phê pha phin với sữa đặc'),
(N'Bạc xỉu', 1, 28000, N'Nhiều sữa, ít cà phê'),
(N'Trà sữa trân châu', 2, 35000, N'Trà sữa truyền thống kèm trân châu'),
(N'Trà sữa Matcha', 2, 40000, N'Trà sữa vị matcha Nhật Bản'),
(N'Trà đào cam sả', 3, 35000, N'Trà đào tươi mát với cam và sả'),
(N'Trà chanh', 3, 25000, N'Trà chanh sả giải nhiệt'),
(N'Sinh tố bơ', 4, 35000, N'Bơ tươi xay nhuyễn'),
(N'Đá xay Matcha', 4, 40000, N'Matcha xay đá mát lạnh'),
(N'Nước ép cam', 3, 30000, N'Nước cam tươi nguyên chất'),
(N'Nước mía', 3, 15000, N'Nước mía tươi mát lạnh'),
(N'Nước dừa tươi', 3, 25000, N'Nước dừa xiêm nguyên trái'),
(N'Trà vải', 3, 30000, N'Trà đen kết hợp hạt vải thơm ngon'),
(N'Trà sen vàng', 3, 35000, N'Trà olong, củ sen và hạt sen'),
(N'Nước chanh dây', 3, 25000, N'Nước chanh dây chua ngọt'),
(N'Sinh tố xoài', 5, 35000, N'Xoài chín xay nhuyễn'),
(N'Sinh tố dâu', 5, 35000, N'Dâu tây xay cùng sữa'),
(N'Sữa chua xay', 5, 30000, N'Sữa chua, đá và trái cây hỗn hợp'),
(N'Cà phê muối', 1, 30000, N'Cà phê hòa quyện lớp kem muối béo ngậy'),
(N'Trà sữa Thái xanh', 2, 35000, N'Trà sữa Thái Lan vị trà xanh');

INSERT INTO ban (Ten_ban, Khu_vuc, Trang_thai) VALUES
(N'Bàn 1', N'Tầng 1', N'Trống'),
(N'Bàn 2', N'Tầng 1', N'Trống'),
(N'Bàn 3', N'Tầng 1', N'Trống'),
(N'Bàn 4', N'Tầng 2', N'Trống'),
(N'Bàn 5', N'Tầng 2', N'Trống'),
(N'Bàn VIP', N'Sân vườn', N'Trống');

INSERT INTO khach_hang (ho_ten, so_dien_thoai, dia_chi, diem_tich_luy, ngay_dang_ki) VALUES
(N'Nguyen Van An', '0912345678', N'Số 12 ngõ 49 Thúy Lĩnh, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 15, '2023-01-15 08:30:00'),
(N'Tran Thi Binh', '0987654321', N'Số 65 ngõ 479 phố Linh Nam, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 5, '2023-03-20 14:15:00'),
(N'Le Van Cuong', '0903456789', N'Số 409 đường Tam Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 0, '2023-05-10 19:45:00'),
(N'Pham Minh Duc', '0356123987', N'Số 126 đường Tam Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 20, '2023-06-12 10:20:00'),
(N'Hoang Thu Trang', '0778451236', N'Ngõ 22 phố Tạm Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 8, '2023-08-05 16:00:00'),
(N'Vu Quoc Tuan', '0963712458', N'Số 8 ngách 34/12 phố Linh Nam, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 12, '2023-09-18 11:30:00'),
(N'Dang Thi Hoa', '0839261745', N'Số 215 đường Tam Trinh, P. Vĩnh Tuy, Q. Hai Bà Trưng, Hà Nội', 3, '2023-11-02 20:15:00'),
(N'Bui Quang Huy', '0782931564', N'Ngách 49/15 Thúy Lĩnh, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 30, '2024-01-10 09:00:00'),
(N'Do Ngoc Mai', '0387621459', N'Số 15 ngõ 196 Tam Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 0, '2024-02-14 18:30:00'),
(N'Ngo Thanh Tung', '0945812637', N'Số 88 phố Tạm Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 5, '2024-03-25 13:45:00'),
(N'Trinh Thu Ha', '0589124763', N'Số 37 ngõ 479 Linh Nam, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 18, '2024-04-12 08:20:00'),
(N'Phan Van Tai', '0867153492', N'Số 245 đường Tam Trinh, P. Hoàng Văn Thụ, Q. Hoàng Mai, Hà Nội', 7, '2024-05-30 15:10:00'),
(N'Cao Thi Kim', '0398127465', N'Ngõ 120 Tạm Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 10, '2024-07-08 19:20:00'),
(N'Le Hoang Nam', '0971583624', N'Số 54 ngõ 2 Thúy Lĩnh, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 2, '2024-08-15 17:40:00'),
(N'Vu Duc Huy', '0918374625', N'Số 310 đường Tam Trinh, P. Vĩnh Tuy, Q. Hai Bà Trưng, Hà Nội', 25, '2024-09-22 10:50:00'),
(N'Nguyen Thi Lan', '0347182659', N'Số 19 ngách 49/23 Linh Nam, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 5, '2024-11-05 14:00:00'),
(N'Bui Minh Anh', '0763182495', N'Số 105 phố Tạm Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 12, '2024-12-20 20:30:00'),
(N'Dang Quoc Bao', '0891745263', N'Số 76 ngõ 196 Tam Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 0, '2025-02-10 11:15:00'),
(N'Tran Van Kien', '0936814275', N'Số 23 ngõ 49 Thúy Lĩnh, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 8, '2025-04-18 09:45:00'),
(N'Pham Thu Trang', '0359182736', N'Số 158 đường Tam Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 15, '2025-07-05 18:20:00'),
(N'Hoang Van Long', '0974128356', N'Số 44 ngõ 479 Linh Nam, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội', 0, '2025-10-12 16:30:00'),
(N'Nguyen Mai Phuong', '0816372945', N'Số 92 Tạm Trinh, P. Mai Động, Q. Hai Bà Trưng, Hà Nội', 5, '2026-01-08 10:00:00');

INSERT INTO nhan_vien (ho_ten, chuc_vu, so_dien_thoai, ngay_vao_lam) VALUES
(N'Nguyen Van Hung', N'Quản lý', '0982746153', '2023-01-10'),
(N'Tran Quoc Bao', N'Quản lý', '0904382917', '2023-02-01'),
(N'Tran Thi Mai', N'Thu ngân', '0918273645', '2023-02-15'),
(N'Le Thu Ha', N'Thu ngân', '0357193842', '2023-03-20'),
(N'Pham Minh Tuan', N'Pha chế', '0774928571', '2023-04-10'),
(N'Hoang Ngoc Anh', N'Pha chế', '0963819247', '2023-05-15'),
(N'Do Van Binh', N'Pha chế', '0839271648', '2023-06-01'),
(N'Vu Duc Huy', N'Phục vụ', '0782619483', '2023-06-15'),
(N'Dang Thi Lan', N'Phục vụ', '0386472915', '2023-07-15'),
(N'Bui Quang Nam', N'Phục vụ', '0942837561', '2023-08-20'),
(N'Ngo Thanh Thao', N'Phục vụ', '0583928471', '2023-10-05'),
(N'Trinh Van Duc', N'Phục vụ', '0869153724', '2023-11-11');

INSERT INTO tai_khoan (Ten_dang_nhap, Mat_khau, stt_nhan_vien, role) VALUES
('quanly1', '123456', 1, 'ADMIN'),
('quanly2', '123456', 2, 'ADMIN'),
('thungan1', '123456', 3, 'NHANVIEN'),
('thungan2', '123456', 4, 'NHANVIEN'),
('phache1', '123456', 5, 'NHANVIEN'),
('phache2', '123456', 6, 'NHANVIEN'),
('phache3', '123456', 7, 'NHANVIEN'),
('phucvu1', '123456', 8, 'NHANVIEN'),
('phucvu2', '123456', 9, 'NHANVIEN'),
('phucvu3', '123456', 10, 'NHANVIEN'),
('phucvu4', '123456', 11, 'NHANVIEN'),
('phucvu5', '123456', 12, 'NHANVIEN');

UPDATE tk
SET tk.ngay_tao = DATEADD(hour, 8, CAST(nv.ngay_vao_lam AS DATETIME))
FROM tai_khoan tk
JOIN nhan_vien nv ON tk.stt_nhan_vien = nv.stt;