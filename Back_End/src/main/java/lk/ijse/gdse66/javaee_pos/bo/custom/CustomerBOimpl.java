package lk.ijse.gdse66.javaee_pos.bo.custom;

import lk.ijse.gdse66.javaee_pos.bo.CustomerBO;
import lk.ijse.gdse66.javaee_pos.dao.CustomerDAO;
import lk.ijse.gdse66.javaee_pos.dao.DAOFactory;
import lk.ijse.gdse66.javaee_pos.dao.SuperDAO;
import lk.ijse.gdse66.javaee_pos.dto.CustomerDTO;
import lk.ijse.gdse66.javaee_pos.entity.Customer;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomerBOimpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getBoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDTO dto, Connection connection) throws SQLException {

        return customerDAO.add(new Customer(dto.getId(), dto.getName(), dto.getMobile(), dto.getNic(), dto.getCity(),dto.getStreet()),connection);
    }

    @Override
    public BasicDataSource pool(HttpServletRequest req) {
        ServletContext servletContext = req.getServletContext();

        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("pos");

        return pool;
    }
}
