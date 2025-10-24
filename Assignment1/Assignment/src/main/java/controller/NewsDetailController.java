package controller;

import java.io.IOException;
import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.NewsServiceImpl;
import service.impl.CategoryServiceImpl;
import service.interf.NewsService;
import service.interf.CategoryService;

@WebServlet("/newsDetail")
public class NewsDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NewsService newsService = new NewsServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/index");
            return;
        }

        // Lấy bài viết theo ID
        News news = newsService.findById(id);
        if (news == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Tin tức không tồn tại");
            return;
        }

        // Tăng lượt xem lên 1
        Integer currentViewCount = news.getViewCount();
        if (currentViewCount == null) {
            currentViewCount = 0;
        }
        news.setViewCount(currentViewCount + 1);

        // Cập nhật lượt xem mới vào DB
        newsService.update(news);

        // Đưa đối tượng news vào request để hiển thị trong JSP
        req.setAttribute("news", news);

        // Load categories cho header
        req.setAttribute("categories", categoryService.findAll());

        // Forward đến trang chi tiết bài viết
        req.getRequestDispatcher("/views/user/newsDetail.jsp").forward(req, resp);
    }
}
