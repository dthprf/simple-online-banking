package com.simplebanking.sob.Model;

import java.math.BigDecimal;

public interface Accountable {
    
    BigDecimal getBalance();

    void addTransaction(Operationable transfer);

    void addFounds(BigDecimal value);

    void removeFounds(BigDecimal value);
}
