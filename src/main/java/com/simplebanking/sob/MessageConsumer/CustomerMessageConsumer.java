package com.simplebanking.sob.MessageConsumer;

import com.simplebanking.sob.Model.SOBMessage;
import com.simplebanking.sob.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class CustomerMessageConsumer implements Runnable {
    private Queue<SOBMessage> queue = new ConcurrentLinkedQueue<>();

    @Autowired
    private CustomerService customerService;

    @Override
    public void run() {
        SOBMessage message;

        while (!Thread.currentThread().isInterrupted()) {
            message = queue.poll();

        }
    }
}
