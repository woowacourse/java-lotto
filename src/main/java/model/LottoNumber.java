package model;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        checkNumber(number);
        this.number = number;
    }

    public int value() {
        return number;
    }

    private void checkNumber(int number) {
        if (isNotInRangeNumber(number)) {
            throw new RuntimeException();
        }
    }

    private boolean isNotInRangeNumber(int number) {
        return !(number <= 45 && number > 0);
    }
}
