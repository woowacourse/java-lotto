package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String NUMBER_REGEX = "^[0-9]+$";
	private static final String MUST_BE_POSITIVE_INTEGER = "[ERROR] 입력은 양의 정수만 허용됩니다.";
	private static final String MUST_NOT_EMPTY = "[ERROR] 입력은 빈 입력일 수 없습니다.";

	public static int inputMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return inputSingleNumber();
	}

	public static List<Integer> inputAnsNumbers() {
		System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
		return inputMultipleNumber();
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return inputSingleNumber();
	}

	private static String inputLine() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private static int inputSingleNumber() {
		String userInput = inputLine();
		validateNumber(userInput);
		return Integer.parseInt(userInput);
	}

	private static List<Integer> inputMultipleNumber() {
		List<Integer> answers = new ArrayList<>();
		String[] parsedInput = inputLine().split(", ");

		for (String eachInput : parsedInput) {
			validateNumber(eachInput);
			answers.add(Integer.parseInt(eachInput));
		}

		return answers;
	}

	private static void validateNumber(String input) {
		validateEmpty(input);
		validatePositiveInteger(input);
	}

	private static void validatePositiveInteger(String input) {
		if (!input.matches(NUMBER_REGEX)) {
			throw new IllegalArgumentException(MUST_BE_POSITIVE_INTEGER);
		}
	}

	private static void validateEmpty(String Input) {
		if (Input.isEmpty()) {
			throw new IllegalArgumentException(MUST_NOT_EMPTY);
		}
	}
}
