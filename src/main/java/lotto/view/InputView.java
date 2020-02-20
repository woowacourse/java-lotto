package lotto.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static int inputPurchaseMoney() {
		System.out.println("구입 금액을 입력해 주세요.");
		try {
			return scanner.nextInt();
		} catch (InputMismatchException e) {
			return inputPurchaseMoney();
		}
	}

	
}
