package mobile.umn.mobileapp.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import mobile.umn.mobileapp.entity.MasterItem;

/**
 * Created by user on 5/17/2018.
 */

public class MasterItemRestClient {
    private String BASE_URL = "http://mobileapp-backend.herokuapp.com/api/masteritem/search";
//    private String BASE_URL = "http://mobileapp-backend.herokuapp.com/api/requestheader/all";
    private RestTemplate restTemplate = new RestTemplate();

//    public MasterItem find(int item_id){
//        try {
//            return restTemplate.getForObject(BASE_URL + "/" + item_id, MasterItem.class);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }

    public List<MasterItem> findAllByName(String item_name){
        try {
            return restTemplate.exchange(BASE_URL + "/" + item_name.toUpperCase(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<MasterItem>>() {
                    }).getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<List<Object>> findAll(){
        try {
            return restTemplate.exchange(BASE_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<List<Object>>>() {
                    }).getBody();
        } catch (Exception e){
            return null;
        }
    }
}
