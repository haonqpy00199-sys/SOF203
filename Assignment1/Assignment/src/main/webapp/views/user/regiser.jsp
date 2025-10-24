<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<style>
    /* (giữ nguyên CSS như bạn gửi) */
</style>
</head>
<body>
    <jsp:include page="/views/common/header.jsp" />

    <div class="register-container">
        <h2>Đăng ký tài khoản</h2>
        <form action="/Assignment/register" method="post">
            
            <label for="fullname">Họ và tên</label>
            <input type="text" id="fullname" name="Fullname" placeholder="Nhập họ tên đầy đủ" required>

            <label for="birthday">Ngày sinh</label>
            <input type="date" id="birthday" name="Birthday" required>

            <label for="gender">Giới tính</label>
            <select id="gender" name="Gender" required>
                <option value="">-- Chọn giới tính --</option>
                <option value="true">Nam</option>
                <option value="false">Nữ</option>
            </select>

            <label for="mobile">Số điện thoại</label>
            <input type="tel" id="mobile" name="Mobile" placeholder="Nhập số điện thoại" required pattern="[0-9]{10,11}">

            <label for="email">Email</label>
            <input type="email" id="email" name="Email" placeholder="Nhập email" required>

            <label for="password">Mật khẩu</label>
            <input type="password" id="password" name="Password" placeholder="Nhập mật khẩu" required>

            <label for="cfmPass">Xác nhận mật khẩu</label>
            <input type="password" id="cfmPass" name="cfmPass" placeholder="Xác nhận mật khẩu" required>

            <button type="submit">Đăng ký</button>
        </form>

        <div class="login-link">
            <p>Đã có tài khoản? <a href="/Assignment/login">Đăng nhập ngay</a></p>
        </div>
    </div>

    <jsp:include page="/views/common/footer.jsp" />
</body>
</html>
