package com.aaronmalone.account.activity.utility;

import java.math.BigDecimal;

class StringCharSequence implements CharSequence {

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
        return new StringCharSequence(this.str, offset + start, end - start);
    }

    @Override
    public String toString() {
        return new String(str.toCharArray(), offset, length);
    }

    public BigDecimal toBigDecimal() {
        return new BigDecimal(str.toCharArray(), offset, length);
    }
}
