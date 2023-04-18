package com.aaronmalone.account.activity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Test
    void testFromCapitalOneCardWithDebit() {
        String line = "2023-02-12,2023-02-13,7197,DROPBOX,Internet,11.99,";
        BankTransaction transaction = BankTransaction.fromCapitalOneCard(line);

        assertEquals(line, transaction.line);
        assertEquals(LocalDate.parse("2023-02-13"), transaction.postDate);
        assertEquals(LocalDate.parse("2023-02-12"), transaction.transactionDate);
        assertEquals(BigDecimal.valueOf(-1199, 2), transaction.amount);
        assertEquals("DROPBOX", transaction.description);
        assertEquals("Internet", transaction.bankCategory);
        assertEquals("", transaction.bankType);
        assertEquals("", transaction.memo);
    }

    void testFromCapitalOneCardWithCredit() {
        String line = "2023-01-29,2023-01-30,7197,CAPITAL ONE ONLINE PYMT,Payment/Credit,,27.48";
        BankTransaction transaction = BankTransaction.fromCapitalOneCard(line);

        assertEquals(line, transaction.line);
        assertEquals(LocalDate.parse("2023-01-30"), transaction.postDate);
        assertEquals(LocalDate.parse("2023-01-29"), transaction.transactionDate);
        assertEquals(BigDecimal.valueOf(2748, 2), transaction.amount);
        assertEquals("CAPITAL ONE ONLINE PYMT", transaction.description);
        assertEquals("Payment/Credit", transaction.bankCategory);
        assertEquals("", transaction.bankType);
        assertEquals("", transaction.memo);
    }

    private void assertEquals(Object expected, Object actual) {
        boolean isStringCharSeqComparison = expected instanceof String && actual instanceof CharSequence;
        Assertions.assertEquals(expected, isStringCharSeqComparison ? actual.toString() : actual);
    }

}