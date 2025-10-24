package DAO;

import java.util.List;

public abstract class daos<EntityType, KeyType> {
	public abstract void insert(EntityType entiTy);
    public abstract void update(EntityType entiTy);
    public abstract void delete(KeyType id);
    public abstract List<EntityType> selectAll();
    public abstract EntityType selectByID(KeyType id);
    public abstract List<EntityType> selectBySql(String sql, Object...args);
}
