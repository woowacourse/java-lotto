package src.model.vo;

public class LottoNumber {

    protected static final int MIN_NUMBER_RANGE = 1;
    protected static final int MAX_NUMBER_RANGE = 45;

    private final int value;

    private LottoNumber(final int value) {
        this.value = value;
    }

    public static LottoNumber from(final int value) {
        validate(value);
        return new LottoNumber(value);
    }

    private static void validate(final int value) {
        if (value < MIN_NUMBER_RANGE || value > MAX_NUMBER_RANGE) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "value=" + value +
                '}';
    }
}
