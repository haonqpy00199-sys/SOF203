package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Users;
import utils.jdbc;

public class UsersDAO extends daos<Users, String> {

    String INSERT_SQL = "INSERT INTO USERS(Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE USERS SET Password= ?, Fullname = ?, Birthday =?, Gender = ?, Mobile =?, Email =?, Role= ? WHERE Id = ?";
    String DELETE_SQL = "DELETE FROM USERS WHERE Id = ?";
    String SELECT_ALL_SQL = "SELECT * FROM USERS";
    String SELECT_BY_ID_SQL = "SELECT * FROM USERS WHERE Id=?";
    String SELECT_BY_EMAIL = "SELECT * FROM USERS WHERE Email=?";

    @Override
    public void insert(Users entiTy) {
        java.sql.Date sqlDate = null;
        if (entiTy.getBirthday() != null) {
            sqlDate = new java.sql.Date(entiTy.getBirthday().getTime());
        }
        jdbc.update(INSERT_SQL,
                entiTy.getId(),
                entiTy.getPassword(),
                entiTy.getFullname(),
                sqlDate,
                entiTy.getGender(),
                entiTy.getMobile(),  // sửa từ getMoble() thành getMobile()
                entiTy.getEmail(),
                entiTy.getRole()
        );
    }

    @Override
    public void update(Users entiTy) {
        java.sql.Date sqlDate = null;
        if (entiTy.getBirthday() != null) {
            sqlDate = new java.sql.Date(entiTy.getBirthday().getTime());
        }
        jdbc.update(UPDATE_SQL,
                entiTy.getPassword(),
                entiTy.getFullname(),
                sqlDate,
                entiTy.getGender(),
                entiTy.getMobile(),
                entiTy.getEmail(),
                entiTy.getRole(),
                entiTy.getId()  // id ở cuối, khớp với câu SQL
        );
    }

    @Override
    public void delete(String id) {
        jdbc.update(DELETE_SQL, id);
    }

    @Override
    public List<Users> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Users selectByID(String id) {
        List<Users> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Users> selectBySql(String sql, Object... args) {
        List<Users> list = new ArrayList<>();
        try {
            ResultSet rs = jdbc.query(sql, args);
            while (rs.next()) {
                Users entity = new Users();
                entity.setId(rs.getString("Id"));
                entity.setPassword(rs.getString("Password"));
                entity.setFullname(rs.getString("FullName"));
                entity.setBirthday(rs.getDate("Birthday"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setMobile(rs.getString("Mobile"));
                entity.setEmail(rs.getString("Email"));
                entity.setRole(rs.getBoolean("Role"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Users selectByEmail(String email) {
        List<Users> list = selectBySql(SELECT_BY_EMAIL, email);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
