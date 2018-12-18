package com.simplebanking.sob.Model;

import javax.persistence.*;

@Entity
@Table(name = "contact_number")
public class MobilePhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numberId;
    private int areaCode;
    private int number;

    protected MobilePhoneNumber() {
    }

    public MobilePhoneNumber(int areaCode, int number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
