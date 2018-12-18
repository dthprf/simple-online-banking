package com.simplebanking.sob.Service;

import com.simplebanking.sob.Model.PersonalAccount;

import java.math.BigDecimal;

public interface PersonalAccountService {
    PersonalAccount createPersonalAccount(Long customerId, PersonalAccount personalAccount);

    void addFounds(Integer accountNumber, BigDecimal value);

    void removeFounds(Integer accountNumber, BigDecimal value);
}
