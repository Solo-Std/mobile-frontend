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
public class RequestDetail {
    private int detail_id;
    private MasterItem item;
    private int total_price;
    private int item_qty;
}
