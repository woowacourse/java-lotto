package utils;

import java.util.regex.Pattern;

public class InputValidator {

	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final Pattern COMPILED_NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN);
	private static final String WINNING_NUMBER_PATTERN = "^([1-9]{1,2}[,][\\s]?){5}[1-9]{1,2}$";
	private static final Pattern COMPILED_WINNING_NUMBER_PATTERN = Pattern.compile(WINNING_NUMBER_PATTERN);
	private static final int AMOUNT_MIN_RANGE = 1_000;
	private static final int AMOUNT_MAX_RANGE = 100_000;

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
		if (!COMPILED_WINNING_NUMBER_PATTERN.matcher(winningNumber).matches()) {
			throw new IllegalArgumentException(LotteryMessage.WINNING_NUMBER_FORMAT_ERROR);
		}
	}

}
