package lotto.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputUtil {
	private static final int SPLIT_LIMIT = -1;
	private static final String SPLIT_DELIMITER = ",";
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	public static final String INPUT_MONEY = "구매 금액을 입력해 주세요.";
	public static final String INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
	public static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static int inputMoney() throws IOException {
		System.out.println(INPUT_MONEY);
		return Integer.parseInt(bufferedReader.readLine());
	}

	public static List<String> inputWinningNumber() throws IOException {
		System.out.println(INPUT_WINNING_NUMBER);
		return Arrays.asList(bufferedReader
				.readLine()
				.replace(SPACE, EMPTY)
				.split(SPLIT_DELIMITER, SPLIT_LIMIT));
	}

	public static int inputBonusNumber() throws IOException {
		System.out.println(INPUT_BONUS_NUMBER);
		return Integer.parseInt(bufferedReader.readLine());
	}
}
