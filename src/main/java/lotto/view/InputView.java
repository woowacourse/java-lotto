package lotto.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static int inputPurchaseMoney() {
		System.out.println("구입 금액을 입력해 주세요.");
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			return inputPurchaseMoney();
		}
	}

	public static String inputWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return scanner.nextLine();
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 볼을 입력해주세요.");
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			return inputBonusNumber();
		}
	}
}
