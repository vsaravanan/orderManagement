package com.saravanan.ordermanagement.repository;

import com.saravanan.ordermanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.repository
 * @class CustomerRepository
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
