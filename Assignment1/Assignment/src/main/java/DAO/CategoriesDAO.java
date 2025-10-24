package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Categories;
import utils.jdbc;

public class CategoriesDAO extends daos<Categories, String> {
    
    String INSERT_SQL = "INSERT INTO Categories(Id, Name) values (?, ?)";
    String UPDATE_SQL = "UPDATE CATEGORIES SET Name = ? WHERE Id = ?";
    String DELETE_SQL = "DELETE FROM CATEGORIES WHERE Id = ?";
    String SELECT_ALL_SQL = "SELECT * FROM CATEGORIES";
    String SELECT_BY_ID_SQL = "SELECT * FROM CATEGORIES WHERE Id = ?";
    String SELECT_BY_NAME_SQL = "SELECT * FROM CATEGORIES WHERE Name LIKE ?";

    @Override
    public void insert(Categories entity) {
        jdbc.update(INSERT_SQL,
            entity.getId(),
            entity.getName()
        );
    }

    @Override
    public void update(Categories entity) {
        // ✅ Đã fix thứ tự tham số: Name trước, Id sau
        jdbc.update(UPDATE_SQL,
            entity.getName(),
            entity.getId()
        );
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Categories> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Categories selectByID(String id) {
        List<Categories> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Categories> selectBySql(String sql, Object... args) {
        List<Categories> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while (rs.next()) {
                Categories entity = new Categories();
                entity.setId(rs.getString("Id"));
                entity.setName(rs.getString("Name"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
