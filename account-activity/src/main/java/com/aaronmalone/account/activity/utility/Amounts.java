package com.aaronmalone.account.activity.utility;

import java.math.BigDecimal;

public class Amounts {

    public static BigDecimal amountOrZero(CharSequence chars) {
        if (chars == null || chars.length() == 0) {
            return BigDecimal.ZERO;
        }
        if (chars instanceof StringCharSequence) {
            return ((StringCharSequence) chars).toBigDecimal();
        } else {
            return new BigDecimal(chars.toString().trim());
        }
    }
}
