package com.simplebanking.sob.Controller;

import com.simplebanking.sob.Model.TransferImpl;
import com.simplebanking.sob.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping("accounts/{accountId}/transfers")
    public TransferImpl createInternalTransfer(@PathVariable Long accountId,
                                       @Valid @RequestBody TransferImpl transferImpl) {
        return transferService.saveTransfer(transferImpl, accountId);
    }

