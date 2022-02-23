package validator;

public class LottoNumberValidator {

    public static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1에서 45사이의 수여야 합니다.";

    public static void validate(int number) {
        isRightNumberRange(number);
    }

    private static void isRightNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }
}