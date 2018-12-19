package com.simplebanking.sob.Model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer")
@JsonFilter("transferFilter")
public class TransferImpl implements Transfer, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long transferId;

    @ManyToOne(targetEntity = PersonalAccount.class)
    @JoinColumn(name = "target_account_id")
    @JsonProperty("target")
    private Accountable targetAccount;

    @ManyToOne(targetEntity = PersonalAccount.class)
    @JoinColumn(name = "source_account_id")
    @JsonProperty("source")
    private Accountable sourceAccount;
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @JsonProperty("status")
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
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

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
