<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, entity.News" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý bài báo - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f5f6fa; }
        .container { margin-top: 40px; }
        .table th, .table td { vertical-align: middle; }
        .status-approved { color: green; font-weight: 600; }
        .status-pending { color: orange; font-weight: 600; }
    </style>
</head>
<body>

<jsp:include page="/views/admin/common/header.jsp" />

<div class="container">
    <h2 class="text-center mb-4 text-primary">Trang quản trị - Quản lý bài báo</h2>

    <!-- Thống kê -->
    <div class="mb-4">
        <span class="me-3">Tổng bài viết: <strong>${totalNews}</strong></span>
        <span class="me-3">Tổng thể loại: <strong>${totalCategories}</strong></span>
        <span class="me-3">Tổng người dùng: <strong>${totalUsers}</strong></span>
        <span class="me-3">Tổng tác giả: <strong>${totalAuthors}</strong></span>
        <span>Tổng lượt xem: <strong>${totalViewCount}</strong></span>
    </div>

    <!-- Bảng danh sách bài báo chưa duyệt (status = false) -->
    <table class="table table-hover table-bordered bg-white shadow-sm">
        <thead class="table-light">
            <tr class="text-center">
                <th>Mã tin</th>
                <th>Tiêu đề</th>
                <th>Tác giả</th>
                <th>Loại tin</th>
                <th>Ngày đăng</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<News> allNews = (List<News>) request.getAttribute("allNews");
                for (News news : allNews) {
                    if (!news.getStatus()) { // chỉ hiển thị bài chưa duyệt (status = false)
            %>
            <tr class="text-center">
                <td><%= news.getId() %></td>
                <td class="text-start"><%= news.getTitle() %></td>
                <td><%= news.getAuthorName() != null ? news.getAuthorName() : news.getAuthor() %></td>
                <td><%= news.getCategoryName() %></td>
                <td><%= news.getPostedDate() %></td>
                <td><span class="status-pending">Pending</span></td>
                <td>
                    <form method="post" action="newsAction">
                        <input type="hidden" name="id" value="<%= news.getId() %>" />
                        <button type="submit" name="action" value="approve" class="btn btn-success btn-sm">Duyệt</button>
                        <button type="submit" name="action" value="reject" class="btn btn-danger btn-sm">Từ chối</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <div class="d-flex justify-content-between mt-3">
        <a href="/Assignment/managermentNews" class="btn btn-secondary">Thêm bài mới</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="/views/admin/common/footer.jsp" />
</body>
</html>
