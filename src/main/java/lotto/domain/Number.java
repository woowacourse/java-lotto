package lotto.domain;

public class Number {
    private static final int MAX_NUMBER = 45;
    private static final int MIN_NUMBER = 0;

    private final int num;

    public Number(final int num) {
        validate(num);
        this.num = num;
    }

    private void validate(final int num) {
        if (num < MIN_NUMBER || num > MAX_NUMBER) {
            throw new IllegalArgumentException("유효한 번호가 아닙니다.");
        }
    }
}
