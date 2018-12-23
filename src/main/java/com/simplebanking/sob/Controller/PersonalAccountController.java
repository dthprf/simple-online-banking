package com.simplebanking.sob.Controller;

import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;
import com.simplebanking.sob.Exchange.Exchange;
import com.simplebanking.sob.Factory.MessageFactory;
import com.simplebanking.sob.Model.PersonalAccount;
import com.simplebanking.sob.Model.SOBMessage;
import com.simplebanking.sob.Service.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class PersonalAccountController {
    @Autowired
    PersonalAccountService personalAccountService;

    @Autowired
    Exchange exchange;

    @Autowired
    MessageFactory messageFactory;

    @PostMapping("/customers/{customerId}/accounts")
    public DeferredResult<PersonalAccount> createAccount(@PathVariable Long customerId,
                                                         @Valid @RequestBody PersonalAccount personalAccount) {

        DeferredResult<PersonalAccount> result = new DeferredResult<>();
        HashMap<String, Long> params = new HashMap<>();
        params.put("customerId", customerId);
        SOBMessage message = messageFactory.createMessage(MethodType.POST, RouteKey.ACCOUNTS,
                personalAccount, result, params);
        exchange.routeMessage(message);

        return result;
    }
}
