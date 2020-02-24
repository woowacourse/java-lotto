package lotto.util;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
	public static final String BLANK = " ";
	public static final String NO_BLANK = "";
	public static final String DELIMITER = ",";

	public static String removeBlank(String value) {
		return value.replaceAll(BLANK, NO_BLANK);
	}

	public static List<String> parseByComma(String value) {
		return Arrays.asList(value.split(DELIMITER));
	}
}
