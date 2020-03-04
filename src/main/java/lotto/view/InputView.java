package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static Scanner scanner = new Scanner(System.in);

	public static int inputPurchaseMoney() {
		System.out.println("구입하실 금액을 입력해주세요.");
		return inputValidInteger();
	}

	public static int inputManualTicketCount() {
		System.out.println("수동으로 구입할 로또 티켓 숫자를 입력해주세요.");
		return inputValidInteger();
	}

	public static List<String> inputManualLottoTicket(int manualLottoTicketCount) {
		System.out.println("수동으로 구입할 로또 번호를 입력해주세요.");

		List<String> manualLottoTicketInput = new ArrayList<>();
		for(int i = 0; i < manualLottoTicketCount; i++) {
			manualLottoTicketInput.add(scanner.nextLine());
		}
		return manualLottoTicketInput;
	}

	public static String inputWinningNumbers() {
		System.out.println("지난 주 당첨 번호를 입력해주세요.");
		return scanner.nextLine();
	}

	public static int inputBonusNumber() {
		System.out.println("보너스 번호를 입력해주세요.");
		return inputValidInteger();
	}

	private static int inputValidInteger() {
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			OutputView.printExceptionMessage("올바른 정수를 입력해주세요.");
			return inputValidInteger();
		}
	}
}
