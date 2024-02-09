package lk.ijse.gdse66.javaee_pos.dao.custom;

import lk.ijse.gdse66.javaee_pos.dao.CrudDAO;
import lk.ijse.gdse66.javaee_pos.dao.CustomerDAO;
import lk.ijse.gdse66.javaee_pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOimpl implements CustomerDAO {
    @Override
    public boolean add(Customer entity, Connection connection) throws SQLException {
        return connection.createStatement().executeUpdate
                ("INSERT INTO customer VALUES ('" + entity.getId() + "','" + entity.getName() + "','" +
                        entity.getMobile() + "','" + entity.getNic() + "','"+entity.getCity()+"'," +
                        "'"+entity.getStreet()+"')")>0;
    }

    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery("Select * from customer");

        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getString("id"),
                    resultSet.getString("name"), resultSet.getString("mobile"),
                    resultSet.getString("nic"),resultSet.getString("city"),
                    resultSet.getString("street"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean update(Customer entity, Connection connection) throws SQLException {
        System.out.println(entity.getCity());
        System.out.println(entity.getId());
        System.out.println(entity.getNic());
        System.out.println(entity.getStreet());
        return connection.createStatement().executeUpdate("UPDATE customer SET  name='"+entity.getName()+"',mobile='"+entity.getMobile()+"',nic='"+entity.getNic()+"',city='"+entity.getCity()+"',street='"+entity.getStreet()+"' WHERE id='"+entity.getId()+"'")>0;

    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return connection.createStatement().executeUpdate("DELETE FROM customer WHERE id ='"+id+"'")>0;

    }
}
