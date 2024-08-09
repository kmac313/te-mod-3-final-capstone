package com.techelevator.controller;

import com.techelevator.dao.CustomerDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import com.techelevator.model.Invoice;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CustomerController {
    private CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    //TODO Get Customer Method

    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerDao.getCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    //TODO Get Customer By Id Method

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomersById(@PathVariable int id){
        return new ResponseEntity<Customer>(customerDao.getCustomerById(id), HttpStatus.OK);
    }

    //TODO Get Customer By Email Method

    @RequestMapping(path = "/customer/{email}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomersByEmail(@RequestBody String email){
        return new ResponseEntity<Customer>(customerDao.getCustomerByEmail(email), HttpStatus.OK);
    }

    //TODO Create Customer Method

    @RequestMapping(path = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Map<String, Object> newCustomer) {

        /* Object Structure for the customer
         {
            "firstName": "John",
            "lastName": "Doe",
            "streetAddress": "123 Main Street",
            "city": "Ohio",
            "zipcode": 12345,
            "stateAbbreviation": "OH",
            "phoneNumber": "1234567890",
            "email": "email@email.com",
            "username": "user"
        }
        */



        String firstName = (String)newCustomer.get("firstName");
        String lastName = (String)newCustomer.get("lastName");
        String streetAddress = (String)newCustomer.get("streetAddress");
        String City = (String)newCustomer.get("city");
        int zipcode = (int)newCustomer.get("zipcode");
        try {
            if (zipcode < 10000 || zipcode >= 100000) {
                zipcode = (int) newCustomer.get("zipcode");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zip code must be 5 digits");
            }
        } catch (NumberFormatException nfe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-Numeric Zip Code");
        }
        String stateAbbreviation = (String)newCustomer.get("stateAbbreviation");
        String phoneNumber = (String)newCustomer.get("phoneNumber");
        for (int p = 0 ; p <phoneNumber.length() ; p++){
            try {
                Integer.parseInt(phoneNumber.substring(p, p + 1));
                if (phoneNumber.length() != 10) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number must be 10 digits");
                }
            } catch (NumberFormatException nfe) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-Numeric Phone Number");
            }
        }
        String email = (String)newCustomer.get("email");
        String username = (String)newCustomer.get("username");

        Customer createdCustomer;


        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setStreetAddress(streetAddress);
        customer.setCity(City);
        customer.setZipcode(zipcode);
        customer.setStateAbbreviation(stateAbbreviation);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setUsername(username);
        createdCustomer = customerDao.createCustomer(customer);




        return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
    }

    //TODO Update Customer Method

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<Customer>updateCustomer (@RequestBody Customer customer,  @PathVariable int customerId) {
        customer.setCustomerId(customerId);
        Customer updatedCustomer = null;
        try {
            updatedCustomer = customerDao.updateCustomer(customer);
        } catch(DaoException doe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
    }

    //TODO Delete Customer By Id Method

    @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int id) {
        customerDao.deleteCustomerById(id);
    }

    //TODO Delete Customer By Username Method

    @RequestMapping(path = "/customer/", method = RequestMethod.DELETE)
    public void deleteCustomerByUsername(@RequestBody Map<String, Object> deletionDTO) {

        String username = (String)deletionDTO.get("username");
        Boolean confirm = (Boolean)deletionDTO.get("confirm");

        if(confirm == true) {
            customerDao.deleteCustomerByUsername(username);
        }
    }


//TODO Change cathc statemtns to response status

//TODO MapBox



}
