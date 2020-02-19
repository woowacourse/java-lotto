package lotto.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningNumberParser {
	public static List<Integer> parse(String input) {
		return Stream.of(input.split(","))
			.map(String::trim)
			.map(WinningNumberParser::parseNumber)
			.collect(Collectors.toList());
	}

	private static int parseNumber(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("숫자만 입력하세요.");
		}
	}
}
