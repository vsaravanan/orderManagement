package com.saravanan.ordermanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Override
    public String toString() {

        return "Order{" +
                "\n, orderId=" + orderId +
                ( customer != null ?  "\n, customer =" + customer.getCustomerId() : "" ) +
                "\n, totalAmount=" + totalAmount +
                "\n, status='" + status + '\'' +
                "\n, createdAt=" + createdAt +
                "\n }";
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    public Long getCustomerId() {
        return customer != null ? customer.getCustomerId() : null;
    }

    private BigDecimal totalAmount;
    private String status;


    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<OrderItem> items = new ArrayList<>();


    public void setItems(List<OrderItem> items) {
        this.items = items;
        recalculateTotalAmount();
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);

        if (this.items != null && this.items.size() > 0 && this.items.contains(item)) {
            this.items.removeIf(existing -> Objects.equals(existing, item));
        }

        this.items.add(item);
        recalculateTotalAmount();
    }

    public void recalculateTotalAmount() {
        totalAmount = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
