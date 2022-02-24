package validator;

import utils.Constants;

public class LottoBallValidator {

    public static final String LOTTO_NUM_RANGE_ERROR_MESSAGE = "로또 번호는 1에서 45사이의 수여야 합니다.";

    public static void validate(int number) {
        isRightNumberRange(number);
    }

    private static void isRightNumberRange(int number) {
        if (number < Constants.MIN_LOTTO_NUM || number > Constants.MAX_LOTTO_NUM) {
            throw new IllegalArgumentException(LOTTO_NUM_RANGE_ERROR_MESSAGE);
        }
    }
}