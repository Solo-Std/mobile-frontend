package mobile.umn.mobileapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mobile.umn.mobileapp.entity.RequestDetail;

/**
 * Created by User on 24/05/2018.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeader {
    private int request_header_id;
    private String requested_by;
    private String request_date;
    private String number;
    private int counter;
    private String type;
    //1=dh,2=pm,3=fc,4=gm
    private String app_status1;
    private String app_by1;
    private String app_status2;
    private String app_by2;
    private String app_status3;
    private String app_by3;
    private String app_status4;
    private String app_by4;
    private int grand_total;

    ///////////Details//////
    private List<RequestDetail> details;
}
