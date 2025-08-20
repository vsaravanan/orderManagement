package com.saravanan.ordermanagement.controller;


import com.saravanan.ordermanagement.mapper.OrderMapper;
import com.saravanan.ordermanagement.model.Order;
import com.saravanan.ordermanagement.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.OrderApi;
import org.openapitools.model.OrderData;
import org.openapitools.model.OrderInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.controller
 * @class OrderApiController
 */
@RestController
@Slf4j
public class OrderApiController implements OrderApi {

    @Autowired
    private OrderService orderService;

    public ResponseEntity<OrderData> createOrder(OrderInput orderInput) {
        log.info("Received order request: {}", orderInput);

        if (orderInput.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item");
        }

        Order processedOrder = orderService.createOrder(orderInput);

        OrderData orderData = OrderMapper.toData(processedOrder);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("X-Custom-Header", "value")
                .body(orderData);
    }



    public ResponseEntity<OrderData> getOrderById(Long orderId) {
        Order order = orderService.getOrderById(orderId);
        OrderData orderData = OrderMapper.toData(order);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("X-Custom-Header", "value")
                .body(orderData);
    }



    public ResponseEntity<String> cancelOrder(Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .header("X-Custom-Header", "value")
                .body("deleted order id " + orderId);
    }

    public ResponseEntity<List<OrderData>> listOrdersByCustomer(Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);

        List<OrderData> orderDataList = orders.stream().map(OrderMapper::toData).toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("X-Custom-Header", "value")
                .body(orderDataList);
    }

}
