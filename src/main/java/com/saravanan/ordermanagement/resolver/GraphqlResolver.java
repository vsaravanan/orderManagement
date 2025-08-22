package com.saravanan.ordermanagement.resolver;


import com.saravanan.ordermanagement.model.Customer;
import com.saravanan.ordermanagement.model.Order;
import com.saravanan.ordermanagement.service.CustomerService;
import com.saravanan.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Sarav on 15 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.mapper
 * @class OrderQueryResolver
 */
@Controller
public class GraphqlResolver {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @QueryMapping
    public Order getOrder(@Argument Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @QueryMapping
    public Customer getCustomer(@Argument Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @QueryMapping
    public List<Order> getOrdersByCustomer(@Argument Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

//    public Customer getCustomer(Long customerId) {
//        return customerService.getCustomerById(customerId);
//    }
}
