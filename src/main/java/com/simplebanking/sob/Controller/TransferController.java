package com.simplebanking.sob.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.simplebanking.sob.Model.Transfer;
import com.simplebanking.sob.Model.TransferImpl;
import com.simplebanking.sob.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping("accounts/{accountId}/transfers")
    public MappingJacksonValue createInternalTransfer(@PathVariable Long accountId,
                                                      @Valid @RequestBody TransferImpl transferImpl) {

        Transfer deputedTransfer = transferService.saveTransfer(transferImpl, accountId);

        return serializeFullTransfer(deputedTransfer);
    }

    @PostMapping("accounts/{sourceAccountId}/transfers/{targetAccountId}")
    public MappingJacksonValue createExternalTransfer(@PathVariable Long sourceAccountId, @PathVariable Long targetAccountId,
                                                      @Valid @RequestBody TransferImpl transferImpl) {

        Transfer deputedTransfer = transferService.saveTransfer(transferImpl, sourceAccountId, targetAccountId);

        return serializeFullTransfer(deputedTransfer);
    }

    @GetMapping("accounts/{accountId}/transfers")
    public MappingJacksonValue getTransactions(@RequestParam Set<String> fields,
                                               @PathVariable Long accountId) {

        List<TransferImpl> transfers = transferService.getTransactions(accountId);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(transfers);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("transferFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    private MappingJacksonValue serializeFullTransfer(Transfer transfer) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(transfer);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("transferFilter", SimpleBeanPropertyFilter.serializeAll());

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
