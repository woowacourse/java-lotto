package lotto.common.utill;

import static lotto.common.constant.ErrorMessage.*;

import java.util.ArrayList;
import java.util.List;

public final class InputParser {
    private static final String REGULAR_EXPRESSION = "^[0-9]+$";
    private static final String SEPARATOR = ",";

    private InputParser() {
    }

    public static int parseToInt(String str) {
        validateNumeric(str);
        return Integer.parseInt(str);
    }

    private static void validateNumeric(String str) {
        if (!str.matches(REGULAR_EXPRESSION)) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER_TYPE.getMessage());
        }
    }

    public static List<Integer> parseToList(String str) {
        List<Integer> list = new ArrayList<>();
        String[] split = str.split(SEPARATOR);

        for (String s : split) {
            list.add(parseToInt(s.trim()));
        }

        return list;
    }

}
