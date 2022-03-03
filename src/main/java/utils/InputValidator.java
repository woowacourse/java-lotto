package utils;

import java.util.regex.Pattern;

public class InputValidator {

	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final Pattern COMPILED_NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";
	private static final int PROPER_LOTTERY_LENGTH = 6;

	public static void validateMoney(final String money) {
		validateNumber(money, LotteryMessage.PURCHASE_AMOUNT_NOT_NUMBER_ERROR);
	}

	public static void validateTheNumberOfManualLottery(final String number) {
		validateNumber(number, LotteryMessage.THE_NUMBER_OF_MANUAL_LOTTERY_IS_NOT_NUMBER);
	}

	public static void validateBonusNumber(final String number) {
		validateNumber(number, LotteryMessage.BONUS_BALL_NOT_NUMBER_ERROR);
	}

	public static void validateManualLottery(final String manualLottery) {
		final String[] manualLotteryNumbers = manualLottery.split(WINNING_NUMBER_DISTRIBUTOR);
		for (String number : manualLotteryNumbers) {
			validateNumber(number, LotteryMessage.LOTTERY_NUMBER_NOT_NUMBER_ERROR);
		}
		checkTheNumberOfNumber(manualLotteryNumbers.length, LotteryMessage.LOTTERY_SIZE_ERROR);
	}

	public static void validateWinningNumber(final String winningNumber) {
		final String[] winningNumbers = winningNumber.split(WINNING_NUMBER_DISTRIBUTOR);
		for (String number : winningNumbers) {
			validateNumber(number, LotteryMessage.LOTTERY_NUMBER_NOT_NUMBER_ERROR);
		}
		checkTheNumberOfNumber(winningNumbers.length, LotteryMessage.LOTTERY_SIZE_ERROR);
	}

	private static void validateNumber(final String number, final String errorMessage) {
		 if (!COMPILED_NUMBER_PATTERN.matcher(number).matches()) {
			 throw new IllegalArgumentException(errorMessage);
		 }
	}

	private static void checkTheNumberOfNumber(int length, final String errorMessage) {
		if (length != PROPER_LOTTERY_LENGTH) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

}
