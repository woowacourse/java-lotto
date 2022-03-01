package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final String PAYMENT_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
	private static final String MANUAL_LOTTO_TICKETS_PURCHASE_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
	private static final String WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

	private static final String NUMBER_DELIMITER = ",";

	private static final String PAYMENT_IS_NOT_NUMERIC_ERROR = "금액은 숫자여야 합니다.";
	private static final String BALL_IS_NOT_A_NUMERIC_ERROR = "볼은 하나의 숫자여야 합니다.";
	private static final String NUMBER_IS_NOT_NUMERIC_ERROR = "구매 갯수는 숫자여야 합니다.";

	private static final Scanner scanner = new Scanner(System.in);

	public static int getPayment() {
		System.out.println(PAYMENT_MESSAGE);
		try {
			return Integer.parseInt(scanner.nextLine());

		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(PAYMENT_IS_NOT_NUMERIC_ERROR);
		}
	}

	public static int getManualLottoNumber() {
		System.out.println();
		System.out.println(MANUAL_LOTTO_NUMBER_MESSAGE);
		try {
			return Integer.parseInt(scanner.nextLine());

		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(NUMBER_IS_NOT_NUMERIC_ERROR);
		}
	}

	public static List<List<Integer>> getManualLottoTickets(final int count) {
		System.out.println();
		System.out.println(MANUAL_LOTTO_TICKETS_PURCHASE_MESSAGE);

		List<List<Integer>> tickets = new ArrayList<>();
		for(int i = 0; i<count; i++) {
			tickets.add(getManualLottoTicket());
		}

		return tickets;
	}

	private static List<Integer> getManualLottoTicket() {
		return Arrays.stream(scanner.nextLine()
			.split(NUMBER_DELIMITER, -1))
			.map(InputView::parseNumber)
			.collect(Collectors.toList());
	}

	public static List<Integer> getWinningNumber() {
		System.out.println();
		System.out.println(WINNING_NUMBER_MESSAGE);

		String[] input = scanner.nextLine()
			.split(NUMBER_DELIMITER, -1);

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
