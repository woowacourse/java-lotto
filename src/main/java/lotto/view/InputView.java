package lotto.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static int inputPurchaseMoney() {
		System.out.println("구입금액을을 입력해 주세요.");
		return SCANNER.nextInt();
	}
}
