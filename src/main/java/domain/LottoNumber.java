package domain;

public class LottoNumber {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;
    public static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = String.format("로또 번호는 %d~%d 사이로 입력해주세요.",
            MIN_VALUE, MAX_VALUE);

    private final int value;

    public LottoNumber(int value) {
        validate(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void validate(int number) {
        if (isNotInRangeNumber(number)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private boolean isNotInRangeNumber(int number) {
        return !(number <= MAX_VALUE && number >= MIN_VALUE);
    }
}
