<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/views/admin/common/header.jsp"/>

<div class="container mt-5">
    <h2 class="text-center mb-4">Quản lý người dùng</h2>

    <!-- Thông báo -->
    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>

    <!-- Form thêm/sửa -->
    <form action="managermentUser" method="post" class="row g-3 mb-4">
        <input type="hidden" name="action" value="${empty user ? 'add' : 'update'}"/>
        <input type="hidden" name="id" value="${user.id}"/>

        <div class="col-md-4">
            <label>Tên đăng nhập (email):</label>
            <input type="text" name="username" value="${user.email}" class="form-control" required>
        </div>

        <div class="col-md-4">
            <label>Mật khẩu:</label>
            <input type="password" name="password" class="form-control" placeholder="${empty user ? '' : 'Để trống nếu không đổi'}">
        </div>

        <div class="col-md-4">
            <label>Họ tên:</label>
            <input type="text" name="fullname" value="${user.fullname}" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label>Email:</label>
            <input type="email" name="email" value="${user.email}" class="form-control" required>
        </div>

        <div class="col-md-3">
            <label>Vai trò:</label>
            <select name="role" class="form-select" required>
                <option value="true" ${user.role ? 'selected' : ''}>Admin</option>
                <option value="false" ${!user.role ? 'selected' : ''}>Người dùng</option>
            </select>
        </div>

        <div class="col-md-3 d-flex align-items-end">
            <button type="submit" class="btn btn-${empty user ? 'success' : 'primary'} w-100">
                ${empty user ? 'Thêm người dùng' : 'Cập nhật'}
            </button>
        </div>

        <div class="col-md-12">
            <a href="managermentUser" class="btn btn-secondary">Làm mới</a>
        </div>
    </form>

    <!-- Danh sách người dùng -->
    <table class="table table-bordered table-hover text-center">
        <thead class="table-light">
            <tr>
                <th>STT</th>
                <th>Tên đăng nhập</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Vai trò</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${userList}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${user.id}</td>
                    <td>${user.fullname}</td>
                    <td>${user.email}</td>
                    <td>
                        <span class="badge bg-${user.role ? 'primary' : 'secondary'}">
                            ${user.role ? 'Admin' : 'User'}
                        </span>
                    </td>
                    <td>
                        <a href="managermentUser?action=edit&id=${user.id}" class="btn btn-sm btn-warning">Sửa</a>
                        <form action="managermentUser" method="post" style="display:inline-block;">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="id" value="${user.id}"/>
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Xóa người dùng này?');">Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/views/admin/common/footer.jsp"/>
</body>
</html>
