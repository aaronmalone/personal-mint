package com.aaronmalone.account.activity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.aaronmalone.account.activity.utility.Amounts.amountOrZero;
import static com.aaronmalone.account.activity.utility.Dates.MONTH_DAY_YEAR_FORMAT;
import static com.aaronmalone.account.activity.utility.Dates.determineTransactionDate;
import static com.aaronmalone.account.activity.utility.Split.split;

class BankTransaction {

    final CharSequence line;
    final LocalDate postDate;
    final LocalDate transactionDate;
    final BigDecimal amount;
    final CharSequence description;
    final CharSequence bankCategory;
    final CharSequence bankType;
    final CharSequence memo;

    public BankTransaction(CharSequence line, LocalDate postDate, LocalDate transactionDate,
                           BigDecimal amount, CharSequence description, CharSequence bankCategory,
                           CharSequence bankType, CharSequence memo) {
        this.line = line;
        this.postDate = postDate;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
        this.bankCategory = bankCategory;
        this.bankType = bankType;
        this.memo = memo;
    }

    static BankTransaction fromChaseCard(String line) {
        CharSequence[] array = split(line);
        return new BankTransaction(
                line,
                LocalDate.parse(array[1], MONTH_DAY_YEAR_FORMAT), // post date
                LocalDate.parse(array[0], MONTH_DAY_YEAR_FORMAT), // transaction date
                amountOrZero(array[5]),   // amount
                array[2],                 // description
                array[3],                 // category
                array[4],                 // type
                array[6]                  // memo
        );
    }

    static BankTransaction fromChaseBankAccount(String line) {
        CharSequence[] array = split(line);

        // NOTE:
        //   index 0 has 'Details' column (CREDIT, DEBIT, CHECK, etc.)
        //   index 5 has bank balance

        LocalDate postDate = LocalDate.parse(array[1], MONTH_DAY_YEAR_FORMAT);
        CharSequence description = array[2];
        LocalDate transactionDate = determineTransactionDate(description, postDate);
        return new BankTransaction(
                line,
                postDate,
                transactionDate,
                amountOrZero(array[3]),
                description,
                "",
                array[4],
                array[6]
        );
    }

    public static BankTransaction fromCapitalOneCard(String line) {
        CharSequence[] array = split(line);

        // Note: index 2 is card number

        BigDecimal debit = amountOrZero(array[5]);
        BigDecimal credit = amountOrZero(array[6]);

        return new BankTransaction(
                line,
                LocalDate.parse(array[1]),
                LocalDate.parse(array[0]),
                credit.subtract(debit),
                array[3],
                array[4],
                "",
                ""
        );
    }
}
