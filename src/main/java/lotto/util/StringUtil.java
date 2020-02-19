package lotto.util;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
	public static String removeBlank(String value) {
		return value.replaceAll(" ","");
	}

	public static List<String> parseByComma(String value) {
		return Arrays.asList(value.split(","));
	}
}
