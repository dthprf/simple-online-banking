package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Model.Customer;
import com.simplebanking.sob.Model.SOBMessage;
import com.simplebanking.sob.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class CustomerMessageConsumer implements Runnable, MessageConsumer {

    private final BlockingQueue<SOBMessage> queue = new LinkedBlockingQueue<>();

    private String routeKey = "customers";

    @Autowired
    private CustomerService customerService;

    public CustomerMessageConsumer() {
        new Thread(this).start();
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

    @Override
    public void processMessage(SOBMessage message) {
        String method = message.getMethod();

        switch (method) {
            case "POST":
                Customer createdCustomer = customerService.createCustomer((Customer) message.getRequestBody());
                DeferredResult<Customer> result = (DeferredResult<Customer>) message.getDeferredResult();
                result.setResult(createdCustomer);
                break;
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
    public String getRouteKey() {
        return routeKey;
    }
}
