package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 입력 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String MANUAL_COUNT_INPUT_GUIDE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
	private static final String PURCHASE_MONEY_INPUT_GUIDE_MESSAGE = "구입금액을 입력해주세요.";
	private static final String WINNING_LOTTO_INPUT_GUIDE_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String BONUS_BALL_INPUT_GUIDE_MESSAGE = "보너스 볼을 입력해 주세요.";
	private static final String MANUAL_LOTTO_TICKET_INPUT_GUIDE_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

	private InputView() {
	}

	public static long inputLottoMoney() {
		System.out.println(PURCHASE_MONEY_INPUT_GUIDE_MESSAGE);
		return Long.parseLong(SCANNER.nextLine());
	}

	public static int inputManualCount() {
		System.out.println(MANUAL_COUNT_INPUT_GUIDE_MESSAGE);
		return Integer.parseInt(SCANNER.nextLine());
	}

	public static List<String> inputManualLottoTicketNumbers(int manualCount) {
		List<String> lottoTicketNumbers = new ArrayList<>();
		if (manualCount == 0) {
			return lottoTicketNumbers;
		}
		System.out.println(MANUAL_LOTTO_TICKET_INPUT_GUIDE_MESSAGE);
		for (int count = 0; count < manualCount; count++) {
			lottoTicketNumbers.add(SCANNER.nextLine());
		}
		return lottoTicketNumbers;
	}

	public static String inputWinningLotto() {
		System.out.println(WINNING_LOTTO_INPUT_GUIDE_MESSAGE);
		return SCANNER.nextLine();
	}

	public static int inputBonusBall() {
		System.out.println(BONUS_BALL_INPUT_GUIDE_MESSAGE);
		return Integer.parseInt(SCANNER.nextLine());
	}
}
