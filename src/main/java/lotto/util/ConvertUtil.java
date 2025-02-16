package lotto.util;

import java.util.Arrays;
import java.util.List;

import static lotto.constant.ErrorMessage.NOT_NUMBER;

public class ConvertUtil {
    public static final String DELIMITER = ",";

    private ConvertUtil() {}

    public static int convertToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER.getMessage());
        }
    }

    public static List<Integer> convertToList(String winningNumbers) {
        String[] split = winningNumbers.split(DELIMITER);
        return Arrays.stream(split)
            .map(ConvertUtil::convertToNumber)
            .toList();
    }
}
