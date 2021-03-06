package com.simplebanking.sob.MessageConsumer;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;
import com.simplebanking.sob.Model.SOBMessage;
import com.simplebanking.sob.Model.Transfer;
import com.simplebanking.sob.Model.TransferImpl;
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
    private final BlockingQueue<SOBMessage> queue = new LinkedBlockingQueue<>();
    @Autowired
    TransferService transferService;

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

    private void processPostTransfer(SOBMessage message) {
        Map<String, Long> params = message.getPathParams();
        DeferredResult<MappingJacksonValue> response = (DeferredResult<MappingJacksonValue>) message.getDeferredResult();
        TransferImpl postedTransfer = (TransferImpl) message.getRequestBody();
        TransferImpl result;

//        TransferType transferType = postedTransfer.getTransferType();

        switch (params.size()) {
            case 1:
                result = transferService.saveTransfer(postedTransfer, params.get(CUSTOMER_ACCOUNT));
                response.setResult(serializeFullTransfer(result));
                break;

            case 2:
                result = transferService.saveTransfer(postedTransfer, params.get(CUSTOMER_ACCOUNT),
                        params.get(TARGET_ACCOUNT_ID));
                response.setResult(serializeFullTransfer(result));
                break;

            default:
                System.out.println("ERROR: WRONG TRANSFER TYPE");
                //todo : return error result
        }
    }

    private void processGetTransfer(SOBMessage message) {
        Long accountId = message.getPathParams().get(CUSTOMER_ACCOUNT);
        DeferredResult<MappingJacksonValue> response = (DeferredResult<MappingJacksonValue>)
                message.getDeferredResult();

        List<TransferImpl> transfers = transferService.getTransactions(accountId);
        Set<String> fieldsFilter = message.getFilterParams();

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(transfers);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("transferFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fieldsFilter));

        mappingJacksonValue.setFilters(filters);
        response.setResult(mappingJacksonValue);
    }

    private MappingJacksonValue serializeFullTransfer(Transfer transfer) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(transfer);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("transferFilter", SimpleBeanPropertyFilter.serializeAll());

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
