package com.aaronmalone.account.activity.utility;

import java.util.ArrayList;

public class Split {

    private Split() {
        // utility class
    }

    public static String[] split(final String line) {
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

        return result.toArray(new String[0]);
    }

    private static boolean isQuoteCharacter(char c) {
        return c == '"';
    }
}
