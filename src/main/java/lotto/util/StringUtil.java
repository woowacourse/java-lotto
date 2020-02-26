package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lotto.exception.EmptyInputException;

public class StringUtil {
	public static final String BLANK = " ";
	public static final String NO_BLANK = "";
	public static final String DELIMITER = ",";

	public static String removeBlank(String value) {
		return value.replaceAll(BLANK, NO_BLANK);
	}

	public static List<String> parseToNumbers(String value) {
		return Arrays.asList(value.split(DELIMITER));
	}

	public static void checkBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new EmptyInputException("공백은 사용할 수 없습니다.");
		}
	}

	public static void checkNull(Object o) {
		if (Objects.isNull(o)) {
			throw new NullPointerException("널은 입력되지 않습니다.");
		}
	}

	public static void checkNumberFormat(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("문자는 사용이 불가능합니다.");
		}
	}
}
