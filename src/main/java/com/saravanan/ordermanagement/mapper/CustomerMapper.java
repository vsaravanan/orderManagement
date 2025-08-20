package com.saravanan.ordermanagement.mapper;

import com.saravanan.ordermanagement.model.Customer;
import org.openapitools.model.CustomerData;
import org.openapitools.model.CustomerInput;
import org.springframework.beans.BeanUtils;

/**
 * @author Sarav on 14 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.mapper
 * @class CustomerMapper
 */
public class CustomerMapper {


//    public static Customer toEntity(CustomerData customerData) {
//        Customer customer = new Customer();
//        BeanUtils.copyProperties(customerData, customer);
//        return customer;
//    }

    public static Customer toEntity(CustomerInput customerInput) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerInput, customer);
        return customer;
    }

    public static CustomerData toData(Customer customer) {
        CustomerData customerData = new CustomerData();
        BeanUtils.copyProperties(customer, customerData);
        return customerData;
    }
}
