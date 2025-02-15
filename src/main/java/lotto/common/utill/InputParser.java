package lotto.common.utill;

import static lotto.common.constant.ErrorMessage.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputParser {
    private static final String SEPARATOR = ",";

    private InputParser() {
    }

    public static int parseToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER_TYPE.getMessage());
        }
    }

    public static List<Integer> parseToList(String str) {
        String[] split = str.split(SEPARATOR);
        return Arrays.stream(split)
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

}
