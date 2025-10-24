package controller;

import java.io.IOException;
import java.util.List;

import constant.SessionAttr;
import entity.Categories;
import entity.News;
import entity.Users;
import service.impl.CategoryServiceImpl;
import service.impl.NewsServiceImpl;
import service.impl.UserServiceImpl;         // <-- Thêm service User
import service.interf.CategoryService;
import service.interf.NewsService;
import service.interf.UserService;           // <-- Thêm interface UserService

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
    "/managermentUser",
    "/managermentCategory",
    "/managermentNews"
})
public class adminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CategoryService cateService = new CategoryServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();
    private final UserService userService = new UserServiceImpl();   // <-- Khởi tạo service user

    // ================== GET ==================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getServletPath();
        String action = req.getParameter("action");

        switch (path) {
            case "/managermentUser":
                if ("edit".equals(action)) {
                    String id = req.getParameter("id");
                    if (id != null && !id.trim().isEmpty()) {
                        Users user = userService.findById(id);
                        if (user != null) {
                            req.setAttribute("user", user);
                        } else {
                            req.setAttribute("message", "⚠️ Không tìm thấy người dùng với ID: " + id);
                        }
                    } else {
                        req.setAttribute("message", "⚠️ ID người dùng không hợp lệ!");
                    }
                }
                doGetManagermentUser(req, resp);
                break;

            case "/managermentCategory":
                if ("edit".equals(action)) {
                    String id = req.getParameter("id");
                    if (id != null && !id.trim().isEmpty()) {
                        Categories cate = cateService.findById(id);
                        if (cate != null) {
                            req.setAttribute("category", cate);
                        } else {
                            req.setAttribute("message", "⚠️ Không tìm thấy thể loại với ID: " + id);
                        }
                    } else {
                        req.setAttribute("message", "⚠️ ID thể loại không hợp lệ!");
                    }
                }
                doGetManagermentCategory(req, resp);
                break;

            case "/managermentNews":
                if ("edit".equals(action)) {
                    String id = req.getParameter("id");
                    if (id != null && !id.trim().isEmpty()) {
                        News news = newsService.findById(id);
                        if (news != null) {
                            req.setAttribute("news", news);
                        } else {
                            req.setAttribute("message", "⚠️ Không tìm thấy tin tức với ID: " + id);
                        }
                    } else {
                        req.setAttribute("message", "⚠️ ID tin tức không hợp lệ!");
                    }
                }
                doGetManagermentNews(req, resp);
                break;

            default:
                resp.sendRedirect("index");
        }
    }

    // ======= USER PAGE =======
    private void doGetManagermentUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Users> listUsers = userService.findAll();
        req.setAttribute("userList", listUsers);
        req.getRequestDispatcher("/views/admin/page/QLNguoiDung.jsp").forward(req, resp);
    }

    // ======= CATEGORY PAGE =======
    private void doGetManagermentCategory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Categories> list = cateService.findAll();
        req.setAttribute("cateList", list);
        req.getRequestDispatcher("/views/admin/page/QLTheLoai.jsp").forward(req, resp);
    }

    // ======= NEWS PAGE =======
    private void doGetManagermentNews(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<News> listNews = newsService.findAll();
        req.setAttribute("listNews", listNews);

        List<Categories> listCategory = cateService.findAll();
        req.setAttribute("cateList", listCategory);

        req.getRequestDispatcher("/views/admin/page/QLTinTuc.jsp").forward(req, resp);
    }

    // ================== POST ==================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getServletPath();
        String action = req.getParameter("action");

        // ⚠️ Fix lỗi NullPointerException
        if (action == null) {
            doGet(req, resp);
            return;
        }

        switch (path) {
            case "/managermentUser":
                handleUserActions(req, resp, action);
                break;

            case "/managermentCategory":
                handleCategoryActions(req, resp, action);
                break;

            case "/managermentNews":
                handleNewsActions(req, resp, action);
                break;

            default:
                doGet(req, resp);
        }
    }

    // ================== USER ACTIONS ==================
    private void handleUserActions(HttpServletRequest req, HttpServletResponse resp, String action)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String roleParam = req.getParameter("role");

        // Validate đầu vào (với add/update)
        if (!"delete".equals(action)) {
            if (username == null || username.trim().isEmpty()) {
                req.setAttribute("message", "⚠️ Tên đăng nhập không được để trống!");
                doGetManagermentUser(req, resp);
                return;
            }
            if (fullname == null || fullname.trim().isEmpty()) {
                req.setAttribute("message", "⚠️ Họ tên không được để trống!");
                doGetManagermentUser(req, resp);
                return;
            }
            if (email == null || email.trim().isEmpty()) {
                req.setAttribute("message", "⚠️ Email không được để trống!");
                doGetManagermentUser(req, resp);
                return;
            }
            if (roleParam == null || roleParam.trim().isEmpty()) {
                req.setAttribute("message", "⚠️ Vai trò không được để trống!");
                doGetManagermentUser(req, resp);
                return;
            }
        }

        Users user = new Users();
        user.setId(id);
        user.setEmail(username);
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password); // Lưu ý: nên hash password trước khi lưu trong thực tế
        }
        user.setFullname(fullname);
        user.setEmail(email);
        boolean role = Boolean.parseBoolean(roleParam);
        user.setRole(role);

        boolean success = false;
        switch (action) {
            case "add":
                success = userService.insert(user);
                if (success) {
                    req.getSession().setAttribute("message", "✅ Thêm người dùng thành công!");
                } else {
                    req.setAttribute("message", "⚠️ Thêm người dùng thất bại!");
                    doGetManagermentUser(req, resp);
                    return;
                }
                break;

            case "update":
                Users oldUser = userService.findById(id);
                if (oldUser == null) {
                    req.setAttribute("message", "⚠️ Người dùng không tồn tại!");
                    doGetManagermentUser(req, resp);
                    return;
                }
                // Nếu password rỗng thì giữ password cũ
                if (password == null || password.trim().isEmpty()) {
                    user.setPassword(oldUser.getPassword());
                }

                success = userService.update(user);
                if (success) {
                    req.getSession().setAttribute("message", "✏️ Cập nhật người dùng thành công!");
                } else {
                    req.setAttribute("message", "⚠️ Cập nhật người dùng thất bại!");
                    doGetManagermentUser(req, resp);
                    return;
                }
                break;

            case "delete":
                if (id == null || id.trim().isEmpty()) {
                    req.setAttribute("message", "⚠️ ID người dùng không hợp lệ!");
                    doGetManagermentUser(req, resp);
                    return;
                }
                success = userService.delete(id);
                if (success) {
                    req.getSession().setAttribute("message", "🗑️ Xóa người dùng thành công!");
                } else {
                    req.setAttribute("message", "⚠️ Xóa người dùng thất bại!");
                    doGetManagermentUser(req, resp);
                    return;
                }
                break;

            default:
                req.getSession().setAttribute("message", "⚠️ Hành động không hợp lệ!");
        }

        // Redirect để tránh resubmission
        resp.sendRedirect(req.getContextPath() + "/managermentUser");
    }

    // ================== CATEGORY ACTIONS ==================
    private void handleCategoryActions(HttpServletRequest req, HttpServletResponse resp, String action)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        if ((name == null || name.trim().isEmpty()) && !"delete".equals(action)) {
            req.setAttribute("message", "⚠️ Tên thể loại không được để trống!");
            doGetManagermentCategory(req, resp);
            return;
        }

        Categories cate = new Categories();
        cate.setId(id);
        cate.setName(name);

        switch (action) {
            case "add":
                cateService.insert(cate);
                req.getSession().setAttribute("message", "✅ Thêm thể loại thành công!");
                break;

            case "update":
                cateService.update(cate);
                req.getSession().setAttribute("message", "✏️ Cập nhật thể loại thành công!");
                break;

            case "delete":
                cateService.delete(id);
                req.getSession().setAttribute("message", "🗑️ Xóa thể loại thành công!");
                break;

            default:
                req.getSession().setAttribute("message", "⚠️ Hành động không hợp lệ!");
        }

        resp.sendRedirect(req.getContextPath() + "/managermentCategory");
    }

    // ================== NEWS ACTIONS ==================
    private void handleNewsActions(HttpServletRequest req, HttpServletResponse resp, String action)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String image = req.getParameter("image");
        String categoryId = req.getParameter("categoryId");

        Users loggedInUser = (Users) req.getSession().getAttribute(SessionAttr.CURRENT_USER);
        if (loggedInUser == null && !"delete".equals(action)) {
            req.setAttribute("message", "⚠️ Bạn cần đăng nhập để thực hiện thao tác này!");
            doGetManagermentNews(req, resp);
            return;
        }

        if ((title == null || title.trim().isEmpty()) && !"delete".equals(action)) {
            req.setAttribute("message", "⚠️ Tiêu đề không được để trống!");
            doGetManagermentNews(req, resp);
            return;
        }

        if ((categoryId == null || categoryId.trim().isEmpty()) && !"delete".equals(action)) {
            req.setAttribute("message", "⚠️ Thể loại không được để trống!");
            doGetManagermentNews(req, resp);
            return;
        }

        News news = new News();

        if ("add".equals(action)) {
            news.setId(null);
            news.setPostedDate(new java.util.Date());
            news.setViewCount(0);
        } else {
            if (id == null || id.trim().isEmpty()) {
                req.setAttribute("message", "⚠️ ID tin tức không hợp lệ!");
                doGetManagermentNews(req, resp);
                return;
            }
            news.setId(id);
        }

        news.setTitle(title);
        news.setContent(content);
        news.setImage(image);
        news.setCategoryId(categoryId);

        if (!"delete".equals(action)) {
            news.setAuthor(loggedInUser.getId());
        }

        news.setHome("on".equals(req.getParameter("home")));
        news.setStatus("on".equals(req.getParameter("status")));

        boolean success = false;
        switch (action) {
            case "add":
                success = newsService.insert(news);
                if (success) {
                    req.getSession().setAttribute("message", "✅ Đã thêm tin tức thành công!");
                } else {
                    req.setAttribute("message", "⚠️ Thêm tin tức thất bại!");
                    doGetManagermentNews(req, resp);
                    return;
                }
                break;

            case "update":
                News oldNews = newsService.findById(id);
                if (oldNews == null) {
                    req.setAttribute("message", "⚠️ Tin tức không tồn tại!");
                    doGetManagermentNews(req, resp);
                    return;
                }
                news.setPostedDate(oldNews.getPostedDate());
                news.setViewCount(oldNews.getViewCount());

                success = newsService.update(news);
                if (success) {
                    req.getSession().setAttribute("message", "✏️ Cập nhật tin tức thành công!");
                } else {
                    req.setAttribute("message", "⚠️ Cập nhật tin tức thất bại!");
                    doGetManagermentNews(req, resp);
                    return;
                }
                break;

            case "delete":
                success = newsService.delete(id);
                if (success) {
                    req.getSession().setAttribute("message", "🗑️ Đã xóa tin tức!");
                } else {
                    req.setAttribute("message", "⚠️ Xóa tin tức thất bại!");
                    doGetManagermentNews(req, resp);
                    return;
                }
                break;

            default:
                req.getSession().setAttribute("message", "⚠️ Hành động không hợp lệ!");
        }

        resp.sendRedirect(req.getContextPath() + "/managermentNews");
    }
}
