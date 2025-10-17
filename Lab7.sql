create database Lab07;
go

use Lab07;
go

CREATE TABLE Departments(
  Id VARCHAR(10) NOT NULL PRIMARY KEY,
  Name NVARCHAR(100) NOT NULL,
  Description NVARCHAR(255) NULL
);