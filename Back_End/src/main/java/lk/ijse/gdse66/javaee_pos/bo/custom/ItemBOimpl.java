package lk.ijse.gdse66.javaee_pos.bo.custom;

import lk.ijse.gdse66.javaee_pos.bo.ItemBO;
import lk.ijse.gdse66.javaee_pos.dao.DAOFactory;
import lk.ijse.gdse66.javaee_pos.dao.ItemDAO;
import lk.ijse.gdse66.javaee_pos.dto.ItemDTO;
import lk.ijse.gdse66.javaee_pos.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOimpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getBoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItem(ItemDTO dto, Connection connection) throws SQLException {
        return itemDAO.add(new Item(dto.getId(), dto.getItem_category(), dto.getUnit_price(), dto.getQty(),
                dto.getItem_description()),connection);

    }

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException {
        ArrayList<ItemDTO> allItems= new ArrayList<>();

        ArrayList<Item> allEntity = itemDAO.getAll(connection);
        for (Item i : allEntity) {
            allItems.add(new ItemDTO(i.getId(),i.getItem_category(),i.getUnit_price(),i.getQty(),i.getItem_description()));
        }
        return allItems;
    }
}
