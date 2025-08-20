package com.saravanan.ordermanagement.controller;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.controller
 * @class CustomerApiController
 */


import com.saravanan.ordermanagement.mapper.CustomerMapper;
import com.saravanan.ordermanagement.model.Customer;
import com.saravanan.ordermanagement.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.CustomerApi;
import org.openapitools.model.CustomerData;
import org.openapitools.model.CustomerInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class CustomerApiController implements CustomerApi {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ResponseEntity<CustomerData> createCustomer(@Valid @RequestBody CustomerInput customerInput) {
        log.info("Creating customer: {}", customerInput);

        Customer customer = customerService.createCustomer(customerInput);
        CustomerData customerData1 = CustomerMapper.toData(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerData1);
    }
}
