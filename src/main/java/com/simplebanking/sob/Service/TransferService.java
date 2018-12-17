package com.simplebanking.sob.Service;

import com.simplebanking.sob.Model.Transfer;

import java.util.List;

public interface TransferService {

    List<Transfer> getTransactions(Long accountId);

    Transfer saveTransfer(Transfer transfer, Long targetAccountId);

    Transfer saveTransfer(Transfer transfer, Long sourceAccountId, Long targetAccountId);

}
