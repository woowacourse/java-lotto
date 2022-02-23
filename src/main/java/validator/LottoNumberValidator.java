package validator;

import static utils.Messages.*;

public class LottoNumberValidator {
	public static void validate(int number) {
		isRightNumberRange(number);
	}

	private static void isRightNumberRange(int number) {
		if (number < 1 || number > 45) {
			throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
		}
	}
}