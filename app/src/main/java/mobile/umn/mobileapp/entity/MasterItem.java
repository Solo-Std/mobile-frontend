package mobile.umn.mobileapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by user on 5/17/2018.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterItem {
    private int item_id;
    private String item_code;
    private String item_name;
    private String uom;
    private String item_type;
    private int item_price;
}