package lk.ijse.gdse66.javaee_pos.util;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class PoolUtil {
    public static BasicDataSource pool(HttpServletRequest req){
        ServletContext servletContext = req.getServletContext();

        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("pos");

        return pool;
    }
}
