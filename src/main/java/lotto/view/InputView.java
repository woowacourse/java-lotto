package lotto.view;

import java.util.Scanner;

public class InputView {
	public static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해주세요.";

	private static Scanner scanner = new Scanner(System.in);

	public static String inputMoney() {
		System.out.println(INPUT_MONEY_MESSAGE);
		return scanner.nextLine();
	}
}
