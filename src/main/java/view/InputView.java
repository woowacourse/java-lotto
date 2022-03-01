package view;

import java.util.Scanner;

public class InputView {

	private static final String BLANK = " ";
	private static final String COMMA = ",";
	private static final String EMPTY_STRING = "";
	private static final String NUMBER_IS_NOT_DIGIT_MESSAGE = "숫자를 입력해야 합니다";
	private final Scanner scanner = new Scanner(System.in);

	private String readLine() {
		return scanner.nextLine();
	}

	public String requestMoney() {
		return readLine();
	}

	public int requestManualLottoCount() {
		try {
			return Integer.parseInt(readLine());
		} catch (Exception exception) {
			throw new NumberFormatException(NUMBER_IS_NOT_DIGIT_MESSAGE);
		}
	}

	public String[][] requestManualLotto(int manualLottoCount) {
		String[][] manualLotto = new String[manualLottoCount][];
		for (int lottoIndex = 0; lottoIndex < manualLottoCount; lottoIndex++) {
			manualLotto[lottoIndex] = removeBlankAndSplitByComma(readLine());
		}
		return manualLotto;
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
