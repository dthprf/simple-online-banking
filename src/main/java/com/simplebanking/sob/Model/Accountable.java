package com.simplebanking.sob.Model;

import java.math.BigDecimal;

public interface Accountable {
    void addFounds(BigDecimal value);
    void removeFounds(BigDecimal value);
}
