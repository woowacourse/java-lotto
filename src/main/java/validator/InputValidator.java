package validator;

import java.util.regex.Pattern;

public class InputValidator {

    public static final String LOTTO_NUMBER_INPUT_PATTERN_ERROR_MESSAGE = "로또 번호는 1, 2, 3, 4, 5, 6 과 같은 형태로 입력하여야 합니다.";

    public static void isRightPattern(String lottoNumbers) {
        final String REGEX = "^[\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+$";

        if (!Pattern.matches(REGEX, lottoNumbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_INPUT_PATTERN_ERROR_MESSAGE);
        }
    }
}