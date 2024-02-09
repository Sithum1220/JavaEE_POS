package lk.ijse.gdse66.javaee_pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String id;
    private String item_category;
    private String unit_price;
    private String qty;
    private String item_description;

}
