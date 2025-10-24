<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Trang Chủ Tin Tức</title>

<style>
  /* Reset cơ bản */
  * {
    margin: 0; padding: 0; box-sizing: border-box;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }

  body, html {
    width: 100vw;
    min-height: 100vh;
    background-color: #f9fafb;
  }

  /* CHỈ STYLE PHẦN MAIN - phần thân chính */
  main {
    width: 100vw;
    padding: 30px 5vw;
    max-width: 100%;
    display: flex;
    gap: 40px;
    background-color: #f9fafb;
  }

  /* Tin tức - phần lớn bên trái */
  .news-list {
    flex: 3;
    display: grid;
    grid-template-columns: repeat(auto-fill,minmax(300px,1fr));
    gap: 20px;
  }

  /* Card tin tức */
  .news-card {
    background: white;
    border-radius: 12px;
    box-shadow: 0 4px 10px rgb(0 0 0 / 0.1);
    overflow: hidden;
    display: flex;
    flex-direction: column;
    transition: transform 0.3s ease;
  }
  .news-card:hover {
    transform: translateY(-6px);
  }

  .news-card img {
    width: 100%;
    height: 180px;
    object-fit: cover;
  }

  .news-card .content {
    padding: 16px;
    flex-grow: 1;
    display: flex;
    flex-direction: column;
  }

  .news-card .title {
    font-weight: 700;
    font-size: 18px;
    margin-bottom: 8px;
    color: #1e293b;
    text-decoration: none;
  }

  .news-card .title:hover {
    color: #fbbf24;
  }

  .news-card .meta {
    font-size: 14px;
    color: #64748b;
    margin-bottom: 12px;
  }

  .news-card .excerpt {
    flex-grow: 1;
    font-size: 15px;
    color: #334155;
  }

  /* Phần Top 5 xem nhiều bên phải */
  aside.top-viewed {
    flex: 1;
    background: white;
    border-radius: 12px;
    padding: 20px;
    box-shadow: 0 4px 10px rgb(0 0 0 / 0.1);
    height: fit-content;
    position: sticky;
    top: 100px;
  }

  aside.top-viewed h2 {
    font-size: 22px;
    color: #1e293b;
    margin-bottom: 20px;
    border-bottom: 2px solid #fbbf24;
    padding-bottom: 6px;
  }

  aside.top-viewed ul {
    list-style: none;
  }

  aside.top-viewed li {
    margin-bottom: 18px;
  }

  aside.top-viewed li a {
    color: #334155;
    font-weight: 600;
    text-decoration: none;
    font-size: 16px;
    transition: color 0.3s ease;
  }
  aside.top-viewed li a:hover {
    color: #fbbf24;
  }

  aside.top-viewed li .views {
    font-size: 13px;
    color: #64748b;
  }

  /* Responsive */
  @media (max-width: 900px) {
    main {
      flex-direction: column;
    }
    aside.top-viewed {
      position: static;
      margin-top: 30px;
      width: 100%;
    }
  }
</style>
</head>
<body>

<!-- Giữ nguyên header, footer import ngoài -->
<jsp:include page="/views/common/header.jsp" />

<main>
  <!-- Danh sách bài viết -->
  <section class="news-list">
    <c:forEach var="news" items="${allNews}">
      <article class="news-card">
        <a href="/Assignment/newsDetail?id=${news.id}">
          <img src="${news.image}" alt="${news.title}" />
        </a>
        <div class="content">
          <a href="/Assignment/newsDetail?id=${news.id}" class="title">${news.title}</a>
          <div class="meta">
            <span>${news.categoryName}</span> | 
            <span>By ${news.authorName}</span> | 
            <span><fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy" /></span>
          </div>
          <p class="excerpt">
            <c:choose>
              <c:when test="${fn:length(news.content) > 120}">
                ${fn:substring(news.content,0,120)}...
              </c:when>
              <c:otherwise>
                ${news.content}
              </c:otherwise>
            </c:choose>
          </p>
        </div>
      </article>
    </c:forEach>
  </section>

  <!-- Top 5 bài xem nhiều -->
  <aside class="top-viewed">
    <h2>Top 5 bài xem nhiều</h2>
    <ul>
      <c:forEach var="top" items="${topNews}">
        <li>
          <a href="/news/detail?id=${top.id}">${top.title}</a>
          <div class="views">Lượt xem: ${top.viewCount}</div>
        </li>
      </c:forEach>
    </ul>
  </aside>
</main>

<jsp:include page="/views/common/footer.jsp" />

</body>
</html>
