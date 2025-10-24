package service.impl;

import java.util.List;
import java.util.Random;

import DAO.CategoriesDAO;
import entity.Categories;
import service.interf.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoriesDAO dao = new CategoriesDAO();

    @Override
    public List<Categories> findAll() {
        try {
            return dao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Categories findById(String id) {
        try {
            return dao.selectByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Categories> findByName(String name) {
        try {
            String sql = "SELECT * FROM CATEGORIES WHERE Name LIKE ?";
            return dao.selectBySql(sql, "%" + name + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Categories category) {
        try {
            // ✅ Chỉ sinh ID nếu chưa có
            if (category.getId() == null || category.getId().trim().isEmpty()) {
                String randomId = generateRandomId(10);
                category.setId(randomId);
            }

            dao.insert(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Categories category) {
        try {
            dao.update(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            dao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ✅ Hàm sinh ID ngẫu nhiên gồm chữ và số
     * @param length - độ dài ID mong muốn
     * @return chuỗi ID ngẫu nhiên, ví dụ: "A9B3X7T4Q2"
     */
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
