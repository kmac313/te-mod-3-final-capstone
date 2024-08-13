package com.techelevator.dao;

import com.techelevator.Application;
import com.techelevator.controller.ProductController;
import com.techelevator.model.LoginDto;
import com.techelevator.model.LoginResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.client.RestTemplate;

public class ProductControllerTests extends BaseDaoTests{
    private ProductController pc;
    private ProductDao dao;
    private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate namedJdbc;
    private RestTemplate http;
    private HttpHeaders header;
    private final String BASE_URL = "http://localhost:9000";

    @Before
    public void setup(){

        jdbc = new JdbcTemplate(dataSource);
        namedJdbc = new NamedParameterJdbcTemplate(dataSource);
        dao = new JdbcProductDao(jdbc, namedJdbc);
        pc = new ProductController(dao);
        http = new RestTemplate();


    }

    public void loginAdmin(){
        Application.main(new String[0]);
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
    public void t01_(){
        loginAdmin();
    }
}
