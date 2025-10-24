package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.NewSletters;
import utils.jdbc;

public class NewSlettersDAO extends daos<NewSletters, String>{

	String INSERT_SQL =" INSERT INTO NEWSLETTERS(Email, Enabled) values (?,?)";
	String UPDATE_SQL = "UPDATE NEWSLETTERS SET Enabled= ? WHERE Email = ?";
	String DELETE_SQL = "DELETE  FROM NEWSLETTERS WHERE Email = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NEWSLETTERS";
    String SELECT_BY_ID_SQL = "SELECT * FROM NEWSLETTERS WHERE Email=?";
    String SELECT_BY_MA_CD_SQL = "SELECT * FROM NEWSLETTERS WHERE Enabled=?";
	
	@Override
	public void insert(NewSletters entiTy) {
		// TODO Auto-generated method stub
		jdbc.update(INSERT_SQL,
				entiTy.getEmail(),
				entiTy.getEnabled()
				);
		
	}

	@Override
	public void update(NewSletters entiTy) {
		// TODO Auto-generated method stub
		jdbc.update(UPDATE_SQL, 
				entiTy.getEmail(),
				entiTy.getEnabled()
				);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		jdbc.update(DELETE_SQL, id);
		
	}

	@Override
	public List<NewSletters> selectAll() {
		// TODO Auto-generated method stub
		return this.selectBySql(SELECT_ALL_SQL);
	}

	@Override
	public NewSletters selectByID(String id) {
		List<NewSletters> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
	}

	@Override
	public List<NewSletters> selectBySql(String sql, Object... args) {

		List<NewSletters> list = new  ArrayList<>();
		try {
			ResultSet rs =jdbc.query(sql, args);
			while(rs.next()) {
				NewSletters entity = new NewSletters();
				entity.setEmail(rs.getString("Email"));
				entity.setEnabled(rs.getBoolean("Enabled"));
				list.add(entity);
			}
			rs.getStatement().getConnection().close();
            return list;
		}catch (Exception e) {
            throw new RuntimeException(e);
		}
		
	}

}
