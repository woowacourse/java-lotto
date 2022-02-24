package validator;

import java.util.regex.Pattern;

public class InputValidator {
    private static final String LOTTO_NUM_INPUT_PATTERN_ERROR_MESSAGE = "로또 번호는 1, 2, 3, 4, 5, 6 과 같은 형태로 입력하여야 합니다.";
    private static final Pattern PATTERN = Pattern.compile("^[\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+$");

    public static void isRightPattern(final String lottoNumbers) {
        if (!PATTERN.matcher(lottoNumbers).matches()) {
            throw new IllegalArgumentException(LOTTO_NUM_INPUT_PATTERN_ERROR_MESSAGE);
        }
    }
}