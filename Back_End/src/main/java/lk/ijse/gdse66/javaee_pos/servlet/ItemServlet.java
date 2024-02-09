package lk.ijse.gdse66.javaee_pos.servlet;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import lk.ijse.gdse66.javaee_pos.bo.BOFactory;
import lk.ijse.gdse66.javaee_pos.bo.ItemBO;
import lk.ijse.gdse66.javaee_pos.dto.CustomerDTO;
import lk.ijse.gdse66.javaee_pos.dto.ItemDTO;
import lk.ijse.gdse66.javaee_pos.util.PoolUtil;
import lk.ijse.gdse66.javaee_pos.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = PoolUtil.pool(req).getConnection();) {
            ArrayList<ItemDTO> customers = itemBO.getAllItems(connection);
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (ItemDTO dto : customers) {
                JsonObjectBuilder builder = Json.createObjectBuilder();
                builder.add("id", dto.getId());
                builder.add("category", dto.getItem_category());
                builder.add("unitPrice", dto.getUnit_price());
                builder.add("qty", dto.getQty());
                builder.add("description", dto.getItem_description());
                jsonArrayBuilder.add(builder.build());
            }

            //create the response Object
//            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allCustomers.build()));
            ResponseUtil.genJson("Success", 200, resp, jsonArrayBuilder);

        } catch (SQLException e) {
            resp.setStatus(500);
//            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String category = req.getParameter("category");
        String unitPrice = req.getParameter("unitPrice");
        String qty = req.getParameter("qty");
        String description = req.getParameter("description");
        resp.setContentType("application/json");

        try (Connection connection = PoolUtil.pool(req).getConnection()) {

            itemBO.addItem(new ItemDTO(id, category, unitPrice, qty, description), connection);
//            PrintWriter writer = resp.getWriter();
//            writer.print(ResponseUtil.genJson("Success",200).build();
            ResponseUtil.genJson("Success", 200, resp);
        } catch (SQLException e) {
//            PrintWriter writer = resp.getWriter();
//            writer.print(ResponseUtil.genJson("Error", 500, e).build());
            ResponseUtil.genJson("Success", 200, e, resp);

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
