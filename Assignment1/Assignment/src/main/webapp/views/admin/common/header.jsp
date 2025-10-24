<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Header Admin</title>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Segoe UI", Arial, sans-serif;
    }

    /* ====== HEADER ====== */
    header {
        background-color: #1e293b; /* xanh đậm */
        color: white;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 10px 30px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.2);
    }

    header .logo {
        font-size: 22px;
        font-weight: bold;
        color: #fbbf24; /* vàng */
    }

    header nav a {
        color: #e2e8f0;
        text-decoration: none;
        margin: 0 12px;
        font-size: 15px;
        transition: 0.2s;
    }

    header nav a:hover,
    header nav a.active {
        color: #fbbf24;
        border-bottom: 2px solid #fbbf24;
    }

    header .user {
        display: flex;
        align-items: center;
        gap: 10px;
        font-size: 15px;
    }

    header .user img {
        width: 32px;
        height: 32px;
        border-radius: 50%;
        object-fit: cover;
        border: 2px solid #fbbf24;
    }

    header .user a {
        color: #f87171; /* đỏ nhạt cho nút đăng xuất */
        text-decoration: none;
        font-size: 14px;
    }

    header .user a:hover {
        text-decoration: underline;
    }
</style>
</head>

<body>
    <header>
        <!-- Logo / Tên trang -->
        <div class="logo">🧭 Admin Panel</div>

        <!-- Menu điều hướng -->
        <nav>
            <a href="/Assignment/indexAdmin" class="active">Trang chủ</a>
            <a href="/Assignment/managermentUser" >Quản lý người dùng</a>
            <a href="/Assignment/managermentCategory">Quản lý thể loại</a>
            <a href="/Assignment/managermentNews">Quản lý Tin tức</a>
          
        </nav>

        <!-- Khu vực người dùng -->
        <div class="user">
            <img src="assets/admin-avatar.png" alt="Admin Avatar">
            <span>Xin chào, Admin</span>
            <a href="/Assignment/logout">Đăng xuất</a>
        </div>
    </header>
</body>
</html>
