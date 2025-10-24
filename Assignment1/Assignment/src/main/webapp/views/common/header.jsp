<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="logo">🧭 BIG BLACK COCK NEWS</div>

        <!-- Menu điều hướng -->
        <nav>
		    <a href="/Assignment/index" class="active">Trang chủ</a>
		
		    <c:forEach var="c" items="${categories}">
		        <a href="/Assignment/category?id=${c.id}">
		            ${c.name}
		        </a>
		    </c:forEach>
		</nav>


        <!-- Khu vực người dùng -->
        <div class="user">
         <c:choose>
		    <c:when test="${not empty sessionScope.currentUser}">
		        <a href="/Assignment/logout">Đăng xuất</a>
		    </c:when>
		    <c:otherwise>
		        <a href="/Assignment/login">Đăng nhập</a>
		    </c:otherwise>
		</c:choose>

           
        </div>
    </header>
</body>
</html>
