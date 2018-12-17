package com.simplebanking.sob.Model;

import javax.persistence.*;

@Entity
@Table(name = "customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String postCode;
    private String city;
    private String streetName;
    private String houseNumber;
    private String country;

    protected CustomerAddress() {}

    public CustomerAddress(String city, String postCode, String streetName, String houseNumber, String country) {
        this.city = city;
        this.postCode = postCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.country = country;
    }


}
