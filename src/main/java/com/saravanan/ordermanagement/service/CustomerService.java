package com.saravanan.ordermanagement.service;

import com.saravanan.ordermanagement.mapper.CustomerMapper;
import com.saravanan.ordermanagement.model.Customer;
import com.saravanan.ordermanagement.model.Order;
import com.saravanan.ordermanagement.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.CustomerData;
import org.openapitools.model.CustomerInput;
import org.springframework.stereotype.Service;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.service
 * @class CustomerService
 */

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepo;
    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer createCustomer(CustomerInput customerInput) {
        Customer customer = CustomerMapper.toEntity(customerInput);
        Customer savedCustomer = customerRepo.save(customer);
        log.info("Created customer {}", savedCustomer);
        return savedCustomer;
    }

    public Customer addOrder(Order order) {
        Customer customer = customerRepo.findById(order.getCustomer().getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.getOrders().add(order);
        Customer savedCustomer = customerRepo.save(customer);
        log.info("Added order {} to Customer {} ", order, savedCustomer);
        return savedCustomer;
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepo.findById(customerId).orElseThrow( () -> new RuntimeException("Customer not found"));
    }
}
