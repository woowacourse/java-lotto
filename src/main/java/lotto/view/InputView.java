package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String MESSAGE_MONEY_AMOUNT = "구입금액을 입력해 주세요.";
	private static final String MESSAGE_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String MESSAGE_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
	private static final String MESSAGE_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
	private static final String ERROR_TYPE = "[ERROR] 로또 개수는 숫자로 입력해 주세요";
	private static final String MESSAGE_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";

	private static final Scanner scanner = new Scanner(System.in);
	public static final String NUMBER_DELIMITER = ",";

	public static String askMoneyAmount() {
		System.out.println(MESSAGE_MONEY_AMOUNT);
		return scanner.nextLine();
	}

	public static int askManualLottoCount() {
		System.out.println(MESSAGE_MANUAL_LOTTO_COUNT);
		return checkType(scanner.nextLine());
	}

	private static int checkType(String manualCountInput) {
		int manualCount;
		try {
			manualCount = Integer.parseInt(manualCountInput);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_TYPE);
		}
		return manualCount;
	}

	public static List<String[]> askManualLottoNumbers(int count) {
		System.out.println(MESSAGE_MANUAL_LOTTO_NUMBERS);
		List<String[]> manualLottos = new ArrayList<>();
		for (int index = 0; index < count; index++) {
			manualLottos.add(scanner.nextLine().split(NUMBER_DELIMITER));
		}

		return manualLottos;
	}

	public static String[] askWinningNumbers() {
		System.out.println(MESSAGE_WINNING_NUMBERS);
		return splitByComma(scanner.nextLine());
	}

	public static String askBonusNumber() {
		System.out.println(MESSAGE_BONUS_NUMBER);
		return scanner.nextLine();
	}

	private static String[] splitByComma(String string) {
		return string.split(NUMBER_DELIMITER);
	}
}
