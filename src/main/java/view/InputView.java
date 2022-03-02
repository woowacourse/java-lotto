package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String NUMBER_REGEX = "^[0-9]+$";
	private static final String STANDARD_OF_ANSWER_NUMBERS = ", ";
	private static final String WRONG_STANDARD_INPUT = "입력 형식에 맞춰 입력해주세요.";
	private static final String MUST_BE_POSITIVE_INTEGER = "입력은 양의 정수만 허용됩니다.";
	private static final String MUST_NOT_EMPTY = "입력은 빈 입력일 수 없습니다.";

	private InputView() {
	}

	public static int inputMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return inputSingleNumber();
	}

	public static int inputManualLottoCount() {
		System.out.println("\n수동으로 구매할 로또 수를 입력해주세요.");
		return inputSingleNumber();
	}

	public static List<List<Integer>> inputManualNumbers(int count) {
		System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");

		List<List<Integer>> totalManualNumbers = new ArrayList<>();
		while (count-- > 0) {
			totalManualNumbers.add(inputMultipleNumber());
		}

		return totalManualNumbers;
	}

	public static List<Integer> inputAnswerNumbers() {
		System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
		return inputMultipleNumber();
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return inputSingleNumber();
	}

	private static int inputSingleNumber() {
		String input = inputLine();
		validateNumber(input);
		return Integer.parseInt(input);
	}

	private static List<Integer> inputMultipleNumber() {
		String input = inputLine();
		validateMultipleNumber(input);
		return parseMultipleNumber(input);
	}

	private static String inputLine() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private static void validateNumber(String input) {
		validateEmpty(input);
		validatePositiveInteger(input);
	}

	private static void validateMultipleNumber(String input) {
		validateEmpty(input);
		validateStandard(input);
	}

	private static void validateEmpty(String input) {
		if (input == null || input.isEmpty()) {
			throw new IllegalArgumentException(MUST_NOT_EMPTY);
		}
	}

	private static void validatePositiveInteger(String input) {
		if (!input.matches(NUMBER_REGEX)) {
			throw new IllegalArgumentException(MUST_BE_POSITIVE_INTEGER);
		}
	}

	private static void validateStandard(String input) {
		if (!input.contains(STANDARD_OF_ANSWER_NUMBERS)) {
			throw new IllegalArgumentException(WRONG_STANDARD_INPUT);
		}
	}

	private static List<Integer> parseMultipleNumber(String input) {
		List<Integer> parsedMultipleNumbers = new ArrayList<>();
		String[] parsedInput = input.split(STANDARD_OF_ANSWER_NUMBERS);

		for (String eachInput : parsedInput) {
			validateNumber(eachInput);
			parsedMultipleNumbers.add(Integer.parseInt(eachInput));
		}

		return parsedMultipleNumbers;
	}

}
