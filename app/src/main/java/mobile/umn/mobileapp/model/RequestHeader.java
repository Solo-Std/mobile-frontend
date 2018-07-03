package mobile.umn.mobileapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by User on 24/05/2018.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeader {
    public String request_header_id;
    public String type;
    public String name;
    public String date;
    public String total;
}
