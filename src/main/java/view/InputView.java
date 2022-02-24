package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import utils.InputValidator;
import utils.LotteryMessage;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";

	public static int inputValidMoney() {
		final String money = inputMoney();
		return Integer.parseInt(money);
	}

	private static String inputMoney() {
		try {
			System.out.println(LotteryMessage.INPUT_PURCHASE_AMOUNT);
			final String money = scanner.nextLine();
			InputValidator.validateMoney(money);
			return money;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputMoney();
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
			InputValidator.validateWinningNumber(winningNumber);
			return winningNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputLotteryNumber();
		}
	}

	public static int inputValidBonusNumber() {
		return Integer.parseInt(inputBonusNumber());
	}

	private static String inputBonusNumber() {
		try {
			System.out.println(LotteryMessage.LAST_WEEK_BONUS_NUMBER);
			final String bonusNumber = scanner.nextLine();
			InputValidator.validateNumber(bonusNumber);
			return bonusNumber;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputBonusNumber();
		}
	}
}
