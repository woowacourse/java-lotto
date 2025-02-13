public class Money {
    private int value;

    public Money(String input) {
        try {
            int parsed = Integer.parseInt(input.trim());
            validateIsPositive(parsed);
            value = parsed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.", e);
        }
    }

    public int getValue() {
        return value;
    }

    private void validateIsPositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("구입 금액은 양수만 가능합니다.");
        }
    }
}
