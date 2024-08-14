package com.techelevator.dao;

import com.techelevator.controller.InvoiceController;
import com.techelevator.model.LoginDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class InvoiceControllerTests {
    private RestTemplate http;
    private HttpHeaders header;
    private final String BASE_URL = "http://localhost:9000";
    @Before
    public void setup(){
        http = new RestTemplate();
    }

    public void login(String username){
        // Create a request object
        LoginDto loginRequest = new LoginDto();
        loginRequest.setUsername(username);
        loginRequest.setPassword("password");
        String url = BASE_URL + "/login";

        // Send a POST request to the login endpoint
        Map response = http.postForObject(url, loginRequest, Map.class);
        String token = (String) response.get("token");
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(token);
    }

    @Test
    public void t01_API_returns_all_invoices_for_admins_with_default_params(){
        login("admin");

        String url = BASE_URL + "/invoice";
        HttpEntity entity = new HttpEntity(header);
        ResponseEntity<List> response = http.exchange(url, HttpMethod.GET, entity, List.class);
        List invoices = response.getBody();
        for(Object object: invoices){
            System.out.println(object);

        }
        Assert.assertTrue(invoices.size() > 0);
    }
}
