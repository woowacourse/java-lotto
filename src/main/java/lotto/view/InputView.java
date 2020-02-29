package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
	private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨번호를 입력해 주세요.";
	private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

	private static Scanner scanner = new Scanner(System.in);

	public static String inputAsMoney() {
		System.out.println(INPUT_MONEY_MESSAGE);
		return scanner.nextLine();
	}

	public static String inputAsWinningNumbers() {
		System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
		return scanner.nextLine();
	}

	public static String inputAsBonusNumber() {
		System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
		return scanner.nextLine();
	}

	public static String inputAsManualLottoAmount() {
		System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
		return scanner.nextLine();
	}

	public static List<String> inputAsManualLottoNumbers(int manualLottoAmount) {
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
		return IntStream.range(0, manualLottoAmount)
			.mapToObj(x -> scanner.nextLine())
			.collect(Collectors.toList());
	}
}
