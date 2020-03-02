package lotto.view;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import lotto.domain.purchase.PurchasingCount;

public class ConsoleInputView {

	private static final String INPUT_LOTTO_MONEY_MESSAGE = "구입 금액을 입력해주세요.";
	private static final String INPUT_WINNING_LOTTO_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String INPUT_BONUS_LOTTO_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
	private static final Scanner SCANNER = new Scanner(System.in);

	private ConsoleInputView() {
	}

	public static String inputLottoMoney() {
		System.out.println(INPUT_LOTTO_MONEY_MESSAGE);
		return SCANNER.nextLine();
	}

	public static String inputManualPurchasingCount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return SCANNER.nextLine();
	}

	public static List<String> inputManualLottoTickets(PurchasingCount manualPurchasingCount) {
		return IntStream.range(0, manualPurchasingCount.getPurchasingCount())
			.mapToObj(e -> SCANNER.nextLine())
			.collect(toList());
	}

	public static String inputWinningLottoNumbers() {
		System.out.println(INPUT_WINNING_LOTTO_NUMBER_MESSAGE);
		return SCANNER.nextLine();
	}

	public static String inputBonusLottoNumber() {
		System.out.println(INPUT_BONUS_LOTTO_NUMBER_MESSAGE);
		return SCANNER.nextLine();
	}

}
