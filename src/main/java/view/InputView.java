package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import utils.InputValidator;
import utils.DomainTerminology;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String WINNING_NUMBER_DISTRIBUTOR = ",";
	private static final String INPUT_MANUAL_LOTTERIES_NUMBER = DomainTerminology.MANUAL
		+ "으로 구매할 번호를 입력해 주세요.";
	private static final String INPUT_PURCHASE_AMOUNT = DomainTerminology.PURCHASE_AMOUNT + "을 입력해 주세요";
	private static final String LAST_WEEK_WINNING_NUMBERS = "지난 주 "
		+ DomainTerminology.WINNING_NUMBER + "를 입력해 주세요.";
	private static final String LAST_WEEK_BONUS_NUMBER = DomainTerminology.BONUS_BALL + "을 입력해 주세요.";
	private static final String THE_NUMBER_OF_MANUAL_LOTTERY_TO_PURCHASE = DomainTerminology.MANUAL
		+ "으로 구입할 로또 수를 입력해 주세요.";

	public static int inputValidMoney() {
		final String money = inputData(InputValidator::validateMoney,
			() -> System.out.println(INPUT_PURCHASE_AMOUNT));
		return Integer.parseInt(money);
	}

	public static List<Integer> inputValidLotteryNumber() {
		final String numbers = inputData(InputValidator::validateLottery,
			() -> System.out.println(LAST_WEEK_WINNING_NUMBERS));
		return parseStringToIntegerList(numbers);
	}

	public static int inputValidBonusNumber() {
		return Integer.parseInt(inputData(InputValidator::validateBonusNumber,
			() -> System.out.println(LAST_WEEK_BONUS_NUMBER)));
	}

	public static int inputTheNumberOfValidManualLottery() {
		return Integer.parseInt(inputData(InputValidator::validateTheNumberOfManualLottery,
			() -> System.out.println(THE_NUMBER_OF_MANUAL_LOTTERY_TO_PURCHASE)));
	}

	public static List<List<Integer>> inputValidManualLotteries(final int theNumberOfLottery) {
		System.out.println(INPUT_MANUAL_LOTTERIES_NUMBER);
		List<List<Integer>> manualLotteries = new ArrayList<>();
		for (int i = 0; i < theNumberOfLottery; i++) {
			final List<Integer> lottery = parseStringToIntegerList(
				inputData(InputValidator::validateLottery, () -> {}));
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

	private static String inputData(final Consumer<String> validation, final InputMessage inputMessage) {
		try {
			inputMessage.printInputMessage();
			final String inputtedData = scanner.nextLine();
			validation.accept(inputtedData);
			return inputtedData;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputData(validation, inputMessage);
		}
	}
  
}
