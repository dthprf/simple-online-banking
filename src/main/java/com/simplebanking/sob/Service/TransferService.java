package com.simplebanking.sob.Service;

import com.simplebanking.sob.Model.TransferImpl;

import java.util.List;

public interface TransferService {

    List<TransferImpl> getTransactions(Long accountId);

    TransferImpl saveTransfer(TransferImpl transferImpl, Long targetAccountId);

    TransferImpl saveTransfer(TransferImpl transferImpl, Long sourceAccountId, Long targetAccountId);

}
