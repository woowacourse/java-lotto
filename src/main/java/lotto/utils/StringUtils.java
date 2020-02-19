package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
	private static String COMMA = ",";

	public static List<Integer> split(String input) {
		String[] inputs = input.split(COMMA);
		return Arrays.stream(inputs)
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}
}
