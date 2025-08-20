package com.saravanan.ordermanagement.resolver;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.saravanan.ordermanagement.service.CustomerService;
import com.saravanan.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Sarav on 15 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.mapper
 * @class OrderMutationResolver
 */

@Component
public class OrderMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

//    public Order createOrder(OrderInput input) {
//        return orderService.createOrderFromGraphQL(input);
//    }
//
//    public Customer createCustomer(CustomerInput input) {
//        return customerService.createCustomerFromGraphQL(input);
//    }
}
