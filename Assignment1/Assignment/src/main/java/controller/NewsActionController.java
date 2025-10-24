package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.NewsServiceImpl;
import service.interf.NewsService;

@WebServlet("/newsAction")
public class NewsActionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NewsService newsService = new NewsServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String newsId = req.getParameter("id");

        if (newsId == null || action == null) {
            resp.sendRedirect(req.getContextPath() + "/indexAdmin");
            return;
        }

        boolean newStatus;
        if (action.equals("approve")) {
            newStatus = true;
        } else if (action.equals("reject")) {
            newStatus = false;
        } else {
            resp.sendRedirect(req.getContextPath() + "/indexAdmin");
            return;
        }

        newsService.updateStatus(newsId, newStatus);

        resp.sendRedirect(req.getContextPath() + "/indexAdmin");
    }
}
