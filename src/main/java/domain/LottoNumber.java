package domain;

public class LottoNumber {
    public static final int MIN = 1;
    public static final int MAX = 45;

    private final int value;

    public LottoNumber(int value) {
        validate(value);
        this.value = value;
    }

    public static LottoNumber from(String rawInput) {
        try {
            return new LottoNumber(Integer.parseInt(rawInput));
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
