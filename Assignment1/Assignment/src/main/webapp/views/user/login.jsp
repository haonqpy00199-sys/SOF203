<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<style>
    body {
        font-family: "Segoe UI", Arial, sans-serif;
        background-color: #f5f7fa;
        margin: 0;
        padding: 0;
    }

    .login-container {
        width: 400px;
        margin: 80px auto;
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        padding: 30px 40px;
    }

    .login-container h2 {
        text-align: center;
        color: #333;
        margin-bottom: 25px;
    }

    .form-group {
        margin-bottom: 18px;
    }

    label {
        display: block;
        font-weight: 600;
        margin-bottom: 6px;
        color: #333;
    }

    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-sizing: border-box;
    }

    input:focus {
        border-color: #3B82F6;
        outline: none;
    }

    .btn-login {
        width: 100%;
        background-color: #3B82F6;
        color: #fff;
        border: none;
        padding: 12px;
        border-radius: 8px;
        cursor: pointer;
        font-weight: 600;
        transition: 0.3s;
    }

    .btn-login:hover {
        background-color: #2563EB;
    }

    .message {
        color: red;
        text-align: center;
        margin-bottom: 10px;
    }

    .register-link {
        text-align: center;
        margin-top: 15px;
    }

    .register-link a {
        color: #3B82F6;
        text-decoration: none;
    }

    .register-link a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <jsp:include page="/views/common/header.jsp" />

    <div class="login-container">
        <h2>Đăng nhập</h2>

        <!-- Thông báo lỗi hoặc thành công -->
        <c:if test="${not empty errorMessage}">
            <p class="message">${errorMessage}</p>
        </c:if>

        <!-- Form đăng nhập -->
        <form action="<%=request.getContextPath()%>/login" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Nhập email của bạn" required />
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" placeholder="Nhập mật khẩu" required />
            </div>

            <button type="submit" class="btn-login">Đăng nhập</button>

            <div class="register-link">
                <p>Chưa có tài khoản? <a href="<%=request.getContextPath()%>/register">Đăng ký</a></p>
            </div>
        </form>
    </div>

    <jsp:include page="/views/common/footer.jsp" />
</body>
</html>
