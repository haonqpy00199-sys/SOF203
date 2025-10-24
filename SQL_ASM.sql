-- Tạo database
CREATE DATABASE NewsPortal;
GO
USE NewsPortal;
GO

-- Bảng loại tin
CREATE TABLE CATEGORIES (
    Id NVARCHAR(10) PRIMARY KEY,       -- Mã loại tin
    Name NVARCHAR(100) NOT NULL        -- Tên loại tin
);
GO

-- Bảng người dùng
CREATE TABLE USERS (
    Id NVARCHAR(20) PRIMARY KEY,       -- Mã đăng nhập
    Password NVARCHAR(100) NOT NULL,   -- Mật khẩu
    Fullname NVARCHAR(100) NOT NULL,   -- Họ và tên
    Birthday DATE NULL,                -- Ngày sinh
    Gender BIT NULL,                   -- Giới tính (1: Nam, 0: Nữ)
    Mobile NVARCHAR(15) NULL,          -- ✅ Điện thoại (sửa lỗi Moble)
    Email NVARCHAR(100) NULL,          -- Email
    Role BIT NOT NULL DEFAULT 0        -- Vai trò (1: Quản trị, 0: Phóng viên)
);
GO

-- Bảng tin tức
CREATE TABLE NEWS (
    Id NVARCHAR(10) PRIMARY KEY,       -- Mã bản tin
    Title NVARCHAR(200) NOT NULL,      -- Tiêu đề
    Content NVARCHAR(MAX) NULL,        -- Nội dung
    Image NVARCHAR(255) NULL,          -- Hình ảnh hoặc video
    PostedDate DATE NOT NULL,          -- Ngày đăng
    Author NVARCHAR(20) NOT NULL,      -- Mã phóng viên
    ViewCount INT DEFAULT 0,           -- Số lượt xem
    CategoryId NVARCHAR(10) NOT NULL,  -- Mã loại tin
    Home BIT DEFAULT 0,                -- Trang nhất (1: có, 0: không)
    Position INT NULL,                 -- ✅ Thêm cột Position để fix lỗi code
    CONSTRAINT FK_NEWS_AUTHOR FOREIGN KEY (Author) REFERENCES USERS(Id),
    CONSTRAINT FK_NEWS_CATEGORY FOREIGN KEY (CategoryId) REFERENCES CATEGORIES(Id)
);
GO

-- Bảng newsletters
CREATE TABLE NEWSLETTERS (
    Email NVARCHAR(100) PRIMARY KEY,   -- Email nhận tin
    Enabled BIT DEFAULT 1              -- Trạng thái (1: còn hiệu lực, 0: ngưng)
);
GO
