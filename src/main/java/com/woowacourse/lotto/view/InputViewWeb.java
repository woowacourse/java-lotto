package com.woowacourse.lotto.view;

import java.util.Arrays;
import java.util.List;

public class InputViewWeb {
	private static final String WHITE_SPACE = "\\r\\n";

	public static List<String> splitString(String manualLottos) {
		return Arrays.asList(manualLottos.split(WHITE_SPACE));
	}
}
