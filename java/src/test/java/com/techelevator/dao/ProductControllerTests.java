package com.techelevator.dao;

import com.techelevator.Application;
import com.techelevator.controller.ProductController;
import com.techelevator.model.LoginDto;
import com.techelevator.model.LoginResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ProductControllerTests extends BaseDaoTests{
    private ProductController pc;
    private ProductDao dao;
    private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate namedJdbc;
    private RestTemplate http;
    private TestRestTemplate testHttp;
    private HttpHeaders header;
    private final String BASE_URL = "http://localhost:9000";

    @Before
    public void setup(){

        jdbc = new JdbcTemplate(dataSource);
        namedJdbc = new NamedParameterJdbcTemplate(dataSource);
        dao = new JdbcProductDao(jdbc, namedJdbc);
        pc = new ProductController(dao);
        http = new RestTemplate();
        testHttp = new TestRestTemplate();
    }
    public void loginAdmin(){
        // Create a request object
        LoginDto loginRequest = new LoginDto();
        loginRequest.setUsername("admin");
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
    public void t01_API_returns_all_products_with_no_categories() {
        loginAdmin();
        String url = BASE_URL + "/menu";

        Map<String, List<String>> requestBody = new HashMap<>();
        List<String> categories = new ArrayList<>();
        requestBody.put("categories", categories);
        HttpEntity entity = new HttpEntity( requestBody, header );
        ResponseEntity response = http.postForEntity(url, entity, Map.class);

        Map products = (Map)response.getBody();
        System.out.println(products.keySet());
        Assert.assertNotNull(products);
        Assert.assertTrue(products.keySet().size() == 10 );


    }
    @Test
    public void t02_API_returns_all_products_with_some_categories() {
        loginAdmin();
        String url = BASE_URL + "/menu";

        Map<String, List<String>> requestBody = new HashMap<>();
        List<String> categories = new ArrayList<>();
        categories.add("Drink");
        categories.add("Salad");
        categories.add("Dessert");

        requestBody.put("categories", categories);
        HttpEntity entity = new HttpEntity( requestBody, header );
        ResponseEntity response = http.postForEntity(url, entity, Map.class);

        Map products = (Map)response.getBody();
        System.out.println(products.keySet());
        Assert.assertNotNull(products);
        Assert.assertTrue(products.keySet().size() == 3 );
        Assert.assertTrue(products.keySet().containsAll(categories));


    }

    @Test(expected = HttpClientErrorException.class)
    public void t03_API_throws_exception_when_body_for_getProducts_invalid(){
        loginAdmin();
        String url = BASE_URL + "/menu";

        Map<String, List<String>> requestBody = new HashMap<>();
        List<String> categories = new ArrayList<>();
        requestBody.put("categories", categories);
        HttpEntity entity = new HttpEntity(header );
        ResponseEntity response = http.postForEntity(url, entity, Map.class);

        Map products = (Map)response.getBody();
        System.out.println(products.keySet());
        Assert.assertNull(products);

    }

}

