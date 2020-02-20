package lotto.validator;

import lotto.exception.NotIntegerException;

public class Validator {

	public static void validateInteger(String inputMoney) {
		try {
			Integer.parseInt(inputMoney);
		} catch (NumberFormatException e) {
			throw new NotIntegerException();
		}
	}

}
