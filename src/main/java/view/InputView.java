package view;

import java.util.Scanner;

public class InputView {

	private static final String BLANK = " ";
	private static final String COMMA = ",";
	private static final String EMPTY_STRING = "";
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
		return userInput.replaceAll(BLANK, EMPTY_STRING).split(COMMA);
	}

	public String requestBonusNumber() {
		return readLine();
	}
}
