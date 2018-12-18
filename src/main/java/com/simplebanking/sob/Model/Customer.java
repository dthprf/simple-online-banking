package com.simplebanking.sob.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Long customerId;

    @Column(name = "user_login", nullable = false)
    private String userLogin;

    @Column(name = "user_password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerPersonalData customerData;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerAddress customerAddress;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private Set<MobilePhoneNumber> contactNumbers = new HashSet<>();

    @OneToMany(targetEntity = PersonalAccount.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Set<Accountable> accounts = new HashSet<>();

    protected Customer() {
    }

    public Customer(String userLogin, String password, CustomerPersonalData customerData, CustomerAddress customerAddress, Set<MobilePhoneNumber> contactNumbers, Set<Accountable> accounts) {
        this.userLogin = userLogin;
        this.password = password;
        this.customerData = customerData;
        this.customerAddress = customerAddress;
        this.contactNumbers = contactNumbers;
        this.accounts = accounts;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerPersonalData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerPersonalData customerData) {
        this.customerData = customerData;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Set<MobilePhoneNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Set<MobilePhoneNumber> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Set<Accountable> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Accountable> accounts) {
        this.accounts = accounts;
    }
}
