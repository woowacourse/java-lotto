package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final String PAYMENT_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

	private static final String WINNING_NUMBER_DELIMITER = ",";

	private static final String PAYMENT_IS_NOT_NUMERIC_ERROR = "금액은 숫자여야 합니다.";
	private static final String BALL_IS_NOT_A_NUMERIC_ERROR = "볼은 하나의 숫자여야 합니다.";

	private static final Scanner scanner = new Scanner(System.in);

	public static int getPayment() {
		System.out.println(PAYMENT_MESSAGE);

		try {
			return Integer.parseInt(scanner.nextLine());

		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(PAYMENT_IS_NOT_NUMERIC_ERROR);
		}
	}

	public static List<Integer> getWinningNumber() {
		System.out.println();
		System.out.println(WINNING_NUMBER_MESSAGE);

		String[] input = scanner.nextLine()
			.split(WINNING_NUMBER_DELIMITER, -1);

		return Arrays.stream(input)
			.map(String::trim)
			.map(InputView::parseNumber)
			.collect(Collectors.toUnmodifiableList());
	}

	public static int getBonusBall() {
		System.out.println(BONUS_BALL_MESSAGE);

		return parseNumber(scanner.nextLine());
	}

	private static int parseNumber(String number) {
		try {
			return Integer.parseInt(number);

		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(BALL_IS_NOT_A_NUMERIC_ERROR);
		}
	}
}
