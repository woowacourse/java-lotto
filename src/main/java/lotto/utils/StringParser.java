package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringParser {
	public static List<Integer> stringToIntegerList(String input) throws NumberFormatException {
		return Arrays.stream(input.replace(" ", "")
				.split(","))
				.map(Integer::valueOf)
				.collect(Collectors.toUnmodifiableList());
	}
}
