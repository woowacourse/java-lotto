package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoNo;
import lotto.exception.NotIntegerException;

public class LottoNoUtils {
	private static String COMMA = ",";

	public static List<LottoNo> split(String input) {
		String[] inputs = input.split(COMMA);
		try {
			return Arrays.stream(inputs)
				.map(String::trim)
				.map(LottoNo::new)
				.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new NotIntegerException();
		}
	}
}
