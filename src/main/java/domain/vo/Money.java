package domain.vo;

public class Money {
    private static final int LOTTO = 1_000;
    private int value;

    public static Money of(String input) {
        try {
            final int parsed = Integer.parseInt(input.trim());
            return new Money(parsed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.", e);
        }
    }

    public Money(int input) {
        validateIsPositive(input);
        this.value = input;
    }

    public int getValue() {
        return value;
    }

    public int getLottoCount() {
        if (this.value % LOTTO != 0) {
            throw new IllegalArgumentException(String.format("구입 금액은 %d원 단위만 가능합니다.", LOTTO));
        }
        return this.value / LOTTO;
    }

    private void validateIsPositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("구입 금액은 양수만 가능합니다.");
        }
    }
}
