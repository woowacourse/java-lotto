package com.woowacourse.lotto.utils;

import java.util.Arrays;
import java.util.List;

public class StringSeparator {
	private static final String LOTTO_NUMBER_SEPARATOR = ",";

	public static List<String> splitString(String s) {
		return Arrays.asList(s.split(LOTTO_NUMBER_SEPARATOR));
	}
}
