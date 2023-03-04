package com.aaronmalone.account.activity;

import java.math.BigDecimal;
import java.time.LocalDate;

class BankTransaction {

    final String line;
    final LocalDate postDate;
    final LocalDate transactionDate;
    final BigDecimal amount;
    final String description;
    final String bankCategory;
    final String bankType;
    final String memo;

    public BankTransaction(String line, LocalDate postDate, LocalDate transactionDate,
                           BigDecimal amount, String description, String bankCategory,
                           String bankType, String memo) {
        this.line = line;
        this.postDate = postDate;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
        this.bankCategory = bankCategory;
        this.bankType = bankType;
        this.memo = memo;
    }
}
