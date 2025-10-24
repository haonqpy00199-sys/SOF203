package service.interf;

import java.util.List;
import entity.News;

public interface NewsService {
    List<News> findAll();                       // Lấy tất cả tin tức
    News findById(String id);                   // Tìm tin theo ID
    List<News> findByCategory(String categoryId);// Tìm tin theo loại
    List<News> searchByTitle(String keyword);   // Tìm tin theo tiêu đề
    boolean insert(News news);                  // Thêm tin mới
    boolean update(News news);                  // Cập nhật tin
    boolean delete(String id);                  // Xóa tin
    int countDistinctAuthors();
    List<News> findByStatus(boolean status);   // thêm phương thức này
    void updateStatus(String id, boolean status); // thêm phương thức cập nhật trạng thái
}
