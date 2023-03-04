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

}