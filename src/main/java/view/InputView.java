package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String NUMBER_REGEX = "^[0-9]+$";
	private static final String DELIMITER = ", ";
	private static final String ERROR_NOT_NUMBER = "[ERROR] 입력은 반드시 숫자여야 합니다.";
	private static final String ERROR_BLANK_DETECT = "[ERROR] 입력은 빈 입력일 수 없습니다.";


	public static int inputMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return inputSingleNumber();
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return inputSingleNumber();
	}

	public static int inputManualLottoSize() {
		System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
		return inputSingleNumber();
	}

	public static List<Integer> inputSixLottoNumbers() {
		System.out.println("\n지난 주 당첨 번호를 구분자(, ) 기준으로 입력해 주세요.");
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
		String userInput = inputLine();
		validateEmpty(userInput);
		List<Integer> multipleNumbers = new ArrayList<>();
		String[] parsedUserInput = userInput.split(DELIMITER);
		for (String eachInput : parsedUserInput) {
			validateAllNumber(eachInput);
			multipleNumbers.add(Integer.parseInt(eachInput));
		}
		return multipleNumbers;
	}

	private static void validateAllNumber(String userInput) {
		if (!userInput.matches(NUMBER_REGEX)) {
			throw new IllegalArgumentException(ERROR_NOT_NUMBER);
		}
	}

	private static void validateEmpty(String userInput) {
		if (userInput.isEmpty()) {
			throw new IllegalArgumentException(ERROR_BLANK_DETECT);
		}
	}
}
