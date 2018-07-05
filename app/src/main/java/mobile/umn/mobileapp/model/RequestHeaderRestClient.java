package mobile.umn.mobileapp.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import mobile.umn.mobileapp.entity.MasterItem;

/**
 * Created by user on 5/17/2018.
 */

public class RequestHeaderRestClient {
    private String BASE_URL = "http://mobileapp-backend.herokuapp.com/api/requestheader/";
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

    public RequestHeader submit(RequestHeader request){
        try {
            HttpEntity<RequestHeader> req = new HttpEntity<RequestHeader>(request);
            ResponseEntity<RequestHeader> response = restTemplate
                    .exchange(BASE_URL, HttpMethod.POST, req, RequestHeader.class);
            return response.getBody();
        } catch (Exception e){
            return null;
        }
    }
}
