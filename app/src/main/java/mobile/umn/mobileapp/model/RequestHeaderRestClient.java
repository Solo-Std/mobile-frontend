package mobile.umn.mobileapp.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.entity.RequestHeader;

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

    public Request submit(Request request){
        try {
            HttpEntity<Request> req = new HttpEntity<Request>(request);
            ResponseEntity<Request> response = restTemplate
                    .exchange(BASE_URL, HttpMethod.POST, req, Request.class);
            return response.getBody();
        } catch (Exception e){
            return null;
        }
    }

    public void approve(Long request_id, String division ,boolean approve){
        try {
            String acc = approve?"ACCEPTED":"REJECTED";
            restTemplate.exchange(
                    BASE_URL + "/" + request_id + "/" + division + "/" + acc,
                    HttpMethod.PUT,
                    null,
                    new ParameterizedTypeReference<List<MasterItem>>() {
                    }).getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
