package lk.ijse.gdse66.javaee_pos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{

     boolean add(T entity, Connection connection) throws SQLException;
     ArrayList<T> getAll(Connection connection) throws SQLException;
}
