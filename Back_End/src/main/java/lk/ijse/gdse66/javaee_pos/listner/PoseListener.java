package lk.ijse.gdse66.javaee_pos.listner;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PoseListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        pool.setUrl("jdbc:mysql://localhost:3306/javaee_pos");
        pool.setUsername("root");
        pool.setPassword("Sithum0506");
        pool.setInitialSize(5);
        pool.setMaxTotal(5);
        servletContext.setAttribute("pos", pool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
