package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Model.PersonalAccount;
import com.simplebanking.sob.Model.SOBMessage;
import com.simplebanking.sob.Service.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class AccountMessageConsumer implements Runnable, MessageConsumer {

    private final BlockingQueue<SOBMessage> queue = new LinkedBlockingQueue<>();

    private String routeKey = "personalAccount";

    @Autowired
    private PersonalAccountService accountService;

    public AccountMessageConsumer() {
        new Thread(this).start();
    }

    @Override
    public void processMessage(SOBMessage message) {
        String method = message.getMethod();

        switch (method) {
            case "POST":
                PersonalAccount createdAccount = (PersonalAccount) message.getRequestBody();
                Long customerId = message.getPathParams().get("customerId");
                DeferredResult<PersonalAccount> result = (DeferredResult<PersonalAccount>) message.getDeferredResult();
                result.setResult(accountService.createPersonalAccount(customerId, createdAccount));
                break;
        }
    }
