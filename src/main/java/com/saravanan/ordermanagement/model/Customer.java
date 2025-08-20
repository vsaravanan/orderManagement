package com.saravanan.ordermanagement.model;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.model
 * @class Customer
 */

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Override
    public String toString() {
        return "Customer{" +
                "\n, customerId=" + customerId +
                "\n, name='" + name + '\'' +
                "\n, email='" + email + '\'' +
//                ", orders=" + orders +
                "\n }";
    }

    private String name;
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @ToString.Exclude // ⛔ Prevent recursion
    @EqualsAndHashCode.Exclude
    private List<Order> orders;

}
