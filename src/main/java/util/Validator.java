package util;

public class Validator {

    private static final String ERROR = "[ERROR] ";
    private static final String NEGATIVE_PRICE_ERROR = "음수인 금액은 입력할 수 없습니다.";
    private static final String NOT_INTEGER_ERROR = "정수가 아닌 문자열이 입력되었습니다.";
    private static final int LOTTO_NUMBERS_COUNT = 6;
    public static final String INVALID_LOTTO_NUMBER_COUNT = "로또 번호의 수는 6자리를 입력해주어야 합니다.";

    public static void validateNegativePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(ERROR + NEGATIVE_PRICE_ERROR);
        }
    }

    public static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR + NOT_INTEGER_ERROR);
        }
    }

    public static void validateLottoNumbers(String[] lottoNumbers) {
        if (lottoNumbers.length != LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(ERROR + INVALID_LOTTO_NUMBER_COUNT);
        }
    }
}
