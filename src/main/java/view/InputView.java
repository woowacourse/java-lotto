package view;

import java.util.Scanner;

public class InputView {
	private static final String NUMBER_REGEX = "^[0-9]+$";

	public static int inputMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return inputSingleNumber();
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return inputSingleNumber();
	}

	public static String[] inputAnsNumbers() {
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

	private static String[] inputMultipleNumber() {
		String userInput = inputLine();
		validateEmpty(userInput);
		String[] parsedUserInput = userInput.split(", ");
		for (String eachInput : parsedUserInput) {
			validateAllNumber(eachInput);
		}
		return parsedUserInput;
	}

	private static void validateAllNumber(String userInput) {
		if (!userInput.matches(NUMBER_REGEX)) {
			throw new IllegalArgumentException("[ERROR] 입력은 반드시 숫자여야 합니다.");
		}
	}

	private static void validateEmpty(String userInput) {
		if (userInput.isEmpty()) {
			throw new IllegalArgumentException("[ERROR] 입력은 빈 입력일 수 없습니다.");
		}
	}
}
