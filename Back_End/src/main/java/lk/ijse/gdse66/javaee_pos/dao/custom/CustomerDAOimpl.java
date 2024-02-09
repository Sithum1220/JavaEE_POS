package lk.ijse.gdse66.javaee_pos.dao.custom;

import lk.ijse.gdse66.javaee_pos.dao.CrudDAO;
import lk.ijse.gdse66.javaee_pos.dao.CustomerDAO;
import lk.ijse.gdse66.javaee_pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAOimpl implements CustomerDAO {
    @Override
    public boolean add(Customer entity, Connection connection) throws SQLException {
        return connection.createStatement().executeUpdate
                ("INSERT INTO customer VALUES ('" + entity.getId() + "','" + entity.getName() + "','" +
                        entity.getMobile() + "','" + entity.getNic() + "','"+entity.getCity()+"'," +
                        "'"+entity.getStreet()+"')")>0;
    }
}
