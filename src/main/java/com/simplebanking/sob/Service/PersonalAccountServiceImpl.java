package com.simplebanking.sob.Service;

import com.simplebanking.sob.Exception.NotFoundException;
import com.simplebanking.sob.Model.PersonalAccount;
import com.simplebanking.sob.Repository.CustomerRepositiory;
import com.simplebanking.sob.Repository.PersonalAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class PersonalAccountServiceImpl implements PersonalAccountService {
    @Autowired
    CustomerRepositiory customerRepositiory;

    @Autowired
    PersonalAccountRepository personalAccountRepository;

    @Override
    public PersonalAccount createPersonalAccount(Long customerId, PersonalAccount personalAccount) {
        return customerRepositiory.findById(customerId)
                .map(customer -> {
                    personalAccount.setOwner(customer);
                    return personalAccountRepository.save(personalAccount);
                }).orElseThrow(() -> new NotFoundException("Customer does not exist (with ID: " + customerId));
    }

    @Override
    public void addFounds(Integer accountNumber, BigDecimal value) {
        //TODO
        System.out.println("Not implemented yet");
    }

    @Override
    public void removeFounds(Integer accountNumber, BigDecimal value) {
        //TODO
        System.out.println("Not implemented yet");
    }
}
