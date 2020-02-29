package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String INPUT_MULTI_DATA_DELIMITER = ",";
	private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String INPUT_MANUAL_LOTTO_SIZE = "수동으로 구매할 로또 수를 입력해주세요.";
	private static final String INPUT_WINNING_LOTTO_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
	private static final String INPUT_WINNING_LOTTO_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
	private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
	private static final String ILLEGAL_NUMBER_FORMAT_EXCEPTION_MESSAGE = "입력 형식이 올바르지 않습니다.";
	private static final int MINIMUM_COUNT_ABLE_TO_INPUT_BY_CONSOLE = 1;

	private InputView() {
	}

	public static int inputMoney() {
		System.out.println(INPUT_MONEY_MESSAGE);
		return convertToInt(SCANNER.nextLine());
	}

	public static int inputManualTicketSize() {
		System.out.println(INPUT_MANUAL_LOTTO_SIZE);
		return convertToInt(SCANNER.nextLine());
	}

	public static List<List<Integer>> inputManualLottos(int count) {
		if (count < MINIMUM_COUNT_ABLE_TO_INPUT_BY_CONSOLE) {
			return Collections.emptyList();
		}
		System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			result.add(convertToIntegers(SCANNER.nextLine(), INPUT_MULTI_DATA_DELIMITER));
		}
		return result;
	}

	public static List<Integer> inputWinningLotto2() {
		System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
		return convertToIntegers(SCANNER.nextLine(), INPUT_MULTI_DATA_DELIMITER);
	}

	public static int inputWinningBonusBall() {
		System.out.println(INPUT_WINNING_LOTTO_BONUS_BALL_MESSAGE);
		return convertToInt(SCANNER.nextLine());
	}

	private static int convertToInt(String inputValue) {
		try {
			return Integer.parseInt(inputValue);
		} catch (NumberFormatException e) {
			throw new IllegalInputException(ILLEGAL_NUMBER_FORMAT_EXCEPTION_MESSAGE);
		}
	}

	private static List<Integer> convertToIntegers(String inputValue, String delimiterRegex) {
		try {
			return Arrays.stream(inputValue.split(delimiterRegex))
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new IllegalInputException(ILLEGAL_NUMBER_FORMAT_EXCEPTION_MESSAGE);
		}
	}
}
