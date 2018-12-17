package com.simplebanking.sob.Service;

import com.simplebanking.sob.Model.Customer;
import com.simplebanking.sob.Repository.CustomerRepositiory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepositiory customerRepositiory;

    @Override
    public Customer createCustomer(Customer customer) {
        return this.customerRepositiory.save(customer);
    }
}
