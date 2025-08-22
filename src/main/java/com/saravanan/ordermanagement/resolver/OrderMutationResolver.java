package com.saravanan.ordermanagement.resolver;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.saravanan.ordermanagement.model.Customer;
import com.saravanan.ordermanagement.model.Order;
import com.saravanan.ordermanagement.service.CustomerService;
import com.saravanan.ordermanagement.service.OrderService;
import org.openapitools.model.CustomerInput;
import org.openapitools.model.OrderInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author Sarav on 15 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.mapper
 * @class OrderMutationResolver
 */

@Controller
public class OrderMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @MutationMapping
    public Order createOrder(@Argument OrderInput input) {
        return orderService.createOrder(input);
    }

    @MutationMapping
    public Customer createCustomer(@Argument CustomerInput input) {
        return customerService.createCustomer(input);
    }
}
