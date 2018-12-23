package com.simplebanking.sob.MessageConsumer;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;
import com.simplebanking.sob.Constants.TransferType;
import com.simplebanking.sob.Model.*;
import com.simplebanking.sob.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class TransferMessageConsumer implements Runnable, MessageConsumer {

    private static final String TARGET_ACCOUNT_ID = "targetAccountId";
    private static final String CUSTOMER_ACCOUNT = "sourceAccountId";
    private static final RouteKey ROUTE_KEY = RouteKey.TRANSFERS;
    private static final String FIELD_FILTER = "fieldFilter";

    @Autowired
    TransferService transferService;

    private final BlockingQueue<SOBMessage> queue = new LinkedBlockingQueue<>();

    public TransferMessageConsumer() {
        startConsumer();
    }

    @Override
    public void enqueueMessage(SOBMessage message) {
        try {
            this.queue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RouteKey getRouteKey() {
        return this.ROUTE_KEY;
    }

    @Override
    public void startConsumer() {
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
        MethodType method = message.getMethod();

        switch (method) {
            case POST:
                processPostTransfer(message);
                break;

            case GET:
                processGetTransfer(message);
                break;

            default:
                System.out.println("WRONG METHOD");
                //todo : return error
        }
    }

