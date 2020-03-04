package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringParser {
	private static final String BLANK = "";
	private static final String SPACE = " ";
	private static final String COMMA_DELIMITER = ",";

	public static List<Integer> parseIntegerList(String input) {
		return Arrays.stream(input.replace(SPACE, BLANK)
				.split(COMMA_DELIMITER))
				.map(Integer::parseInt)
				.collect(Collectors.toUnmodifiableList());
	}
}
