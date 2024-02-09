package lk.ijse.gdse66.javaee_pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {

    private String id;
    private String name;
    private String mobile;
    private String nic;
    private String city;
    private String street;
}
