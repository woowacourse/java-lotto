package domain;

import domain.numberstrategy.NumberGenerator;
import domain.numberstrategy.RandomNumberGenerator;

public record Number(int value) {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public Number {
        validateNumberRange(value);
    }

    public static NumberGenerator createRandomNumberGenerator() {
        return new RandomNumberGenerator(MIN_NUMBER, MAX_NUMBER);
    }

    private void validateNumberRange(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException("번호는 " + MIN_NUMBER + " 이상 " + MAX_NUMBER + "이하여야 합니다.");
        }
    }
}
