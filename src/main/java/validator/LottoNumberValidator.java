package validator;

public class LottoNumberValidator {

    private static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1~45 사이로 입력해주세요.";
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;

    public static void validate(int number) {
        if (isNotInRangeNumber(number)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private static boolean isNotInRangeNumber(int number) {
        return !(number <= MAX_LOTTO_NUMBER && number >= MIN_LOTTO_NUMBER);
    }
}
