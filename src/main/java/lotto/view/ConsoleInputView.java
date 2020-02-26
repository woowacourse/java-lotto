package lotto.view;

import static lotto.view.ConsoleOutputView.*;

import java.util.Scanner;

import lotto.domain.exception.InvalidLottoMoneyException;
import lotto.domain.lotto.LottoMoney;

public class ConsoleInputView {
	private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해주세요.";
	private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해주세요.";
	private static final String INPUT_WINNING_LOTTO_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String INPUT_BONUS_LOTTO_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

	private static Scanner scanner = new Scanner(System.in);

	public static LottoMoney continuousInputMoney() {
		try {
			return new LottoMoney(inputMoney());
		} catch (InvalidLottoMoneyException ime) {
			printExceptionMessage(ime.getMessage());
			return continuousInputMoney();
		}
	}

	private static String inputMoney() {
		System.out.println(INPUT_MONEY_MESSAGE);
		return scanner.nextLine();
	}

	public static String inputManualLottoCount() {
		System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
		return scanner.nextLine();
	}

	public static String inputManualLotto() {
		return scanner.nextLine();
	}

	public static String inputWinningLottoNumber() {
		System.out.println(INPUT_WINNING_LOTTO_NUMBER_MESSAGE);
		return scanner.nextLine();
	}

	public static String inputBonusLottoNumber() {
		System.out.println(INPUT_BONUS_LOTTO_NUMBER_MESSAGE);
		return scanner.nextLine();
	}
}
