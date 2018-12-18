package com.simplebanking.sob.Model;

import java.math.BigDecimal;

public interface Accountable {
    
    BigDecimal getBalance();

    void addTransaction(Transfer transfer);

    void addFounds(BigDecimal value);

    void removeFounds(BigDecimal value);
}
