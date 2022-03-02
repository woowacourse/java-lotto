package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import utils.InputValidator;
import utils.LotteryMessage;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";

	public static int inputValidMoney() {
		final String money = inputData(LotteryMessage.INPUT_PURCHASE_AMOUNT, InputValidator::validateMoney);
		return Integer.parseInt(money);
	}

	public static List<Integer> inputValidLotteryNumber() {
		final String numbers = inputData(LotteryMessage.LAST_WEEK_WINNING_NUMBERS, InputValidator::validateWinningNumber);
		return Arrays.stream(numbers.split(WINNING_NUMBER_DISTRIBUTOR))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	public static int inputValidBonusNumber() {
		return Integer.parseInt(inputData(LotteryMessage.LAST_WEEK_BONUS_NUMBER, InputValidator::validateNumber));
	}

	private static String inputData(final String inputMessage, final Consumer<String> validation) {
		try {
			System.out.println(inputMessage);
			final String inputtedData = scanner.nextLine();
			validation.accept(inputtedData);
			return inputtedData;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputData(inputMessage, validation);
		}
	}


}
