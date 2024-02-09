package lk.ijse.gdse66.javaee_pos.bo;

import lk.ijse.gdse66.javaee_pos.dto.CustomerDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerBO extends SuperBO{

    boolean addCustomer(CustomerDTO dto, Connection connection) throws SQLException;

    BasicDataSource pool(HttpServletRequest req);
}
