package lotto.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputPurchaseMoney() {
		System.out.println("구입금액을을 입력해 주세요.");
		return SCANNER.nextLine();
	}

	public static String inputWinningLotto() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return SCANNER.nextLine();
	}

	public static String inputWinningLottoBonus() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return SCANNER.nextLine();
	}
}
