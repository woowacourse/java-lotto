package lotto.view;

import java.util.Scanner;

public class ConsoleInputView {
	private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해주세요.";
	private static final String INPUT_WINNING_LOTTO_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String INPUT_BONUS_LOTTO_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

	private static Scanner SCANNER = new Scanner(System.in);

	public static String inputMoney() {
		System.out.println(INPUT_MONEY_MESSAGE);
		return SCANNER.nextLine();
	}

	public static String inputWinningLottoNumber() {
		System.out.println(INPUT_WINNING_LOTTO_NUMBER_MESSAGE);
		return SCANNER.nextLine();
	}

	public static String inputBonusLottoNumber() {
		System.out.println(INPUT_BONUS_LOTTO_NUMBER_MESSAGE);
		return SCANNER.nextLine();
	}
}
