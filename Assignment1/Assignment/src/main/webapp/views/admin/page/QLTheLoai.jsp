<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý thể loại</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 40px;
        }
        .card {
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            border-radius: 12px;
        }
        .message {
            text-align: center;
            font-weight: 600;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <jsp:include page="/views/admin/common/header.jsp" />

    <div class="container">
        <h2 class="text-center text-primary mb-4">Quản lý thể loại</h2>

        <!-- Thông báo -->
        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-info text-center">${sessionScope.message}</div>
            <c:remove var="message" scope="session" />
        </c:if>

        <!-- Form thêm / sửa thể loại -->
        <div class="card p-4 mb-4">
            <form action="managermentCategory" method="post" class="row g-3">
                <!-- Hidden ID để sử dụng khi sửa hoặc xóa -->
                <input type="hidden" name="id" value="${category.id}" />

                <div class="col-md-12">
                    <label class="form-label fw-bold">Tên thể loại</label>
                    <input type="text" class="form-control" name="name" value="${category.name}" required>
                </div>

                <div class="col-12 text-end mt-3">
                    <c:choose>
                        <c:when test="${not empty category}">
                            <button type="submit" name="action" value="update" class="btn btn-warning text-white">Cập nhật</button>
                            <button type="submit" name="action" value="delete" class="btn btn-danger"
                                onclick="return confirm('Bạn có chắc muốn xóa thể loại này?')">Xóa</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" name="action" value="add" class="btn btn-success">Thêm mới</button>
                        </c:otherwise>
                    </c:choose>
                    <a href="managermentCategory" class="btn btn-secondary">Làm mới</a>
                </div>
            </form>
        </div>

        <!-- Danh sách thể loại -->
        <div class="card p-3">
            <h5 class="mb-3">Danh sách thể loại</h5>
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-primary text-center">
                    <tr>
                        <th style="width:20%">Mã</th>
                        <th style="width:60%">Tên thể loại</th>
                        <th style="width:20%">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cateList}">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td class="text-center">
                                <a href="managermentCategory?action=edit&id=${item.id}" class="btn btn-sm btn-info">Sửa</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty cateList}">
                        <tr>
                            <td colspan="3" class="text-center text-muted">Chưa có thể loại nào</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="/views/admin/common/footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
