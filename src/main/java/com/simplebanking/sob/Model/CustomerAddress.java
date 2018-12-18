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

    protected CustomerAddress() {
    }

    public CustomerAddress(String city, String postCode, String streetName, String houseNumber, String country) {
        this.city = city;
        this.postCode = postCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.country = country;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
