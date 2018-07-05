package mobile.umn.mobileapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by User on 05/07/2018.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NonTableDetail {
    private int item_id;
    private int qty;
    private int request_header_id;
    private int request_detail_id;
}
