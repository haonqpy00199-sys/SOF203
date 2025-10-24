package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.News;
import utils.jdbc;

public class NewsDAO extends daos<News, String> {

    private final String INSERT_SQL = """
        INSERT INTO NEWS (Id, Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home, Status)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    private final String UPDATE_SQL = """
        UPDATE NEWS 
        SET Title = ?, Content = ?, Image = ?, PostedDate = ?, Author = ?, 
            ViewCount = ?, CategoryId = ?, Home = ?, Status = ? 
        WHERE Id = ?
    """;

    private final String DELETE_SQL = "DELETE FROM NEWS WHERE Id = ?";

    private final String SELECT_ALL_SQL = """
        SELECT n.*, c.Name AS CategoryName, u.Fullname AS AuthorName
        FROM NEWS n
        LEFT JOIN CATEGORIES c ON n.CategoryId = c.Id
        LEFT JOIN USERS u ON n.Author = u.Id
    """;

    private final String SELECT_BY_ID_SQL = SELECT_ALL_SQL + " WHERE n.Id = ?";
    private final String SELECT_BY_TITLE_SQL = SELECT_ALL_SQL + " WHERE n.Title LIKE ?";
    private final String SELECT_BY_STATUS_SQL = SELECT_ALL_SQL + " WHERE n.Status = ?";

    private final String UPDATE_STATUS_SQL = "UPDATE NEWS SET Status = ? WHERE Id = ?";

    @Override
    public void insert(News entity) {
        jdbc.update(INSERT_SQL,
            entity.getId(),
            entity.getTitle(),
            entity.getContent(),
            entity.getImage(),
            entity.getPostedDate(),
            entity.getAuthor(),
            entity.getViewCount(),
            entity.getCategoryId(),
            entity.getHome() != null ? entity.getHome() : false,
            entity.getStatus() != null ? entity.getStatus() : false);
    }

    @Override
    public void update(News entity) {
        jdbc.update(UPDATE_SQL,
            entity.getTitle(),
            entity.getContent(),
            entity.getImage(),
            entity.getPostedDate(),
            entity.getAuthor(),
            entity.getViewCount(),
            entity.getCategoryId(),
            entity.getHome() != null ? entity.getHome() : false,
            entity.getStatus() != null ? entity.getStatus() : false,
            entity.getId());
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<News> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public News selectByID(String id) {
        List<News> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<News> selectByTitle(String title) {
        return this.selectBySql(SELECT_BY_TITLE_SQL, "%" + title + "%");
    }

    public List<News> selectByStatus(boolean status) {
        return this.selectBySql(SELECT_BY_STATUS_SQL, status);
    }

    public void updateStatus(String id, boolean status) {
        jdbc.update(UPDATE_STATUS_SQL, status, id);
    }

    @Override
    public List<News> selectBySql(String sql, Object... args) {
        List<News> list = new ArrayList<>();
        try (Connection conn = jdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    News entity = new News();
                    entity.setId(rs.getString("Id"));
                    entity.setTitle(rs.getString("Title"));
                    entity.setContent(rs.getString("Content"));
                    entity.setImage(rs.getString("Image"));
                    entity.setPostedDate(rs.getDate("PostedDate"));
                    entity.setAuthor(rs.getString("Author"));
                    entity.setViewCount(rs.getInt("ViewCount"));
                    entity.setCategoryId(rs.getString("CategoryId"));  // đây nhé
                    entity.setHome(rs.getBoolean("Home"));
                    entity.setStatus(rs.getBoolean("Status"));

                    entity.setCategoryName(rs.getString("CategoryName"));
                    entity.setAuthorName(rs.getString("AuthorName"));

                    list.add(entity);
                }
            }

        } catch (Exception e) {
            System.err.println("[ERROR][SELECT] " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public Integer selectScalar(String sql, Object... args) {
        Object val = jdbc.value(sql, args);
        if (val == null) {
            return null;
        }
        try {
            return Integer.parseInt(val.toString());
        } catch (NumberFormatException e) {
            System.err.println("[ERROR][selectScalar] Value is not a valid integer: " + val);
            return null;
        }
    }
}
