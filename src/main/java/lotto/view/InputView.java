package lotto.view;

import java.util.Scanner;

public class InputView {
	private static final int INDEX_FIX = 1;
	private static final Scanner scanner = new Scanner(System.in);

	public static String inputPayment() {
		System.out.println("구입금액을 입력해주세요");
		return scanner.nextLine();
	}

	public static String inputWinningNumbers() {
		System.out.println("지난주 당첨 번호를 입력해주세요");
		return scanner.nextLine();
	}

	public static String inputBonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요");
		return scanner.nextLine();
	}

	public static String inputManualTicketValue() {
		System.out.println("수동으로 구매할 로또 수를 입력해주세요");
		return scanner.nextLine();
	}

	public static String inputManualNumbers(int ticketIndex) {
		System.out.printf("수동으로 구매할 %d번째 로또 번호를 입력해주세\n",ticketIndex+INDEX_FIX);
		return scanner.nextLine();
	}
}
