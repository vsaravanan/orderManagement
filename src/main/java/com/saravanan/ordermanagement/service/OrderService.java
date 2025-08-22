package com.saravanan.ordermanagement.service;

import com.saravanan.ordermanagement.mapper.OrderItemMapper;
import com.saravanan.ordermanagement.mapper.OrderMapper;
import com.saravanan.ordermanagement.model.Customer;
import com.saravanan.ordermanagement.model.Order;
import com.saravanan.ordermanagement.model.OrderItem;
import com.saravanan.ordermanagement.repository.CustomerRepository;
import com.saravanan.ordermanagement.repository.OrderItemRepository;
import com.saravanan.ordermanagement.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.OrderData;
import org.openapitools.model.OrderInput;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.service
 * @class OrderService
 */

@Service
@Slf4j
public class OrderService {

    private final CustomerRepository customerRepo;
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepository;
    private final CustomerService customerService;

    public OrderService(CustomerRepository customerRepo, OrderRepository orderRepo, OrderItemRepository orderItemRepository, CustomerService customerService) {
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepository = orderItemRepository;
        this.customerService = customerService;
    }
    public Order createOrder(OrderInput request) {

        Order savedOrder = saveOrder(request);
        customerService.addOrder(savedOrder);
        return savedOrder;
    }

    private Order setNew(Order order, Customer customer) {
        order.setOrderId(null);
        order.setCustomer(customer);
        order.setStatus("NEW");
        order.setCreatedAt(LocalDateTime.now());
        return order;
    }

    public Order saveOrder(OrderInput orderInput) {

        Customer customer = customerRepo.findById(orderInput.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (orderInput.getItems() == null || orderInput.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        Order order =  OrderMapper.toEntity(orderInput);
        Order newOrder = setNew(order, customer);

        Order savedOrder = orderRepo.save(newOrder);
        log.info("Order created: " + savedOrder);

        savedOrder.getItems().forEach(item -> {
            log.info("Order item: " + item);
        });

        return savedOrder;

    }

//    public OrderItem createOrderItem(OrderItem orderItem) {
//
//        return orderItemRepository.saveAndFlush(orderItem);
//    }

    public Order getOrderById(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return order;
    }



    public List<Order> getOrdersByCustomerId(Long customerId) {

//        Customer customer = customerRepo.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Order> orders = orderRepo.findByCustomerCustomerId(customerId);


//
//        List<OrderData> orderDataList = orders.stream().map(order -> {
//            OrderData orderData = OrderMapper.toData(order);
//            orderData.setCustomerId(customer.getCustomerId());
//
//            return orderData;
//        }).toList();

        return orders;

    }

    public void cancelOrder(Long orderId) {
        Order order = getOrderById(orderId);
        if (order.getStatus().equals("CANCELLED")) {
            throw new RuntimeException("Order id " + orderId + " already cancelled" );
        }
        order.setStatus("CANCELLED");
        orderRepo.saveAndFlush(order);
    }
}
