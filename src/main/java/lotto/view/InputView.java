package lotto.view;

import java.util.Scanner;

public class InputView {
	private static Scanner scanner = new Scanner(System.in);

	public static String inputAsMoney() {
		System.out.println("구입금액을 입력해 주세요.");
		return scanner.nextLine();
	}

	public static String inputAsWinningNumbers() {
		System.out.println("지난 주 당첨번호를 입력해 주세요.");
		return scanner.nextLine();
	}

	public static String inputAsBonusNumber() {
		System.out.println("보너스 번호를 입력해 주세요.");
		return scanner.nextLine();
	}
}
