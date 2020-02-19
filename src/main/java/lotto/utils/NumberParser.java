package lotto.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.exceptions.NotNumberException;

public class NumberParser {
	private static final String DELIMITER = ",";

	public static List<Integer> winningNumberParse(String input) {
		return Stream.of(input.split(DELIMITER))
			.map(String::trim)
			.map(NumberParser::parseNumber)
			.collect(Collectors.toList());
	}

	public static int parseNumber(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new NotNumberException("숫자만 입력하세요.");
		}
	}
}
