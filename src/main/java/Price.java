public class Price {

    private final int value;

    public Price(String value) {
        validate(value);
        this.value = Integer.parseInt(value);
    }

    private void validate(String value) {

    }

    public int getValue() {
        return value;
    }
}
