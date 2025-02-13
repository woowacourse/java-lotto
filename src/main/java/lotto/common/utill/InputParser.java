package lotto.common.utill;

import static lotto.common.exception.ErrorMessage.*;

import java.util.ArrayList;
import java.util.List;

public final class InputParser {
    private InputParser() {
    }

    public static int parseToInt(String str) {
        validateNumeric(str);
        return Integer.parseInt(str);
    }

    private static void validateNumeric(String str) {
        if (!str.matches("^[0-9]+$")) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }

    public static List<Integer> parseToList(String str) {
        List<Integer> list = new ArrayList<>();

        String[] split = str.split(",");
        for (String s : split) {
            list.add(parseToInt(s.trim()));
        }

        return list;
    }

}
