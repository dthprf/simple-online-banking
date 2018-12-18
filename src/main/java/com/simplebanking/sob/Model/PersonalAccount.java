package com.simplebanking.sob.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personal_account")
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

    @OneToMany(targetEntity = TransferImpl.class, mappedBy = "targetAccount")
    @JsonIgnore
    private Set<Transfer> transactions = new HashSet<>();

//    @OneToMany(targetEntity = TransferImpl.class, mappedBy = "sourceAccount")
//    @JsonIgnore
//    private Set<Transfer> transactionsOut = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    protected PersonalAccount() {
    }

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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public Set<Transfer> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transfer> transactions) {
        this.transactions = transactions;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void addFounds(BigDecimal value) {
        this.balance = balance.add(value);
    }

    @Override
    public void removeFounds(BigDecimal value) {
        this.balance = balance.subtract(value);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void addTransaction(Transfer transfer) {
        this.transactions.add(transfer);
    }
}
