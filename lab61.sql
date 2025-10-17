create database HRM;
go 

use HRM;
go
-- 1.1
CREATE TABLE Departments(
Id CHAR(3) NOT NULL,
Name NVARCHAR(50) NOT NULL,
Description NVARCHAR(255) NULL,
PRIMARY KEY(Id)
);

CREATE TABLE Employees(
 Id VARCHAR(20) NOT NULL,
 Password NVARCHAR(50) NOT NULL,
 Fullname NVARCHAR(100) NOT NULL,
 Photo NVARCHAR(50) NOT NULL,
 Gender BIT NOT NULL,
 Birthday DATE NOT NULL,
 Salary FLOAT NOT NULL,
 DepartmentId CHAR(3) NOT NULL,
 PRIMARY KEY(Id),
 FOREIGN KEY(DepartmentId) REFERENCES Departments(Id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);
-- Thêm data mẫu cho DEPARTMENTS 
INSERT INTO Departments (Id, Name, Description) VALUES
('D01', N'Human Resources', N'Handles recruitment, training, and employee relations.'),
('D02', N'Information Technology', N'Manages systems, databases, and internal tools.'),
('D03', N'Finance', N'Oversees budgeting, accounting, and payroll operations.'),
('D04', N'Marketing', N'Plans advertising and promotional campaigns.'),
('D05', N'Operations', N'Manages daily business operations and logistics.');


-- Thêm data mẫu cho EMPLOYEES 
INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId) VALUES
('E001', '123', N'Nguyen Van A', 'a.jpg', 1, '1998-05-10', 1500.0, 'D01'),
('E002', '123', N'Tran Thi B', 'b.jpg', 0, '1997-09-25', 1800.0, 'D02'),
('E003', '123', N'Le Van C', 'c.jpg', 1, '1995-03-12', 2000.0, 'D03'),
('E004', '123', N'Pham Thi D', 'd.jpg', 0, '2000-07-21', 1700.0, 'D04'),
('E005', '123', N'Hoang Van E', 'e.jpg', 1, '1996-11-18', 1900.0, 'D02'),
('E006', '123', N'Do Thi F', 'f.jpg', 0, '1999-01-02', 1600.0, 'D01'),
('E007', '123', N'Bui Van G', 'g.jpg', 1, '1994-08-14', 2100.0, 'D03'),
('E008', '123', N'Nguyen Thi H', 'h.jpg', 0, '1998-12-05', 1750.0, 'D05');

-- 1.2

-- Truy vấn tất cả
SELECT * FROM Departments

-- Truy vấn theo khóa chính
SELECT * FROM Departments WHERE Id='D01';

-- Thêm mới
INSERT INTO Departments(Id, Name, Description) VALUES('D06','Marketing',N'Quang cao va ban hang');

-- Cập nhật theo khóa chính
UPDATE Departments SET Name=N'IT Department', Description=N'CNTT va giai phap' WHERE Id='D02';

-- Xóa theo khóa chính
DELETE FROM Departments WHERE Id='D06';