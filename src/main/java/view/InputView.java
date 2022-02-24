package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import utils.LotteryMessage;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final Pattern COMPILED_NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN);
	private static final String WINNING_NUMBER_PATTERN = "^([1-9]{1,2}[,][\\s]?){5}[1-9]{1,2}$";
	private static final Pattern COMPILED_WINNING_NUMBER_PATTERN = Pattern.compile(WINNING_NUMBER_PATTERN);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";
	private static final int AMOUNT_MIN_RANGE = 1_000;
	private static final int AMOUNT_MAX_RANGE = 100_000;

	public static int inputValidMoney() {
		final String money = inputMoney();
		return Integer.parseInt(money);
	}

	private static String inputMoney() {
		try {
			System.out.println(LotteryMessage.INPUT_PURCHASE_AMOUNT);
			final String money = scanner.nextLine();
			validateMoney(money);
			return money;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputMoney();
		}
	}

	private static void validateMoney(final String money) {
		validateNumber(money);
		validateRange(money);
	}

	private static void validateRange(String money) {
		int number = Integer.parseInt(money);
		if (number < AMOUNT_MIN_RANGE || number > AMOUNT_MAX_RANGE) {
			throw new IllegalArgumentException(LotteryMessage.PURCHASE_AMOUNT_RANGE_ERROR);
		}
	}

	private static void validateNumber(String money) {
		if (!COMPILED_NUMBER_PATTERN.matcher(money).matches()) {
			throw new IllegalArgumentException(LotteryMessage.PURCHASE_AMOUNT_NOT_NUMBER_ERROR);
		}
	}

	public static List<Integer> inputValidLotteryNumber() {
		final String numbers = inputLotteryNumber();
		return Arrays.stream(numbers.split(WINNING_NUMBER_DISTRIBUTOR))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	private static String inputLotteryNumber() {
		try {
			System.out.println(LotteryMessage.LAST_WEEK_WINNING_NUMBERS);
			final String winningNumber = scanner.nextLine();
			validateWinningNumber(winningNumber);
			return winningNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputLotteryNumber();
		}
	}

	private static void validateWinningNumber(String winningNumber) {
		if (!COMPILED_WINNING_NUMBER_PATTERN.matcher(winningNumber).matches()) {
			throw new IllegalArgumentException(LotteryMessage.WINNING_NUMBER_FORMAT_ERROR);
		}
	}

	public static int inputValidBonusNumber() {
		return Integer.parseInt(inputBonusNumber());
	}

	private static String inputBonusNumber() {
		try {
			System.out.println(LotteryMessage.LAST_WEEK_BONUS_NUMBER);
			final String bonusNumber = scanner.nextLine();
			validateNumber(bonusNumber);
			return bonusNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputBonusNumber();
		}
	}
}
