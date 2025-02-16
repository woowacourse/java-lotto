package lotto.dto;

import static lotto.constant.ErrorMessage.NOT_NUMBER;

public class ConvertUtil {
    private ConvertUtil() {
    }

    public static int convertToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER.getMessage());
        }
    }
}
