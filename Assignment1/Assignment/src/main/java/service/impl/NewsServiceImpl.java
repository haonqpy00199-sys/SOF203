package service.impl;

import java.util.List;
import java.util.Random;
import java.util.Collections;

import DAO.NewsDAO;
import entity.News;
import service.interf.NewsService;

public class NewsServiceImpl implements NewsService {
    private NewsDAO newsDao = new NewsDAO();

    @Override
    public List<News> findAll() {
        try {
            List<News> list = newsDao.selectAll();
            if (list == null || list.isEmpty()) {
                System.out.println("[INFO] Không có tin tức nào trong cơ sở dữ liệu!");
                return Collections.emptyList();
            }
            return list;
        } catch (Exception e) {
            System.err.println("[ERROR] Lỗi khi lấy danh sách tin tức: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public News findById(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                System.out.println("[WARN] ID tìm kiếm không hợp lệ!");
                return null;
            }
            News news = newsDao.selectByID(id);
            if (news == null) {
                System.out.println("[WARN] Không tìm thấy tin tức với ID: " + id);
            }
            return news;
        } catch (Exception e) {
            System.err.println("[ERROR] Lỗi khi tìm tin theo ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<News> findByCategory(String categoryId) {
        try {
            if (categoryId == null || categoryId.trim().isEmpty()) {
                System.out.println("[WARN] CategoryId không hợp lệ!");
                return Collections.emptyList();
            }
            // Dùng SQL join tương tự SELECT_ALL_SQL nếu muốn CategoryName + AuthorName
            String sql = "SELECT n.*, c.Name AS CategoryName, u.Fullname AS AuthorName "
                       + "FROM NEWS n "
                       + "LEFT JOIN CATEGORIES c ON n.CategoryId = c.Id "
                       + "LEFT JOIN USERS u ON n.Author = u.Id "
                       + "WHERE n.CategoryId = ?";
            List<News> list = newsDao.selectBySql(sql, categoryId);
            if (list.isEmpty()) {
                System.out.println("[INFO] Không có tin tức nào thuộc CategoryId: " + categoryId);
                return Collections.emptyList();
            }
            return list;
        } catch (Exception e) {
            System.err.println("[ERROR] Lỗi khi tìm tin theo CategoryId: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<News> searchByTitle(String keyword) {
        try {
            if (keyword == null) {
                keyword = "";
            }
            // Dùng SQL join tương tự SELECT_ALL_SQL
            String sql = "SELECT n.*, c.Name AS CategoryName, u.Fullname AS AuthorName "
                       + "FROM NEWS n "
                       + "LEFT JOIN CATEGORIES c ON n.CategoryId = c.Id "
                       + "LEFT JOIN USERS u ON n.Author = u.Id "
                       + "WHERE n.Title LIKE ?";
            List<News> list = newsDao.selectBySql(sql, "%" + keyword + "%");
            if (list.isEmpty()) {
                System.out.println("[INFO] Không tìm thấy tin nào chứa từ khóa: " + keyword);
                return Collections.emptyList();
            }
            return list;
        } catch (Exception e) {
            System.err.println("[ERROR] Lỗi khi tìm tin theo tiêu đề: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean insert(News news) {
        try {
            if (news == null) {
                System.err.println("[ERROR] Dữ liệu tin tức không hợp lệ (null)");
                return false;
            }
            if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
                System.err.println("[ERROR] Tiêu đề không được để trống");
                return false;
            }
            if (news.getCategoryId() == null || news.getCategoryId().trim().isEmpty()) {
                System.err.println("[ERROR] CategoryId không được để trống");
                return false;
            }
            if (news.getAuthor() == null || news.getAuthor().trim().isEmpty()) {
                System.err.println("[ERROR] Author không được để trống");
                return false;
            }

            // Sinh ID không trùng
            String randomId;
            do {
                randomId = generateRandomId(10);
            } while (newsDao.selectByID(randomId) != null);
            news.setId(randomId);

            // Default các trường Boolean nếu null
            if (news.getHome() == null) {
                news.setHome(false);
            }
            if (news.getStatus() == null) {
                news.setStatus(false);
            }

            newsDao.insert(news);
            System.out.println("[SUCCESS] Thêm tin tức thành công: " + news.getTitle() + " (ID: " + randomId + ")");
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR] Lỗi khi thêm tin tức: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(News news) {
        try {
            if (news == null || news.getId() == null || news.getId().trim().isEmpty()) {
                System.err.println("[ERROR] Dữ liệu cập nhật không hợp lệ");
                return false;
            }
            // Kiểm tra tồn tại trước khi cập nhật
            if (newsDao.selectByID(news.getId()) == null) {
                System.err.println("[ERROR] Không tìm thấy tin tức để cập nhật (ID: " + news.getId() + ")");
                return false;
            }

            // Default các trường Boolean nếu null
            if (news.getHome() == null) {
                news.setHome(false);
            }
            if (news.getStatus() == null) {
                news.setStatus(false);
            }

            newsDao.update(news);
            System.out.println("[SUCCESS] Cập nhật tin tức thành công: " + news.getId());
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR] Lỗi khi cập nhật tin tức: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                System.err.println("[ERROR] ID không hợp lệ!");
                return false;
            }
            // Kiểm tra tồn tại trước khi xóa (tùy chọn)
            if (newsDao.selectByID(id) == null) {
                System.err.println("[WARN] Không tìm thấy tin tức để xóa (ID: " + id + ")");
                return false;
            }
            newsDao.delete(id);
            System.out.println("[SUCCESS] Xóa tin tức thành công với ID: " + id);
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR] Lỗi khi xóa tin tức: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<News> findByStatus(boolean status) {
        return newsDao.selectByStatus(status);  // chú ý: tên method trong DAO là selectByStatus
    }

    @Override
    public void updateStatus(String id, boolean status) {
        newsDao.updateStatus(id, status);
    }
    
    @Override
    public int countDistinctAuthors() {
        try {
            String sql = "SELECT COUNT(DISTINCT Author) FROM NEWS";
            // Giả sử newsDao có method selectScalar trả về 1 giá trị đơn
            Integer count = newsDao.selectScalar(sql);
            return count != null ? count : 0;
        } catch (Exception e) {
            System.err.println("[ERROR] Lỗi khi đếm số tác giả: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    private String generateRandomId(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
