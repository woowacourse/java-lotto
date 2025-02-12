package domain;

public class Number {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final int value;

    public Number(int value) {
        validateNumberRange(value);
        this.value = value;
    }

    private void validateNumberRange(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException("번호는 " + MIN_NUMBER + " 이상 " + MAX_NUMBER + "이하여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Number number = (Number) o;
        return getValue() == number.getValue();
    }

    @Override
    public int hashCode() {
        return getValue();
    }
}
