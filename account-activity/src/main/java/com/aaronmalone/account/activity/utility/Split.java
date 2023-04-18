package com.aaronmalone.account.activity.utility;

import java.util.ArrayList;

public class Split {

    private Split() {
        // utility class
    }

    public static CharSequence[] split(final String line) {
        final ArrayList<CharSequence> result = new ArrayList<>();

        int startIndex = 0;

        boolean isQuoted = false;
        for (int i = 0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if (c == ',' && !isQuoted) {
                result.add(stripQuotes(line, startIndex, i - startIndex));
                startIndex = i + 1;
            } else if (c == '"') {
                isQuoted = !isQuoted;
            }
        }

        result.add(stripQuotes(line, startIndex, line.length() - startIndex));

        return result.toArray(new CharSequence[0]);
    }

    private static StringCharSequence stripQuotes(String str, int offset, int length) {
        if (length > 0
                && str.charAt(offset) == '"'
                && str.charAt(offset + length - 1) == '"') {
            return new StringCharSequence(str, offset+1, length-2);
        } else {
            return new StringCharSequence(str, offset, length);
        }
    }

    private static class StringCharSequence implements CharSequence {

        private final String str;
        private final int offset;
        private final int length;

        public StringCharSequence(String str, int offset, int length) {
            this.str = str;
            this.offset = offset;
            this.length = length;
        }

        @Override
        public int length() {
            return this.length;
        }

        @Override
        public char charAt(int index) {
            return this.str.charAt(offset + index);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return new StringCharSequence(this.str, offset + start, end-start);
        }

        @Override
        public String toString() {
            return new String(str.toCharArray(), offset, length);
        }
    }

}
