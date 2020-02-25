package lotto.util;

import lotto.exception.EmptyInputException;
import lotto.exception.InvalidRangeException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

	public static void checkBlank(String input) {
		if(input.trim().isEmpty()){
			throw new EmptyInputException("공백은 사용할 수 없습니다.");
		}
	}

	public static void checkNull(Object o) {
		if(Objects.isNull(o)){
			throw new NullPointerException("널문자는 사용할 수 없습니다.");
		}
	}

	public static void checkNumberFormat(String input) {
		try{
			Integer.parseInt(input);
		} catch(NumberFormatException e){
			throw new NumberFormatException("문자열은 사용할 수 없습니다.");
		}
	}

	public static void checkRange(String input) {
		if(Integer.parseInt(input) < 0)
			throw new InvalidRangeException("음수는 입력할 수 없습니다.");
	}
}
