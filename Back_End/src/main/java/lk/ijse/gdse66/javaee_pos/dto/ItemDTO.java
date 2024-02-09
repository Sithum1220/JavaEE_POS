package lk.ijse.gdse66.javaee_pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private String id;
    private String item_category;
    private String unit_price;
    private String qty;
    private String item_description;

}
