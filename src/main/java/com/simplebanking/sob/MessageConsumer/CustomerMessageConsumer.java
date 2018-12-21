package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Model.Customer;
import com.simplebanking.sob.Model.SOBMessage;
import com.simplebanking.sob.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class CustomerMessageConsumer implements Runnable, MessageConsumer {

    private Queue<SOBMessage> queue = new LinkedBlockingQueue<>();

    @Autowired
    private CustomerService customerService;

    @Override
    public void run() {
        SOBMessage message;

        while (!Thread.currentThread().isInterrupted() && !queue.isEmpty()) {
            message = queue.poll();
            processMessage(message);
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
    public void setQueue(Queue<SOBMessage> queue) {
        this.queue = queue;
    }

    @Override
    public void enqueueMessage(SOBMessage message) {
        queue.add(message);
    }

    @Override
    public String getRouteName() {
        return "customers";
    }
}
