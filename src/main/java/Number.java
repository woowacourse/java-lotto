public class Number {
    private int value;

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
        if (value < 1 || value > 45) {
            throw new IllegalArgumentException();
        }
    }

}
