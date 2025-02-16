package domain;

public class Number {
    public static final int MIN = 1;
    public static final int MAX = 45;

    private final int value;

    public Number(int value) {
        validate(value);
        this.value = value;
    }

    public static Number from(String rawInput) {
        try {
            return new Number(Integer.parseInt(rawInput));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int getValue() {
        return value;
    }

    private void validate(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException();
        }
    }
}
