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

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static int inputMoney() throws IOException {
		return Integer.parseInt(bufferedReader.readLine());
	}

	public static int inputManualLottoCount() throws IOException {
		return Integer.parseInt(bufferedReader.readLine());
	}

	public static List<String> inputWinningNumber() throws IOException {
		return Arrays.asList(bufferedReader.readLine()
			.replace(SPACE, EMPTY)
			.split(SPLIT_DELIMITER, SPLIT_LIMIT));
	}

	public static int inputBonusNumber() throws IOException {
		return Integer.parseInt(bufferedReader.readLine());
	}

	public static List<String> inputManualLottoNumbers() throws IOException {
		return Arrays.asList(bufferedReader.readLine()
			.replace(SPACE, EMPTY)
			.split(SPLIT_DELIMITER, SPLIT_LIMIT));
	}
}
