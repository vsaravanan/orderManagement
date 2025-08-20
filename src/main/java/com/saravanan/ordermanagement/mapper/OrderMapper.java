package com.saravanan.ordermanagement.mapper;

import com.saravanan.ordermanagement.model.Order;
import com.saravanan.ordermanagement.model.OrderItem;
import org.openapitools.model.OrderData;
import org.openapitools.model.OrderInput;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sarav on 15 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.mapper
 * @class OrderMapper
 */
public class OrderMapper {

//    public static Order toEntity(OrderData orderData) {
//        Order order = new Order();
//        BeanUtils.copyProperties(orderData, order);
//        return order;
//    }

    public static Order toEntity(OrderInput orderInput) {
        Order order = new Order();
        BeanUtils.copyProperties(orderInput, order);

        List<OrderItem> orderItems = orderInput.getItems().stream()
                .map(input -> {
                    OrderItem item = OrderItemMapper.toEntity(input);
                    item.setOrder(order);
//                    item.setItemId(null);
                    return item;
                })
                .collect(Collectors.toList());
        order.setItems(orderItems);

        return order;
    }

    public static OrderData toData(Order order) {
        OrderData orderData = new OrderData();
        BeanUtils.copyProperties(order, orderData);
        orderData.setCustomerId(order.getCustomer().getCustomerId());

        orderData.setItems (order.getItems().stream()
                .map(OrderItemMapper::toData)
                .collect(Collectors.toList()));

        return orderData;
    }
}