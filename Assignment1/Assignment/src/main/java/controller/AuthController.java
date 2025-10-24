package controller;

import java.io.IOException;
import java.util.Date;

import constant.SessionAttr;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.impl.UserServiceImpl;
import service.interf.UserService;

@WebServlet({"/login",
		"/logout",
		"/register"
		})
public class AuthController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	    String path = req.getServletPath();

	    if (path.equals("/login")) {
	        doGetLogin(req, resp);
	    } else if (path.equals("/logout")) {
	        doGetLogout(session, req, resp);
	    } else if (path.equals("/register")) {
	        doGetRegiser(req, resp);
	    }

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session  = req.getSession();
		String path = req.getServletPath();
		if(path.equals("/login")) {
			doPossLogin(session, req, resp);
		}else if(path.equals("/register")) {
			doPossRegiser(session, req, resp);
		}
	}
	
	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
	}
	
	private void doGetRegiser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/regiser.jsp").forward(req, resp);
	}
	
	private void doGetLogout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute(SessionAttr.CURRENT_USER);
		resp.sendRedirect("index");
	}
	
	private void doPossLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("email");
        String password = req.getParameter("password");
		Users user = userService.login(username, password);
		if(user != null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			if (user.getRole()) {
				resp.sendRedirect("indexAdmin");
			} else {
				resp.sendRedirect("index");
			}
			
		}else {
			req.setAttribute("errorMessage", "Password or account mismatch!");
			doGetLogin(req, resp);
		}
	}
	
	private void doPossRegiser(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");

	    // Lấy dữ liệu từ form - tên biến trùng với name attribute trong form
	    String fullname = req.getParameter("Fullname");
	    String password = req.getParameter("Password");
	    String cfmPass = req.getParameter("cfmPass");
	    String email = req.getParameter("Email");
	    String mobile = req.getParameter("Mobile");
	    String birthdayStr = req.getParameter("Birthday");
	    String genderStr = req.getParameter("Gender");

	    // Kiểm tra email đã tồn tại chưa
	    Users existingUserByEmail = userService.findAll().stream()
	        .filter(u -> u.getEmail() != null && u.getEmail().equalsIgnoreCase(email))
	        .findFirst()
	        .orElse(null);
	    if (existingUserByEmail != null) {
	        req.setAttribute("errorMessage", "Email đã tồn tại!");
	        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	        return;
	    }

	    // Validate fullname
	    if (fullname == null || fullname.trim().length() < 3 || !fullname.matches("[a-zA-Z\\s]+")) {
	        req.setAttribute("errorMessage", "Họ tên không hợp lệ! Ít nhất 3 ký tự và chỉ chứa chữ cái.");
	        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	        return;
	    }

	    // Validate password
	    if (password == null || password.length() < 8) {
	        req.setAttribute("errorMessage", "Mật khẩu phải có ít nhất 8 ký tự!");
	        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	        return;
	    }

	    // Kiểm tra mật khẩu xác nhận
	    if (!password.equals(cfmPass)) {
	        req.setAttribute("errorMessage", "Mật khẩu xác nhận không khớp!");
	        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	        return;
	    }

	    // Validate email
	    if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$")) {
	        req.setAttribute("errorMessage", "Email không hợp lệ!");
	        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	        return;
	    }

	    // Parse birthday
	    Date birthday = null;
	    try {
	        if (birthdayStr != null && !birthdayStr.isEmpty()) {
	            birthday = java.sql.Date.valueOf(birthdayStr);
	        }
	    } catch (Exception e) {
	        req.setAttribute("errorMessage", "Ngày sinh không hợp lệ!");
	        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	        return;
	    }

	    // Parse gender
	    Boolean gender = null;
	    if (genderStr != null && !genderStr.isEmpty()) {
	        gender = Boolean.parseBoolean(genderStr);
	    }

	    // Tạo user mới
	    Users newUser = new Users();
	    newUser.setFullname(fullname);
	    newUser.setPassword(password);
	    newUser.setEmail(email);
	    newUser.setMobile(mobile);
	    newUser.setBirthday(birthday);
	    newUser.setGender(gender);
	    newUser.setRole(false); // mặc định user thường

	    boolean inserted = userService.insert(newUser);
	    if (inserted) {
	        session.setAttribute(SessionAttr.CURRENT_USER, newUser);
	        resp.sendRedirect("index");
	    } else {
	        req.setAttribute("errorMessage", "Đăng ký thất bại!");
	        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	    }
	}
}
