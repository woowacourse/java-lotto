package utils;

import java.util.regex.Pattern;

public class InputValidator {

	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final Pattern COMPILED_NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";
	private static final int AMOUNT_MIN_RANGE = 1_000;
	private static final int AMOUNT_MAX_RANGE = 100_000;
	private static final int PROPER_WINNING_NUMBERS = 6;

	public static void validateMoney(final String money) {
		validateNumber(money);
		validateRange(money);
	}

	private static void validateRange(String money) {
		int number = Integer.parseInt(money);
		if (number < AMOUNT_MIN_RANGE || number > AMOUNT_MAX_RANGE) {
			throw new IllegalArgumentException(LotteryMessage.PURCHASE_AMOUNT_RANGE_ERROR);
		}
	}

	public static void validateNumber(String money) {
		if (!COMPILED_NUMBER_PATTERN.matcher(money).matches()) {
			throw new IllegalArgumentException(LotteryMessage.PURCHASE_AMOUNT_NOT_NUMBER_ERROR);
		}
	}

	public static void validateWinningNumber(String winningNumber) {
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
