package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String NUMBER_REGEX = "^[0-9]+$";
	private static final String MUST_BE_INTEGER = "[ERROR] 입력은 반드시 숫자여야 합니다.";
	private static final String MUST_NOT_EMPTY = "[ERROR] 입력은 빈 입력일 수 없습니다.";

	public static int inputMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return inputSingleNumber();
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return inputSingleNumber();
	}

	public static List<Integer> inputAnsNumbers() {
		System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
		return inputMultipleNumber();
	}

	private static String inputLine() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private static int inputSingleNumber() {
		String userInput = inputLine();
		validateEmpty(userInput);
		validateAllNumber(userInput);
		return Integer.parseInt(userInput);
	}

	private static List<Integer> inputMultipleNumber() {
		List<Integer> answers = new ArrayList<>();
		String[] parsedUserInput = inputLine().split(", ");
		for (String eachInput : parsedUserInput) {
			validateAllNumber(eachInput);
			answers.add(Integer.parseInt(eachInput));
		}
		return answers;
	}

	private static void validateAllNumber(String userInput) {
		if (!userInput.matches(NUMBER_REGEX)) {
			throw new IllegalArgumentException(MUST_BE_INTEGER);

		}
	}

	private static void validateEmpty(String userInput) {
		if (userInput.isEmpty()) {
			throw new IllegalArgumentException(MUST_NOT_EMPTY);
		}
	}
}
