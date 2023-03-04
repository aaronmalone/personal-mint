package com.aaronmalone.account.activity.utility;

import java.util.ArrayList;
import java.util.List;

public class Split {

    private Split() {
        // utility class
    }

    public static List<String> split(final String line) {
        final ArrayList<String> result = new ArrayList<>();

        StringBuilder current = new StringBuilder();

        boolean isQuoted = false;
        for (char c : line.toCharArray()) {
            if (c == ',' && !isQuoted) {
                result.add(current.toString());
                current = new StringBuilder();
            } else if (isQuoteCharacter(c)) {
                isQuoted = !isQuoted;
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());

        return result;
    }

    private static boolean isQuoteCharacter(char c) {
        return c == '"';
    }
}
