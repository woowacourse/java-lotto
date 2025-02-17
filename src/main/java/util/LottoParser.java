package util;

import java.util.Arrays;
import java.util.List;
import model.LottoNumber;

public class LottoParser {

    private static final String DELIMITER_COMMA = ", ";

    public static List<String> splitByDelimiter(String rawInput) {
        String[] splittedInput = rawInput.split(DELIMITER_COMMA);
        return Arrays.stream(splittedInput).toList();
    }

    public static LottoNumber parseNumber(String rawNumber) {
        return new LottoNumber(parseInt(rawNumber));
    }

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
    }
}
