package service.interf;

import java.util.List;
import entity.Categories;

public interface CategoryService {

    // Lấy tất cả thể loại
    List<Categories> findAll();

    // Tìm thể loại theo ID
    Categories findById(String id);

    // Tìm thể loại theo tên
    List<Categories> findByName(String name);

    // Thêm thể loại mới
    boolean insert(Categories category);

    // Cập nhật thể loại
    boolean update(Categories category);

    // Xóa thể loại theo ID
    boolean delete(String id);
}
