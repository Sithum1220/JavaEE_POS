package lk.ijse.gdse66.javaee_pos.dao;


import lk.ijse.gdse66.javaee_pos.dao.custom.CustomerDAOimpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getBoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    //Object creation logic for BO objects
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOimpl();
//            case ITEM:
//                return new ItemDAOimpl();
//            case PLACEORDER:
//                return new PurchaseOrederBOimpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, PLACEORDER
    }
}
