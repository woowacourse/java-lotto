package domain;

public record Number(int value) {

    private static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    public Number {
        validateNumberRange(value);
    }

    private void validateNumberRange(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException("번호는 " + MIN_NUMBER + " 이상 " + MAX_NUMBER + " 이하여야 합니다.");
        }
    }
}
