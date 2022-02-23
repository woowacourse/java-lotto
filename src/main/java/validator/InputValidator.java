package validator;

import static utils.Messages.*;

import java.util.regex.Pattern;

public class InputValidator {
	public static void isRightPattern(String lottoNumbers) {
		final String REGEX = "^[\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+$";

		if (!Pattern.matches(REGEX, lottoNumbers)) {
			throw new IllegalArgumentException(LOTTO_NUMBER_INPUT_PATTERN_ERROR_MESSAGE);
		}
	}
}