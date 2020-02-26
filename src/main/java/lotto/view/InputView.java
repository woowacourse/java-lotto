package lotto.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final int INVALID_NUMBER = Integer.MIN_VALUE;

	public static int inputPurchaseMoney() {
		int purchaseMoney;
		do {
			purchaseMoney = inputPurchaseMoneyIfValid();
		} while (purchaseMoney == INVALID_NUMBER);

		return purchaseMoney;
	}

	private static int inputPurchaseMoneyIfValid() {
		System.out.println("구입 금액을 입력해 주세요.");
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			return INVALID_NUMBER;
		}
	}

	public static String inputWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return scanner.nextLine();
	}

	public static int inputBonusNumber() {
		int bonusNumber;
		do {
			bonusNumber = inputBonusNumberIfValid();
		} while (bonusNumber == INVALID_NUMBER);

		return bonusNumber;
	}

	private static int inputBonusNumberIfValid() {
		System.out.println("보너스 볼을 입력해주세요.");
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			return INVALID_NUMBER;
		}
	}

	public static int inputManualTicketNumber() {
		int manualTicketNumber;
		do {
			manualTicketNumber = inputManualTicketNumberIfValid();
		} while (manualTicketNumber == INVALID_NUMBER);

		return manualTicketNumber;
	}

	private static int inputManualTicketNumberIfValid() {
		System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			return INVALID_NUMBER;
		}
	}
}
