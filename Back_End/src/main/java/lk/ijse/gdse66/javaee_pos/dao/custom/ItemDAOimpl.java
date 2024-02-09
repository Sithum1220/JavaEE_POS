package lk.ijse.gdse66.javaee_pos.dao.custom;

import lk.ijse.gdse66.javaee_pos.dao.ItemDAO;
import lk.ijse.gdse66.javaee_pos.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOimpl implements ItemDAO {
    @Override
    public boolean add(Item entity, Connection connection) throws SQLException {
        return connection.createStatement().executeUpdate("INSERT INTO item VALUES ('" + entity.getId() + "','" +
                entity.getItem_category() + "','" + entity.getUnit_price() + "','" + entity.getQty() + "','"
                +entity.getItem_description()+"')") > 0;
    }

    @Override
    public ArrayList<Item> getAll(Connection connection) throws SQLException {
        ArrayList<Item> allItems = new ArrayList();
        ResultSet rst = connection.createStatement().executeQuery("SELECT * FROM item");
        while (rst.next()) {
            Item item = new Item(rst.getString("id"), rst.getString("item_category"), rst.getString("unit_price"), rst.getString("qty"),rst.getString("item_description"));
            allItems.add(item);
        }
        return allItems;
    }

    @Override
    public boolean update(Item entity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return false;
    }
}
