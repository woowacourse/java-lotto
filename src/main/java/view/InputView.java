package view;

import java.util.Scanner;

public class InputView {

	private final Scanner scanner = new Scanner(System.in);

	private String readLine() {
		return scanner.nextLine();
	}

	public String requestMoney() {
		return readLine();
	}

	public String[] requestWinningNumbers() {
		return removeBlankAndSplitByComma(readLine());
	}

	private String[] removeBlankAndSplitByComma(String userInput) {
		return userInput.replaceAll(" ", "").split(",");
	}

	public String requestBonusNumber() {
		return readLine();
	}
}
