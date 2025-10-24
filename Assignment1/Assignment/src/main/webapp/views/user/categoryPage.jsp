<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${category.name}</title>

<style>
    body {
        font-family: "Segoe UI", Arial, sans-serif;
        background-color: #f1f5f9;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 90%;
        max-width: 1200px;
        margin: 40px auto;
    }

    h1 {
        color: #1e293b;
        border-bottom: 3px solid #fbbf24;
        padding-bottom: 10px;
        margin-bottom: 30px;
        text-transform: uppercase;
        letter-spacing: 1px;
    }

    .news-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
        gap: 25px;
    }

    .news-card {
        background-color: white;
        border-radius: 10px;
        overflow: hidden;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
        transition: transform 0.2s ease, box-shadow 0.2s ease;
    }

    .news-card:hover {
        transform: translateY(-4px);
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.25);
    }

    .news-card img {
        width: 100%;
        height: 180px;
        object-fit: cover;
    }

    .news-card .content {
        padding: 15px 20px;
    }

    .news-card h3 {
        font-size: 18px;
        color: #0f172a;
        margin-bottom: 8px;
    }

    .news-card p {
        font-size: 14px;
        color: #475569;
        margin-bottom: 12px;
        line-height: 1.5;
        height: 60px;
        overflow: hidden;
    }

    .news-card a {
        display: inline-block;
        color: #f59e0b;
        text-decoration: none;
        font-weight: bold;
        transition: color 0.2s;
    }

    .news-card a:hover {
        color: #b45309;
    }

    .no-news {
        text-align: center;
        color: #64748b;
        font-style: italic;
        margin-top: 50px;
        font-size: 18px;
    }
</style>
</head>
<body>

    <%@ include file="/views/common/header.jsp" %>

    <div class="container">
        <h1>${category.name}</h1>

        <c:choose>
            <c:when test="${empty listNews}">
                <p class="no-news">Hiện chưa có bài viết nào trong thể loại này.</p>
            </c:when>

            <c:otherwise>
                <div class="news-grid">
                    <c:forEach var="news" items="${listNews}">
                        <div class="news-card">
                            <c:if test="${not empty news.image}">
                                <img src="/Assignment/uploads/${news.image}" alt="${news.title}">
                            </c:if>
                            <div class="content">
                                <h3>${news.title}</h3>
                                
                                <%-- ✅ Tự động cắt 120 ký tự đầu tiên của nội dung làm tóm tắt --%>
                                <p>
                                    ${fn:substring(news.content, 0, 120)}...
                                </p>
                                
                                <a href="/Assignment/newsDetail?id=${news.id}">Xem chi tiết →</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
