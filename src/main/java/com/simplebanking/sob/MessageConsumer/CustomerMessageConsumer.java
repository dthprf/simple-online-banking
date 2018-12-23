package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;
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

    private static final RouteKey ROUTE_KEY = RouteKey.CUSTOMERS;
    private final BlockingQueue<SOBMessage> queue = new LinkedBlockingQueue<>();
    @Autowired
    private CustomerService customerService;

    public CustomerMessageConsumer() {
        startConsumer();
    }

    @Override
    public void startConsumer() {
        new Thread(this).start();
    }

    @Override
    public void processMessage(SOBMessage message) {
        MethodType method = message.getMethod();

        switch (method) {
            case POST:
                Customer createdCustomer = customerService.createCustomer((Customer) message.getRequestBody());
                DeferredResult<Customer> result = (DeferredResult<Customer>) message.getDeferredResult();
                result.setResult(createdCustomer);
                break;
            //TODO : ERROR HANDLING
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
