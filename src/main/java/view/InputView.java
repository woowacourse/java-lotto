package view;

import java.util.Scanner;

public class InputView {
	private static final String INPUT_MONEY_MESSAGE = "구입급액을 입력해 주세요.";
	private static final String INPUT_WINNING_NUMBER_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
	private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
	public static final String INPUT_PASSIVE_COUNT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
	public static final String INPUT_PASSIVE_LOTTOS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

	private final Scanner scanner = new Scanner(System.in);

	public String inputMoney() {
		System.out.println(INPUT_MONEY_MESSAGE);
		return scanner.nextLine();
	}

	public String inputWinningNumbers() {
		System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
		return scanner.nextLine();
	}

	public String inputBonusBall() {
		System.out.println(INPUT_BONUS_BALL_MESSAGE);
		return scanner.nextLine();
	}

	public String inputPassiveLottoCount() {
		System.out.println(INPUT_PASSIVE_COUNT_MESSAGE);
		return scanner.nextLine();
	}

	public String inputLottoNumbers() {
		return scanner.nextLine();
	}

	public void inputPassiveLottoMessage() {
		System.out.println(INPUT_PASSIVE_LOTTOS_MESSAGE);
	}
}
