package lk.ijse.gdse66.javaee_pos.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO{

     boolean add(T entity, Connection connection) throws SQLException;
}
