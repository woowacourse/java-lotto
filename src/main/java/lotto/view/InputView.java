package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lotto.util.StringSplitUtil;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String INPUT_MANUAL_LOTTO_SIZE = "수동으로 구매할 로또 수를 입력해주세요.";
	private static final String WINNING_LOTTO_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
	private static final String WINNING_LOTTO_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
	private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
	private static final int MINIMUM_COUNT_ABLE_TO_INPUT_BY_CONSOLE = 1;

	private InputView() {
	}

	public static int inputMoney() {
		System.out.println(INPUT_MONEY_MESSAGE);
		return Integer.parseInt(SCANNER.nextLine());
	}

	public static int inputManualTicketSize() {
		System.out.println(INPUT_MANUAL_LOTTO_SIZE);
		return Integer.parseInt(SCANNER.nextLine());
	}

	public static List<String> inputManualLotto(int count) {
		List<String> result = new ArrayList<>();
		if (count < MINIMUM_COUNT_ABLE_TO_INPUT_BY_CONSOLE) {
			return result;
		}
		System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
		for (int i = 0; i < count; i++) {
			result.add(SCANNER.nextLine());
		}
		return result;
	}

	public static String[] inputWinningLotto() {
		System.out.println(WINNING_LOTTO_MESSAGE);
		return StringSplitUtil.splitRawLottoNumbers(SCANNER.nextLine());
	}

	public static int inputWinningBonusBall() {
		System.out.println(WINNING_LOTTO_BONUS_BALL_MESSAGE);
		return Integer.parseInt(SCANNER.nextLine());
	}
}
