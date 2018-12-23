package com.simplebanking.sob.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.simplebanking.sob.Constants.MethodType;
import com.simplebanking.sob.Constants.RouteKey;
import com.simplebanking.sob.Exchange.Exchange;
import com.simplebanking.sob.Factory.MessageFactory;
import com.simplebanking.sob.Model.SOBMessage;
import com.simplebanking.sob.Model.Transfer;
import com.simplebanking.sob.Model.TransferImpl;
import com.simplebanking.sob.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
public class TransferController {

    private static final String TARGET_ACCOUNT_ID = "targetAccountId";
    private static final String CUSTOMER_ACCOUNT = "sourceAccountId";

    @Autowired
    TransferService transferService;

    @Autowired
    Exchange exchange;

    @Autowired
    MessageFactory messageFactory;

    @PostMapping("accounts/{accountId}/transfers")
    public DeferredResult<MappingJacksonValue> createInternalTransfer(@PathVariable Long accountId,
                                                      @Valid @RequestBody TransferImpl transferImpl) {

        HashMap<String, Long> params = new HashMap<>();
        params.put(CUSTOMER_ACCOUNT, accountId);
        DeferredResult<MappingJacksonValue> response = new DeferredResult<>();

        SOBMessage message = messageFactory.createMessage(MethodType.POST, RouteKey.TRANSFERS, transferImpl,
                response, params);

        exchange.routeMessage(message);

        return response;
    }

    @PostMapping("accounts/{sourceAccountId}/transfers/{targetAccountId}")
    public DeferredResult<MappingJacksonValue> createExternalTransfer(@PathVariable Long sourceAccountId, @PathVariable Long targetAccountId,
                                                      @Valid @RequestBody TransferImpl transferImpl) {
        HashMap<String, Long> params = new HashMap<>();
        params.put(CUSTOMER_ACCOUNT, sourceAccountId);
        params.put(TARGET_ACCOUNT_ID, targetAccountId);
        DeferredResult<MappingJacksonValue> response = new DeferredResult<>();

        SOBMessage message = messageFactory.createMessage(MethodType.POST, RouteKey.TRANSFERS, transferImpl,
                response, params);

        exchange.routeMessage(message);

        return response;
    }

    @GetMapping("accounts/{accountId}/transfers")
    public DeferredResult<MappingJacksonValue> getTransactions(@RequestParam Set<String> fields,
                                                              @PathVariable Long accountId) {

        HashMap<String, Long> params = new HashMap<>();
        params.put(CUSTOMER_ACCOUNT, accountId);
        DeferredResult<MappingJacksonValue> response = new DeferredResult<>();

        SOBMessage message = messageFactory.createMessage(MethodType.GET, RouteKey.TRANSFERS,
                null, response, params, fields);

        exchange.routeMessage(message);

        return response;
    }
}
