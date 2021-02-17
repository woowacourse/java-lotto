public class Money {

    public static final int MIN_AMOUNT = 1000;
    private final int value;

    public Money(final String abc) {
        this.value = Integer.parseInt(abc);
        validateMinAmount(value);
    }

    private void validateMinAmount(final int value) {
        if (value < MIN_AMOUNT) {
            throw new IllegalArgumentException("구입 금액은 1000원 이상이여야 합니다.");
        }
    }
}
