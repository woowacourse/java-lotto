package lotto.model.winning_lotto.vo;

public class BonusNumber {

    protected static final int MIN = 1;
    protected static final int MAX = 45;

    private final int value;

    private BonusNumber(final int value) {
        validate(value);
        this.value = value;
    }

    public static BonusNumber from(final int value) {
        return new BonusNumber(value);
    }

    private static void validate(final int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException("보너스 번호는 " + MIN + "부터 " + MAX + " 사이여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BonusNumber{" +
                "value=" + value +
                '}';
    }
}
