package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String NUMBER_REGEX = "^[0-9]+$";
	private static final String DELIMITER = ", ";
	private static final String ERROR_NOT_NUMBER = "[ERROR] 입력은 반드시 숫자여야 합니다.";
	private static final String ERROR_BLANK_DETECT = "[ERROR] 입력은 빈 입력일 수 없습니다.";
	private static final String NOTICE_INPUT_MONEY = "구입금액을 입력해 주세요.";
	private static final String NOTICE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
	private static final String NOTICE_INPUT_MANUAL_LOTTO_SIZE = "\n수동으로 구매할 로또 수를 입력해 주세요.";
	private static final String NOTICE_INPUT_MANUAL_LOTTO_NUMBER = "\n수동으로 구매할 번호를 구분자(, ) 기준으로 입력해 주세요.";
	private static final String NOTICE_INPUT_ANSWER_LOTTO_NUMBER = "\n지난 주 당첨 번호를 구분자(, ) 기준으로 입력해 주세요.";


	public static void noticeMoneyInput() {
		System.out.println(NOTICE_INPUT_MONEY);
	}

	public static void noticeBonusNumberInput() {
		System.out.println(NOTICE_INPUT_BONUS_NUMBER);
	}

	public static void noticeManualLottoSizeInput() {
		System.out.println(NOTICE_INPUT_MANUAL_LOTTO_SIZE);
	}

	public static void noticeAnswerLottoNumbersInput() {
		System.out.println(NOTICE_INPUT_ANSWER_LOTTO_NUMBER);
	}

	public static void noticeManualLottoNumbersInput() {
		System.out.println(NOTICE_INPUT_MANUAL_LOTTO_NUMBER);
	}

	private static String inputLine() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static int inputSingleNumber() {
		String userInput = inputLine();
		validateEmpty(userInput);
		validateAllNumber(userInput);
		return Integer.parseInt(userInput);
	}

	public static List<Integer> inputMultipleNumber() {
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
