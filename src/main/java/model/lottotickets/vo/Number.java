package model.lottotickets.vo;

import java.util.List;

public class Number {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public Number(final int number) {
        this.number = checkNumber(number);
    }

    private int checkNumber(final int number) {
        if (isNotCorrectNumber(number)) {
            throw new RuntimeException();
        }
        return number;
    }

    private boolean isNotCorrectNumber(final int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }

    public boolean contain(final List<Integer> otherNumbers) {
        return otherNumbers.contains(number);
    }

    public boolean contain(final int otherNumber) {
        return number == otherNumber;
    }

    public int get() {
        return number;
    }
}
