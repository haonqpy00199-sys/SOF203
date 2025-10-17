-- Tạo CSDL
CREATE DATABASE Web;
GO
USE Web;
GO

-- Bảng Category
CREATE TABLE Category (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(100) NOT NULL
);

-- Bảng Users
CREATE TABLE Users (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Fullname NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    Mobile NVARCHAR(20),
    Birthday DATE,
    Gender BIT NOT NULL,             -- true = Nam, false = Nữ
    Role BIT NOT NULL,               -- true = Admin, false = Reporter
    Activated BIT NOT NULL DEFAULT 1
);

-- Bảng News
CREATE TABLE News (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Title NVARCHAR(200) NOT NULL,
    Content NVARCHAR(MAX) NOT NULL,
    Image NVARCHAR(255) NULL,
    PostedDate DATETIME2 NOT NULL DEFAULT GETDATE(),
    Author NVARCHAR(100) NULL,
    ViewCount INT NOT NULL DEFAULT 0,
    CategoryId INT NOT NULL,
    Home BIT NOT NULL DEFAULT 0,
    Approved BIT NOT NULL DEFAULT 0,
    ReporterId INT NULL,
    CONSTRAINT FK_News_Category FOREIGN KEY (CategoryId) REFERENCES Category(Id),
    CONSTRAINT FK_News_Reporter FOREIGN KEY (ReporterId) REFERENCES Users(Id)
);

-- Bảng Newsletter
CREATE TABLE Newsletter (
    Email NVARCHAR(100) PRIMARY KEY,
    Enabled BIT NOT NULL DEFAULT 1
);
