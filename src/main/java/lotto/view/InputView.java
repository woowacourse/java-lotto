package lotto.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static int inputPurchaseMoney() {
		String message = "구입 금액을 입력해 주세요.";
		return getValidInteger(message);
	}

	public static int inputManualTicketNumber() {
		String message = "수동으로 구매할 로또 수를 입력해주세요";
		return getValidInteger(message);
	}

	public static String inputManualTicket() {
		System.out.println("수동으로 구매할 로또 번호를 입력해주세요.");
		return scanner.nextLine();
	}

	public static String inputWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		return scanner.nextLine();
	}

	public static int inputBonusNumber() {
		String message = "보너스 볼을 입력해주세요.";
		return getValidInteger(message);
	}

	private static int getValidInteger(String message) {
		System.out.println(message);
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			return getValidInteger(message);
		}
	}
}
