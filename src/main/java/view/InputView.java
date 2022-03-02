package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

	private static final String BLANK = " ";
	private static final String COMMA = ",";
	private static final String EMPTY_STRING = "";
	private static final String MANUAL_LOTTO_COUNT_IS_NOT_DIGIT_MESSAGE = "수동 로또의 개수는 숫자를 입력해야 합니다";
	private static final String MONEY_IS_NOT_DIGIT_MESSAGE = "Money 는 숫자를 입력해야 합니다";
	private static final String BONUS_BALL_IS_NOT_DIGIT_MESSAGE = "보너스 볼은 숫자를 입력해야 합니다";
	private static final String MANUAL_LOTTO_NUMBER_IS_NOT_DIGIT_MESSAGE = "수동 로또의 번호는 숫자를 입력해야합니다";
	private static final String WINNING_NUMBER_IS_NOT_DIGIT_MESSAGE = "당첨 번호는 숫자를 입력해야 합니다";
	private final Scanner scanner = new Scanner(System.in);

	private String readLine() {
		return scanner.nextLine();
	}

	public int requestMoney() {
		return inputNumberFormat(MONEY_IS_NOT_DIGIT_MESSAGE);
	}

	public int requestManualLottoCount() {
		return inputNumberFormat(MANUAL_LOTTO_COUNT_IS_NOT_DIGIT_MESSAGE);
	}

	public List<List<Integer>> requestManualLotto(int manualLottoCount) {
		List<List<Integer>> manualLotto = new ArrayList<>();
		for (int lottoIndex = 0; lottoIndex < manualLottoCount; lottoIndex++) {
			manualLotto.add(inputIntegerListFormat(MANUAL_LOTTO_NUMBER_IS_NOT_DIGIT_MESSAGE));
		}
		return manualLotto;
	}

	public List<Integer> requestWinningNumbers() {
		return inputIntegerListFormat(WINNING_NUMBER_IS_NOT_DIGIT_MESSAGE);
	}

	public int requestBonusNumber() {
		return inputNumberFormat(BONUS_BALL_IS_NOT_DIGIT_MESSAGE);
	}

	private int inputNumberFormat(String message) {
		try {
			return Integer.parseInt(readLine());
		} catch (Exception exception) {
			throw new NumberFormatException(message);
		}
	}

	private List<Integer> inputIntegerListFormat(String message) {
		return Arrays.stream(removeBlankAndSplitByComma())
			.map(string -> inputNumberFormat(message))
			.collect(Collectors.toList());
	}

	private String[] removeBlankAndSplitByComma() {
		return readLine().replaceAll(BLANK, EMPTY_STRING).split(COMMA);
	}
}
