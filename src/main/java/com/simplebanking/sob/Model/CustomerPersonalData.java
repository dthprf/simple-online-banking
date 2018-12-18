package com.simplebanking.sob.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Customer_detail")
public class CustomerPersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long userDataId;
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    private Date dateOfBirth;

    protected CustomerPersonalData() {
    }

    public CustomerPersonalData(String firstName, String lastName, String email, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getUserDataId() {
        return userDataId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
