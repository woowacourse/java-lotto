package validator;

public class LottoNumberValidator {

    private static final String NOT_INTEGER_ERROR_MESSAGE = "입력된 값이 정수가 아닙니다.";
    private static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1~45 사이로 입력해주세요.";
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;

    public static void validate(String number) {
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }

        if (isNotCorrectNumber(parsedNumber)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private static boolean isNotCorrectNumber(int number) {
        return !(number <= MAX_LOTTO_NUMBER && number >= MIN_LOTTO_NUMBER);
    }
}
