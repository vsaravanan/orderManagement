package com.saravanan.ordermanagement.mapper;

import com.saravanan.ordermanagement.model.OrderItem;
import jakarta.validation.Valid;
import org.openapitools.model.OrderItemData;
import org.openapitools.model.OrderItemInput;
import org.springframework.beans.BeanUtils;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.mapper
 * @class OrderItemMapper
 */
public class OrderItemMapper {

    public static OrderItem toEntity(@Valid OrderItemInput orderItemInput) {
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(orderItemInput, orderItem);
        return orderItem;
    }

    public static OrderItemData toData(OrderItem orderItem) {
        OrderItemData OrderItemData = new OrderItemData();
        BeanUtils.copyProperties(orderItem, OrderItemData);
        return OrderItemData;
    }


}
