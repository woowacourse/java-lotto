package lotto.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

	private static final String INPUT_PAYMENT_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String INPUT_ANSWER_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

	private static final String PAYMENT_IS_NOT_NUMERIC_EXCEPTION = "금액은 숫자여야 합니다.";

	private static final Scanner scanner = new Scanner(System.in);

	public static int getPayment() {
		System.out.println(INPUT_PAYMENT_MESSAGE);
		return parseNumber(scanner.nextLine());
	}

	private static int parseNumber(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(PAYMENT_IS_NOT_NUMERIC_EXCEPTION);
		}
	}

	public static List<Integer> getAnswerNumbers() {
		System.out.println(INPUT_ANSWER_NUMBERS_MESSAGE);
		String inputValue = scanner.nextLine();
		return Arrays.stream(inputValue.split(",", -1))
			.map(String::trim)
			.map(InputView::parseNumber)
			.collect(Collectors.toUnmodifiableList());
	}

	public static int getBonusBall() {
		System.out.println(INPUT_BONUS_BALL_MESSAGE);
		return parseNumber(scanner.nextLine());
	}

}
