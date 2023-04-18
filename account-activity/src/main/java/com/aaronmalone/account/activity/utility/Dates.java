package com.aaronmalone.account.activity.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

public class Dates {

    public static final DateTimeFormatter MONTH_DAY_YEAR_FORMAT = new DateTimeFormatterBuilder()
            .appendValue(MONTH_OF_YEAR)
            .appendLiteral('/')
            .appendValue(DAY_OF_MONTH)
            .appendLiteral('/')
            .appendValue(YEAR)
            .toFormatter();

    public static LocalDate determineTransactionDate(CharSequence description, LocalDate postDate) {
        int length = description.length();
        if (description.charAt(length - 3) == '/') {
            String datePart = description.subSequence(length - 5, length).toString();
            int month = getMonth(datePart);
            int day = getDay(datePart);
            LocalDate dateToCheck;
            for (int i = 0; i < 10; ++i) {
                dateToCheck = postDate.minusDays(i);
                if (dateToCheck.getDayOfMonth() == day && dateToCheck.getMonthValue() == month) {
                    return dateToCheck;
                }
            }
        }
        // just use the post date
        return postDate;
    }

    private static int getMonth(String date) {
        assert date.charAt(2) == '/';
        return Integer.parseInt(date.substring(0, 2));
    }

    private static int getDay(String date) {
        assert date.charAt(2) == '/';
        return Integer.parseInt(date.substring(3));
    }
}
