package view;

import java.util.ArrayList;
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
		final String numbers = inputData(LotteryMessage.LAST_WEEK_WINNING_NUMBERS,
			InputValidator::validateLottery);
		return parseStringToIntegerList(numbers);
	}

	public static int inputValidBonusNumber() {
		return Integer.parseInt(inputData(LotteryMessage.LAST_WEEK_BONUS_NUMBER, InputValidator::validateBonusNumber));
	}

	public static int inputTheNumberOfValidManualLottery() {
		return Integer.parseInt(inputData(LotteryMessage.THE_NUMBER_OF_MANUAL_LOTTERY_TO_PURCHASE,
			InputValidator::validateTheNumberOfManualLottery));
	}

	public static List<List<Integer>> inputValidManualLotteries(final int theNumberOfLottery) {
		System.out.println(LotteryMessage.INPUT_MANUAL_LOTTERIES_NUMBER);
		List<List<Integer>> manualLotteries = new ArrayList<>();
		for (int i = 0; i < theNumberOfLottery; i++) {
			final List<Integer> lottery = parseStringToIntegerList(
				inputData("", InputValidator::validateLottery));
			manualLotteries.add(lottery);
		}
		return manualLotteries;
	}

	private static List<Integer> parseStringToIntegerList(final String numbers) {
		return Arrays.stream(numbers.split(WINNING_NUMBER_DISTRIBUTOR))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
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
