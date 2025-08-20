package com.saravanan.ordermanagement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.model
 * @class OrderItem
 */
@Entity
@Data
@Schema(description = "Order item entity")
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    private Long itemId;

    @Override
    public String toString() {
        return "OrderItem{" +
                "\n, itemId=" + itemId +
                "\n, productId='" + productId + '\'' +
                "\n, quantity=" + quantity +
                "\n, price=" + price +
                ( order != null ?  "\n, orderId =" + order.getOrderId() : "" ) +
                "\n }";
    }

    private String productId;

    private Integer quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    @ToString.Exclude // ⛔ Prevent recursion
    @EqualsAndHashCode.Exclude
    private Order order;

    public OrderItem(Long itemId, String productId, Integer quantity, BigDecimal price) {
        this.itemId = itemId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}

