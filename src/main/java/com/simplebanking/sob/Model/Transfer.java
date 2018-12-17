package com.simplebanking.sob.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer")
public class Transfer implements Operationable {
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

    protected Transfer() {}

    public Transfer(Accountable targetAccount, Accountable sourceAccount, BigDecimal value, TransactionStatus transactionStatus) {
        this.targetAccount = targetAccount;
        this.sourceAccount = sourceAccount;
        this.value = value;
        this.transactionStatus = transactionStatus;
    }

    public Long getTransferId() {
        return transferId;
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

    public BigDecimal getValue() {
        return value;
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

    @Override
    public void proceedTransfer() {
        //TODO
        System.out.println("Not implemented yet");
    }
}
