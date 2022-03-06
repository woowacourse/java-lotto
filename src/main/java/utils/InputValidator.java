package utils;

import java.util.regex.Pattern;

public class InputValidator {

	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final Pattern COMPILED_NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";
	private static final String BONUS_BALL_NOT_NUMBER_ERROR = DomainTerminology.BONUS_BALL + "은 숫자여야 합니다";
	private static final String PURCHASE_AMOUNT_NOT_NUMBER_ERROR = DomainTerminology.PURCHASE_AMOUNT + "은 숫자여야 합니다.";
	private static final String LOTTERY_NUMBER_NOT_NUMBER_ERROR = DomainTerminology.LOTTERY_NUMBER + "은 숫자여야 합니다.";
	private static final String THE_NUMBER_OF_MANUAL_LOTTERY_IS_NOT_NUMBER = DomainTerminology.MANUAL_PURCHASE
		+ " 갯수는 숫자여야 합니다.";

	public static void validateMoney(final String money) {
		validateNumber(money, PURCHASE_AMOUNT_NOT_NUMBER_ERROR);
	}

	public static void validateTheNumberOfManualLottery(final String number) {
		validateNumber(number, THE_NUMBER_OF_MANUAL_LOTTERY_IS_NOT_NUMBER);
	}

	public static void validateBonusNumber(final String number) {
		validateNumber(number, BONUS_BALL_NOT_NUMBER_ERROR);
	}

	public static void validateLottery(final String lottery) {
		 final String[] lotteryNumbers = lottery.split(WINNING_NUMBER_DISTRIBUTOR);
		 for (String number : lotteryNumbers) {
			 validateNumber(number, LOTTERY_NUMBER_NOT_NUMBER_ERROR);
		 }
	}

	private static void validateNumber(final String number, final String errorMessage) {
		 if (!COMPILED_NUMBER_PATTERN.matcher(number).matches()) {
			 throw new IllegalArgumentException(errorMessage);
		 }
	}

}
