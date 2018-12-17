package com.simplebanking.sob.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PersonalAccount implements Accountable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    private String accountNumber;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer owner;

    @OneToMany(targetEntity = Transfer.class, mappedBy = "targetAccount")
    private Set<Operationable> transactionsIn = new HashSet<>();

    @OneToMany(targetEntity = Transfer.class, mappedBy = "sourceAccount")
    private Set<Operationable> transactionsOut = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    protected PersonalAccount() {}

    public PersonalAccount(String accountNumber, BigDecimal balance, Customer owner, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.accountType = accountType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Set<Operationable> getTransactionsIn() {
        return transactionsIn;
    }

    public void setTransactionsIn(Set<Operationable> transactionsIn) {
        this.transactionsIn = transactionsIn;
    }

    public Set<Operationable> getTransactionsOut() {
        return transactionsOut;
    }

    public void setTransactionsOut(Set<Operationable> transactionsOut) {
        this.transactionsOut = transactionsOut;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public void addFounds(BigDecimal value) {
        this.balance = balance.add(value);
    }

    @Override
    public void removeFounds(BigDecimal value) {
        this.balance.subtract(value);
    }
}
