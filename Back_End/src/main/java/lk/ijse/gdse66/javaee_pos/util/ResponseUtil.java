package lk.ijse.gdse66.javaee_pos.util;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {
    public static JsonObjectBuilder genJson(String msg, int status) {

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("status", status);
        jsonObjectBuilder.add("message", msg);

        return jsonObjectBuilder;

    }

    public static JsonObjectBuilder genJson(String msg, int status, Exception e) {

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("status", status);
        jsonObjectBuilder.add("message", msg);

        jsonObjectBuilder.add("data", e.getLocalizedMessage());
        return jsonObjectBuilder;

//        try {
//            PrintWriter writer = resp.getWriter();
//            writer.print(jsonObjectBuilder.build());
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }

    }


}
