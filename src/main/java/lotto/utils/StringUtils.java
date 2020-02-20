package lotto.utils;

import lotto.domain.LottoNo;
import lotto.validator.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
	private static String COMMA = ",";

	public static List<LottoNo> toLottoNoList(String input) {
		try {
			return Arrays.stream(input.split(COMMA))
					.map(Integer::parseInt)
					.map(LottoNo::new)
					.collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(Validator.ERROR_MESSAGE_NOT_INTEGER);
		}
	}
}
