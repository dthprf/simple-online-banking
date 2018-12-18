package com.simplebanking.sob.Repository;

import com.simplebanking.sob.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositiory extends JpaRepository<Customer, Long> {
}
