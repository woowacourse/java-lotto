package domain;

public class LottoNumber {
    public static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1~45 사이로 입력해주세요.";
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    public int getValue() {
        return number;
    }

    public void validate(int number) {
        if (isNotInRangeNumber(number)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private boolean isNotInRangeNumber(int number) {
        return !(number <= MAX_LOTTO_NUMBER && number >= MIN_LOTTO_NUMBER);
    }
}
