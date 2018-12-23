package com.simplebanking.sob.Controller;

import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;
import com.simplebanking.sob.Exchange.Exchange;
import com.simplebanking.sob.Factory.MessageFactory;
import com.simplebanking.sob.Model.Customer;
import com.simplebanking.sob.Model.SOBMessage;
import com.simplebanking.sob.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private MessageFactory messageFactory;

    @Autowired
    private Exchange exchange;

    @PostMapping("/customers")
    public DeferredResult<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        DeferredResult<Customer> result = new DeferredResult<>();
        SOBMessage message = messageFactory.createMessage(MethodType.POST, RouteKey.CUSTOMERS, customer, result);
        exchange.routeMessage(message);

        return result;
    }
}
