package com.simplebanking.sob.Service;

import com.simplebanking.sob.Exception.NotFoundException;
import com.simplebanking.sob.Exception.TransferIncorrectException;
import com.simplebanking.sob.Model.*;
import com.simplebanking.sob.Repository.PersonalAccountRepository;
import com.simplebanking.sob.Repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    PersonalAccountRepository personalAccountRepository;


    @Override
    public List<TransferImpl> getTransactions(Long accountId) {
        return transferRepository.findTransactions(accountId);
    }

    private boolean validateInternalTransfer(TransferImpl transferImpl) {
        BigDecimal transferValue = transferImpl.getValue();

        if (!isTransferValueCorrect(transferValue)) {
            return false;
        }

        if (transferImpl.getTargetAccount() == null || transferImpl.getSourceAccount() == null) {
            return false;
        }

        return true;
    }

    private TransferImpl fillTransfer(TransferImpl transferImpl, PersonalAccount targetAccount, PersonalAccount sourceAccount) {
        transferImpl.setTransferType(TransferType.BETWEENACCOUNTS);
        addTargetAccount(transferImpl, targetAccount);
        transferImpl.setSourceAccount(sourceAccount);

        return transferImpl;
    }
}
