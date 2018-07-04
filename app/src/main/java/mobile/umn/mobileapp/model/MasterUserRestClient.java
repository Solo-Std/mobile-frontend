package mobile.umn.mobileapp.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import mobile.umn.mobileapp.entity.MasterUser;

/**
 * Created by user on 5/17/2018.
 */

public class MasterUserRestClient {
    private String BASE_URL = "http://mobileapp-backend.herokuapp.com/api/masteruser";
    private RestTemplate restTemplate = new RestTemplate();

    public List<MasterUser> find(String username, String password) {
        try {
            return restTemplate.exchange(BASE_URL + "/" + username + "/" + password,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<MasterUser>>() {
                    }).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public List<MasterUser> findAll() {
        try {
            return restTemplate.exchange(BASE_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<MasterUser>>() {
                    }).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
