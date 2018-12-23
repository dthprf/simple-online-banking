package com.simplebanking.sob.Service;

import com.simplebanking.sob.Model.Customer;
import com.simplebanking.sob.Repository.CustomerRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepositiory customerRepositiory;

    @Override
    public Customer createCustomer(Customer customer) {
        return this.customerRepositiory.save(customer);
    }
}
