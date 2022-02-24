package view;

import java.util.Scanner;

public class InputView {

	public static final String DELIMITER = ",";
	public static final String SPACE = " ";
	public static final String BLANK = "";
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
		return userInput.replaceAll(SPACE, BLANK).split(DELIMITER);
	}

	public String requestBonusNumber() {
		return readLine();
	}
}
