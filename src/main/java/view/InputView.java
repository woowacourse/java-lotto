package view;

import java.util.Scanner;

public class InputView {

	private static final String DELIMITER = ",";
	private static final String SPACE = " ";
	private static final String BLANK = "";
	private final Scanner scanner = new Scanner(System.in);

	private String readLine() {
		return scanner.nextLine();
	}

	public String[] requestWinningNumbers() {
		return removeBlankAndSplitByComma(readLine());
	}

	private String[] removeBlankAndSplitByComma(String userInput) {
		return userInput.replaceAll(SPACE, BLANK).split(DELIMITER);
	}

	public String requestMoney() {
		return readLine();
	}

	public String requestBonusNumber() {
		return readLine();
	}
}
