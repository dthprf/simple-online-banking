package com.simplebanking.sob.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer")
public class TransferImpl implements Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transferId;

    @ManyToOne(targetEntity = PersonalAccount.class)
    @JoinColumn(name = "target_account_id")
    private Accountable targetAccount;

    @ManyToOne(targetEntity = PersonalAccount.class)
    @JoinColumn(name = "source_account_id")
    private Accountable sourceAccount;
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    protected TransferImpl() {
    }

    public TransferImpl(Accountable targetAccount, Accountable sourceAccount, BigDecimal value,
                        TransactionStatus transactionStatus, TransferType transferType) {
        this.targetAccount = targetAccount;
        this.sourceAccount = sourceAccount;
        this.value = value;
        this.transactionStatus = transactionStatus;
        this.transferType = transferType;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Accountable getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Accountable targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Accountable getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Accountable sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }
}
