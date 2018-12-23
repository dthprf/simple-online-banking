package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;
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

    private static final RouteKey ROUTE_KEY = RouteKey.ACCOUNTS;
    private final BlockingQueue<SOBMessage> queue = new LinkedBlockingQueue<>();
    @Autowired
    private PersonalAccountService accountService;

    public AccountMessageConsumer() {
        startConsumer();
    }

    public void startConsumer() {
        new Thread(this).start();
    }

    @Override
    public void processMessage(SOBMessage message) {
        MethodType method = message.getMethod();

        switch (method) {
            case POST:
                PersonalAccount createdAccount = (PersonalAccount) message.getRequestBody();
                Long customerId = message.getPathParams().get("customerId");
                DeferredResult<PersonalAccount> result = (DeferredResult<PersonalAccount>) message.getDeferredResult();
                result.setResult(accountService.createPersonalAccount(customerId, createdAccount));
                break;

            //TODO Default exception
        }
    }

    @Override
    public void enqueueMessage(SOBMessage message) {
        try {
            this.queue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //TODO real exception handling
        }
    }

    @Override
    public RouteKey getRouteKey() {
        return ROUTE_KEY;
    }

    @Override
    public void run() {
        SOBMessage message;

        while (!Thread.currentThread().isInterrupted()) {
            if (!queue.isEmpty()) {
                try {
                    message = queue.take();
                    processMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
