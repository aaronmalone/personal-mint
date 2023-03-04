package com.aaronmalone.account.activity.utility;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.aaronmalone.account.activity.utility.Dates.MONTH_DAY_YEAR_FORMAT;
import static com.aaronmalone.account.activity.utility.Dates.determineTransactionDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DatesTest {

    private LocalDate date(String s) {
        return LocalDate.parse(s);
    }

    @Test
    void testMonthDayYearFormat() {
        assertEquals(date("2022-12-23"), LocalDate.parse("12/23/2022", MONTH_DAY_YEAR_FORMAT));
        assertEquals(date("2022-09-30"), LocalDate.parse("09/30/2022", MONTH_DAY_YEAR_FORMAT));
    }

    @Test
    void testDetermineTransactionDate() {
        final LocalDate d = date("2023-01-03");

        // same day
        assertEquals(d, determineTransactionDate("Payment 01/03", d));

        // previous day
        assertEquals(date("2023-01-02"), determineTransactionDate("foo bar 01/02", d));

        // previous month
        assertEquals(date("2022-12-31"), determineTransactionDate("late gift 12/31", d));

        // no date to be parsed
        assertEquals(d, determineTransactionDate("1465396710", d));

        // method only "looks back" ten days
        assertEquals(d, determineTransactionDate("Payment 12/12", d));
    }
}