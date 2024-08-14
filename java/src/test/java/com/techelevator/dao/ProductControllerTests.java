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
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

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
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("password");
        String url = BASE_URL+"login";
        ResponseEntity<LoginResponseDto> response = http.getForEntity(url, LoginResponseDto.class);
        header = response.getHeaders();
        LoginResponseDto loginResponseDto = response.getBody();
        System.out.println(loginResponseDto);

    }
    @Test
    public void testLoginEndpoint() {
        // Create a request object
        LoginDto loginRequest = new LoginDto();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("password");
        String url = BASE_URL + "/login";

        // Send a POST request to the login endpoint
        LoginResponseDto response = testHttp.postForObject(url, loginRequest, LoginResponseDto.class);
        System.out.println(response.getUser());


    }
}

