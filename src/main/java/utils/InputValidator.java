package utils;

import java.util.regex.Pattern;

public class InputValidator {

	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final Pattern COMPILED_NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";
	private static final int PROPER_WINNING_NUMBERS = 6;

	public static void validateMoney(final String money) {
		validateNumber(money);
	}

	public static void validateNumber(final String money) {
		if (!COMPILED_NUMBER_PATTERN.matcher(money).matches()) {
			throw new IllegalArgumentException(LotteryMessage.PURCHASE_AMOUNT_NOT_NUMBER_ERROR);
		}
	}

	public static void validateWinningNumber(final String winningNumber) {
		final String[] winningNumbers = winningNumber.split(WINNING_NUMBER_DISTRIBUTOR);
		for (String number : winningNumbers) {
			checkWinningNumber(number);
		}
		checkWinningNumbers(winningNumbers.length);
	}

	private static void checkWinningNumber(final String number) {
		if (!COMPILED_NUMBER_PATTERN.matcher(number).matches()) {
			throw new IllegalArgumentException(LotteryMessage.WINNING_NUMBER_FORMAT_ERROR);
		}
	}

	private static void checkWinningNumbers(final int size) {
		if(size != PROPER_WINNING_NUMBERS) {
			throw new IllegalArgumentException(LotteryMessage.WINNING_NUMBER_FORMAT_ERROR);
		}
	}

}
