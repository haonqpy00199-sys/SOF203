-- Chọn tất cả
CREATE OR ALTER PROCEDURE spInsert
    @Id VARCHAR(20),
    @Name NVARCHAR(50),
    @Description NVARCHAR(100)
AS
BEGIN
    INSERT INTO Departments(Id, Name, Description)
    VALUES(@Id, @Name, @Description)
END
GO

-- update giá trị Id của department
CREATE OR ALTER PROCEDURE spUpdate
    @Id VARCHAR(20),
    @Name NVARCHAR(50),
    @Description NVARCHAR(100)
AS
BEGIN
    UPDATE Departments
    SET Name = @Name, Description = @Description
    WHERE Id = @Id
END
GO


-- Xóa department record theo id
CREATE OR ALTER PROCEDURE spDeleteById
    @Id VARCHAR(20)
AS
BEGIN
    DELETE FROM Departments WHERE Id = @Id
END
GO

-- Chọn tất cả records của department
CREATE OR ALTER PROCEDURE spSelectAll
AS
BEGIN
    SELECT * FROM Departments
END
GO
-- Chọn record of department theo Id
-- 
CREATE OR ALTER PROCEDURE spSelectById
    @Id VARCHAR(20)
AS
BEGIN
    SELECT * FROM Departments WHERE Id = @Id
END
GO