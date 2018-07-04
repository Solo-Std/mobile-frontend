package mobile.umn.mobileapp.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import entity.MasterCard;
import mobile.umn.mobileapp.entity.MasterItem;

/**
 * Created by Heri on 7/3/2018.
 */

public class MasterCardRestClient {
    private String BASE_URL = "http://mobileapp-backend.herokuapp.com/api/masteritem";
    private RestTemplate restTemplate = new RestTemplate();

    public MasterCard find(int item_id){
        try {
            return restTemplate.getForObject(BASE_URL + "/" + item_id, MasterCard.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<MasterCard> findAll(){
        try {
            return restTemplate.exchange(BASE_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<MasterCard>>() {
                    }).getBody();
        } catch (Exception e){
            return null;
        }
    }
}
