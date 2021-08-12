
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tran_Hang
 */
abstract public class MoSysDAO<EntityType, KeyType> {

    abstract public void insert(EntityType entity);

    abstract public void update(EntityType entity);

    abstract public void delete(KeyType id);

    abstract public EntityType selectByID(KeyType id);

    abstract public List<EntityType> selectAll();

    abstract protected List<EntityType> selectBySQL(String sql, Object... args);

    abstract public EntityType readFromRS(ResultSet rs) throws SQLException;
}
