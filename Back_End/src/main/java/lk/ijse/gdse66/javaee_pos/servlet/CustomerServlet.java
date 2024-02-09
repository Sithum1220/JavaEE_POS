package lk.ijse.gdse66.javaee_pos.servlet;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import lk.ijse.gdse66.javaee_pos.bo.BOFactory;
import lk.ijse.gdse66.javaee_pos.bo.CustomerBO;
import lk.ijse.gdse66.javaee_pos.dto.CustomerDTO;
import lk.ijse.gdse66.javaee_pos.util.ResponseUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = customerBO.pool(req).getConnection();) {
            ArrayList<CustomerDTO> customers = customerBO.getAllCustomers(connection);
            JsonArrayBuilder allCustomers = Json.createArrayBuilder();
            for (CustomerDTO dto : customers) {
                JsonObjectBuilder builder = Json.createObjectBuilder();
                builder.add("id", dto.getId());
                builder.add("name", dto.getName());
                builder.add("mobile", dto.getMobile());
                builder.add("nic", dto.getNic());
                builder.add("city", dto.getCity());
                builder.add("street", dto.getStreet());
                allCustomers.add(builder.build());
                System.out.println(dto.getNic());
            }

            //create the response Object
//            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allCustomers.build()));
            PrintWriter writer = resp.getWriter();
            writer.print(allCustomers.build());

        } catch (SQLException e) {
            resp.setStatus(500);
//            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hi");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String mobile = req.getParameter("mobile");
        String nic = req.getParameter("nic");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        resp.setContentType("application/json");

        try (Connection connection = customerBO.pool(req).getConnection()){

            customerBO.addCustomer(new CustomerDTO(id,name,mobile,nic,city,street),connection);
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = resp.getWriter();
            writer.print(ResponseUtil.genJson("Success",200).build());

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = resp.getWriter();
            writer.print(ResponseUtil.genJson("Error",500,e).build());
//            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
