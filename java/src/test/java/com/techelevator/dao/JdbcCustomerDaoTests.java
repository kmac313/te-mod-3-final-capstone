package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
public class JdbcCustomerDaoTests extends BaseDaoTests {

    private JdbcCustomerDao sut;

    private UserDao userDao;


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCustomerDao(jdbcTemplate, userDao);
    }
}
