package com.aaronmalone.account.activity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTransactionTest {

    @Test
    void testFromChaseCard() {
        String line = "07/16/2021,07/18/2021,Some Gas Station #765,Gas,Sale,-12.34,";
        BankTransaction transaction = BankTransaction.fromChaseCard(line);

        assertEquals(line, transaction.line);
        assertEquals(LocalDate.parse("2021-07-18"), transaction.postDate);
        assertEquals(LocalDate.parse("2021-07-16"), transaction.transactionDate);
        assertEquals(BigDecimal.valueOf(-1234, 2), transaction.amount);
        assertEquals("Some Gas Station #765", transaction.description);
        assertEquals("Gas", transaction.bankCategory);
        assertEquals("Sale", transaction.bankType);
        assertEquals("", transaction.memo);
    }

    @Test
    void testFromChaseCardWithoutTransactionDate() {
        String line = "DEBIT,11/30/2021,\"VENMO PAYMENT 54321\",-37.00,ACH_DEBIT,9875.54,,";
        BankTransaction transaction = BankTransaction.fromChaseBankAccount(line);

        assertEquals(line, transaction.line);
        assertEquals(LocalDate.parse("2021-11-30"), transaction.postDate);
        assertEquals(LocalDate.parse("2021-11-30"), transaction.transactionDate);
        assertEquals(BigDecimal.valueOf(-3700, 2), transaction.amount);
        assertEquals("VENMO PAYMENT 54321", transaction.description);
        assertEquals("", transaction.bankCategory);
        assertEquals("ACH_DEBIT", transaction.bankType);
        assertEquals("", transaction.memo);
    }

    @Test
    void testFromChaseCardWithTransactionDate() {
        String line = "CREDIT,01/02/2019,Merry Christmas 12/25,500.00,ACCT_XFER,12500.00,,";
        BankTransaction transaction = BankTransaction.fromChaseBankAccount(line);

        assertEquals(line, transaction.line);
        assertEquals(LocalDate.parse("2019-01-02"), transaction.postDate);
        assertEquals(LocalDate.parse("2018-12-25"), transaction.transactionDate);
        assertEquals(BigDecimal.valueOf(50000, 2), transaction.amount);
        assertEquals("Merry Christmas 12/25", transaction.description);
        assertEquals("", transaction.bankCategory);
        assertEquals("ACCT_XFER", transaction.bankType);
        assertEquals("", transaction.memo);
    }

}