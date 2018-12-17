package com.simplebanking.sob.Controller;

import com.simplebanking.sob.Model.PersonalAccount;
import com.simplebanking.sob.Service.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersonalAccountController {
    @Autowired
    PersonalAccountService personalAccountService;

    @PostMapping("/customers/{customerId}/accounts")
    public PersonalAccount createAccount(@PathVariable Long customerId,
                                         @Valid @RequestBody PersonalAccount personalAccount) {
        return personalAccountService.createPersonalAccount(customerId, personalAccount);
    }
}
