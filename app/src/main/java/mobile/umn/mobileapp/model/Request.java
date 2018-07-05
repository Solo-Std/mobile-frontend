package mobile.umn.mobileapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import mobile.umn.mobileapp.entity.NonTableDetail;
import mobile.umn.mobileapp.entity.RequestHeader;

/**
 * Created by User on 05/07/2018.
 */

@Data
@AllArgsConstructor
public class Request {
    RequestHeader requestHeader;
    List<NonTableDetail> listRequestDetail;
}
