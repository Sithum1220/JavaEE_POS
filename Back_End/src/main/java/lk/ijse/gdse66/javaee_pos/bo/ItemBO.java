package lk.ijse.gdse66.javaee_pos.bo;

import lk.ijse.gdse66.javaee_pos.dao.DAOFactory;
import lk.ijse.gdse66.javaee_pos.dao.ItemDAO;
import lk.ijse.gdse66.javaee_pos.dto.CustomerDTO;
import lk.ijse.gdse66.javaee_pos.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO{
    public boolean addItem(ItemDTO dto, Connection connection) throws SQLException;

    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException;

}
