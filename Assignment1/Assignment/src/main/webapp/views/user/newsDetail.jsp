<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>Chi tiết tin tức</title>
    <style>
        body {
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            margin: 20px auto;
            max-width: 800px;
            background-color: #f9f9f9;
            color: #333;
        }
        h1.title {
            font-size: 2.5rem;
            color: #2c3e50;
            margin-bottom: 0.3em;
            border-bottom: 2px solid #fbbf24;
            padding-bottom: 0.3em;
        }
        .meta {
            font-size: 0.9rem;
            color: #888;
            margin-bottom: 1.5em;
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
        }
        .meta span {
            display: flex;
            align-items: center;
            gap: 5px;
        }
        .image-container {
            text-align: center;
            margin-bottom: 1.5em;
        }
        .image-container img {
            max-width: 100%;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .content {
            font-size: 1.1rem;
            line-height: 1.7;
            white-space: pre-wrap; /* giữ xuống dòng */
            color: #444;
        }
        .back-link {
            margin-top: 2em;
            display: inline-block;
            text-decoration: none;
            background-color: #fbbf24;
            color: #1e293b;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .back-link:hover {
            background-color: #f59e0b;
            color: white;
        }
    </style>
</head>
<body>

    <c:if test="${not empty news}">
        <h1 class="title">${news.title}</h1>

        <div class="meta">
            <span>📅 <fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy" /></span>
            <span>✍️ Tác giả: ${news.authorName}</span>
            <span>📂 Thể loại: ${news.categoryName}</span>
            <span>👁️ Lượt xem: ${news.viewCount}</span>
        </div>

        <div class="image-container">
            <c:if test="${not empty news.image}">
                <img src="${news.image}" alt="${news.title}" />
            </c:if>
        </div>

        <div class="content">${news.content}</div>

        <a href="${pageContext.request.contextPath}/index" class="back-link">← Quay lại trang chủ</a>
    </c:if>

    <c:if test="${empty news}">
        <p>Không tìm thấy bài viết.</p>
        <a href="${pageContext.request.contextPath}/index" class="back-link">← Quay lại trang chủ</a>
    </c:if>

</body>
</html>
