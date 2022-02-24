package view;

import static view.messages.InputExceptionMessages.*;
import static view.messages.InputViewMessages.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String NUMBER_PATTERN = "^[0-9]*$";
	private static final Pattern COMPILED_NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN);
	private static final String WINNING_NUMBER_PATTERN = "^([1-9]{1,2}[,][\\s]?){5}[1-9]{1,2}$";
	private static final Pattern COMPILED_WINNING_NUMBER_PATTERN = Pattern.compile(WINNING_NUMBER_PATTERN);
	private static final String WINNING_NUMBER_DELIMITER = ",";
	private static final int MINIMUM_MONEY = 1000;
	private static final int MAXIMUM_MONEY = 100000;

	public static int inputValidMoney() {
		final String money = inputMoney();
		return Integer.parseInt(money);
	}

	private static String inputMoney() {
		try {
			System.out.println(INPUT_MONEY_MESSAGE.getMessage());
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

	private static void validateNumber(String money) {
		if (!COMPILED_NUMBER_PATTERN.matcher(money).matches()) {
			throw new IllegalArgumentException(INVALID_INPUT_NUMBER_EXCEPTION.getMessage());
		}
	}

	private static void validateRange(String money) {
		int number = Integer.parseInt(money);
		if (number < MINIMUM_MONEY || number > MAXIMUM_MONEY) {
			throw new IllegalArgumentException(INVALID_MONEY_RANGE_EXCEPTION.getMessage());
		}
	}

	public static List<Integer> inputValidLotteryNumber() {
		final String numbers = inputLotteryNumber();
		return splitNumbers(numbers);
	}

	private static String inputLotteryNumber() {
		try {
			System.out.println(INPUT_WINNING_NUMBER_MESSAGE.getMessage());
			final String winningNumber = scanner.nextLine();
			validateWinningNumber(winningNumber);
			return winningNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputLotteryNumber();
		}
	}

	private static List<Integer> splitNumbers(String numbers) {
		return Arrays.stream(numbers.split(WINNING_NUMBER_DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}



	private static void validateWinningNumber(String winningNumber) {
		if (!COMPILED_WINNING_NUMBER_PATTERN.matcher(winningNumber).matches()) {
			throw new IllegalArgumentException(INVALID_WINNING_NUMBER_EXCEPTION.getMessage());
		}
	}

	public static int inputValidBonusNumber() {
		return Integer.parseInt(inputBonusNumber());
	}

	private static String inputBonusNumber() {
		try {
			System.out.println(INPUT_BONUS_BALL_MESSAGE.getMessage());
			final String bonusNumber = scanner.nextLine();
			validateNumber(bonusNumber);
			return bonusNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputBonusNumber();
		}
	}
}
