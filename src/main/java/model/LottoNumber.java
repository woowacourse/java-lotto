package model;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(final int number) {
        this.number = checkNumber(number);
    }

    private int checkNumber(int number) {
        if (isNotCorrectNumber(number)) {
            throw new RuntimeException();
        }
        return number;
    }

    private boolean isNotCorrectNumber(final int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }

    public int getNumber() {
        return number;
    }
}
