package mobile.umn.mobileapp.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.entity.NonTableDetail;
import mobile.umn.mobileapp.entity.RequestDetail;
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

    public Integer submit(RequestHeader request){
        try {
            HttpEntity<RequestHeader> req = new HttpEntity<RequestHeader>(request);
            ResponseEntity<Integer> response = restTemplate
                    .exchange(BASE_URL + "/header", HttpMethod.POST, req, Integer.class);
            return response.getBody();
        } catch (Exception e){
            return null;
        }
    }

    public void submit(List<NonTableDetail> request){
        try {
            for(NonTableDetail n:request){
                HttpEntity<NonTableDetail> req = new HttpEntity<NonTableDetail>(n);
                restTemplate
                        .exchange("http://mobileapp-backend.herokuapp.com/api/requestdetail/detail",
                                HttpMethod.POST,
                                req,
                                new ParameterizedTypeReference<ResponseEntity<NonTableDetail>>() {
                                });
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void approve(Long request_id, String division, boolean approve){
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
