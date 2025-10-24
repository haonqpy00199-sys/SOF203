package controller;

import java.io.IOException;
import java.util.List;

import entity.News;
import entity.Categories;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.CategoryServiceImpl;
import service.impl.NewsServiceImpl;
import service.interf.CategoryService;
import service.interf.NewsService;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
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

        // Lấy danh sách thể loại để hiển thị ở header
        List<Categories> categories = categoryService.findAll();
        req.setAttribute("categories", categories);

        // Lấy thông tin thể loại hiện tại
        Categories category = categoryService.findById(id);
        req.setAttribute("category", category);

        // Lấy danh sách bài viết thuộc thể loại
        List<News> listNews = newsService.findByCategory(id);
        req.setAttribute("listNews", listNews);

        // Forward sang trang JSP
        req.getRequestDispatcher("/views/user/categoryPage.jsp").forward(req, resp);
    }
}
