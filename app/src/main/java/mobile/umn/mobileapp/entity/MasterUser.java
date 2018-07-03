package mobile.umn.mobileapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by user on 5/17/2018.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MasterUser {
    private int user_id;
    private String username;
    private String password;
    private String fullname;
    private String position;
}
