package controller;

import java.io.IOException;
import java.util.List;

import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.CategoryServiceImpl;
import service.impl.NewsServiceImpl;
import service.impl.UserServiceImpl;
import service.interf.CategoryService;
import service.interf.NewsService;
import service.interf.UserService;

@WebServlet({"/index", "/indexAdmin"})
public class homeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoryService categoryService = new CategoryServiceImpl();
    private NewsService newsService = new NewsServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();

        // Lấy danh sách thể loại cho header hoặc menu
        req.setAttribute("categories", categoryService.findAll());

        if (path.equals("/index")) {
            doGetIndex(req, resp);
        } else if (path.equals("/indexAdmin")) {
            doGetIndexAdmin(req, resp);
        }
    }

    private void doGetIndex(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy tất cả bài viết
        List<News> allNews = newsService.findAll();

        // Sắp xếp sao cho các bài có home = true lên đầu, 
        // trong nhóm home = true thì sắp xếp theo ngày đăng (gần nhất lên trước),
        // các bài còn lại sẽ xếp sau cũng theo ngày đăng (gần nhất lên trước)
        List<News> sortedNews = allNews.stream()
            .sorted((n1, n2) -> {
                // Bài home=true lên trước
                if (n1.getHome() && !n2.getHome()) return -1;
                if (!n1.getHome() && n2.getHome()) return 1;
                // Cùng home status thì sắp xếp theo ngày đăng, gần nhất lên trước
                return n2.getPostedDate().compareTo(n1.getPostedDate());
            })
            .toList();

        req.setAttribute("allNews", sortedNews);

        // Lấy top 5 bài có lượt xem nhiều nhất (bạn có thể giữ nguyên hoặc cập nhật theo yêu cầu)
        List<News> topNews = allNews.stream()
                .sorted((n1, n2) -> Integer.compare(n2.getViewCount(), n1.getViewCount()))
                .limit(5)
                .toList();
        req.setAttribute("topNews", topNews);

        // Forward tới JSP userIndex.jsp
        req.getRequestDispatcher("/views/user/userIndex.jsp").forward(req, resp);
    }

    protected void doGetIndexAdmin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // Lấy tất cả bài viết
        List<News> allNews = newsService.findAll();
        req.setAttribute("allNews", allNews);
        
        // Tổng số bài viết
        req.setAttribute("totalNews", allNews.size());

        // Tổng số thể loại
        req.setAttribute("totalCategories", categoryService.findAll().size());

        // Tổng số người dùng
        req.setAttribute("totalUsers", userService.findAll().size());

        // Tổng số tác giả (distinct theo user id trong news)
        req.setAttribute("totalAuthors", newsService.countDistinctAuthors());

        // Tổng lượt xem tất cả bài viết
        req.setAttribute("totalViewCount", allNews.stream().mapToInt(News::getViewCount).sum());

        // Forward đến trang quản trị
        req.getRequestDispatcher("/views/admin/indexAdmin.jsp").forward(req, resp);
    }
}
