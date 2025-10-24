<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Tin tức</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .preview { width: 80px; height: 60px; object-fit: cover; border-radius: 6px; }
        .content-cell {
            max-width: 200px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
    <jsp:include page="/views/admin/common/header.jsp" />

    <div class="container mt-4">
        <h2 class="text-center text-primary mb-4">Quản lý Tin tức</h2>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-success text-center">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <!-- FORM THÊM / SỬA -->
        <div class="card p-4 mb-4">
            <form action="managermentNews" method="post" class="row g-3">
                <input type="hidden" name="id" value="${news != null ? news.id : ''}" />

                <div class="col-md-6">
                    <label class="form-label">Tiêu đề</label>
                    <input type="text" class="form-control" name="title" value="${news != null ? news.title : ''}" required />
                </div>

                <div class="col-md-6">
                    <label class="form-label">Loại tin</label>
                    <select class="form-select" name="categoryId" required>
                        <option value="">-- Chọn loại tin --</option>
                        <c:forEach var="cate" items="${cateList}">
                            <option value="${cate.id}" <c:if test="${cate.id == news.categoryId}">selected</c:if>>${cate.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Hình ảnh (URL)</label>
                    <input type="text" class="form-control" name="image" value="${news != null ? news.image : ''}" />
                    <c:if test="${not empty news.image}">
                        <img src="${news.image}" class="preview mt-2" />
                    </c:if>
                </div>

                <div class="col-md-3 form-check">
                    <input class="form-check-input" type="checkbox" name="home" id="home"
                        <c:if test="${news != null && news.home}">checked</c:if> />
                    <label class="form-check-label" for="home">Trang chủ</label>
                </div>

                <div class="col-md-3 form-check">
                    <input class="form-check-input" type="checkbox" name="status" id="status"
                        <c:if test="${news != null && news.status}">checked</c:if> />
                    <label class="form-check-label" for="status">Hiển thị</label>
                </div>

                <div class="col-12">
                    <label class="form-label">Nội dung</label>
                    <textarea class="form-control" name="content" rows="4" required>${news != null ? news.content : ''}</textarea>
                </div>

                <div class="col-12 text-end">
                    <button type="submit" name="action" value="add" class="btn btn-success">Thêm</button>
                    <button type="submit" name="action" value="update" class="btn btn-warning text-white">Cập nhật</button>
                    <button type="submit" name="action" value="delete" class="btn btn-danger"
                        onclick="return confirm('Bạn có chắc muốn xóa tin này?')">Xóa</button>
                    <a href="managermentNews" class="btn btn-secondary">Làm mới</a>

                </div>
            </form>
        </div>

        <!-- DANH SÁCH -->
        <div class="card p-3">
            <h5 class="mb-3 text-primary">Danh sách Tin tức</h5>
            <table class="table table-bordered table-hover">
                <thead class="table-primary text-center">
                    <tr>
                        <th>Mã</th>
                        <th>Tiêu đề</th>
                        <th>Loại tin</th>
                        <th>Tác giả</th>
                        <th>Hình ảnh</th>
                        <th>Nội dung</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="n" items="${listNews}">
                        <tr>
                            <td>${n.id}</td>
                            <td>${n.title}</td>
                            <td>
                                <c:forEach var="cate" items="${cateList}">
                                    <c:if test="${cate.id == n.categoryId}">${cate.name}</c:if>
                                </c:forEach>
                            </td>
                            <td>${n.author}</td>
                            <td class="text-center">
                                <c:if test="${not empty n.image}">
                                    <img src="${n.image}" class="preview" />
                                </c:if>
                            </td>
                            <td class="content-cell" title="${n.content}">${n.content}</td>
                            <td class="text-center">
                                <a href="managermentNews?action=edit&id=${n.id}" class="btn btn-sm btn-info">Sửa</a>
                                <form action="managermentNews" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${n.id}" />
                                    <button type="submit" name="action" value="delete" class="btn btn-sm btn-danger"
                                        onclick="return confirm('Xác nhận xóa?')">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty listNews}">
                        <tr><td colspan="7" class="text-center text-muted">Chưa có tin tức nào.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <jsp:include page="/views/admin/common/footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
