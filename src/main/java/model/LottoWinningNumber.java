package model;

import java.util.Arrays;
import java.util.List;

public class LottoWinningNumber {
    private static final String REGEX_NUMBER = "[0-9]+";

    public LottoWinningNumber(String numbers) {
        validateInputNumbersBlank(numbers);
        validateNumbersConsistOfInt(numbers);
    }

    private void validateInputNumbersBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public static List<String> split(String numbers) {
        return Arrays.asList(numbers.replace(" ", "").split(","));
    }

    private void validateNumbersConsistOfInt(String numbers) {
        String joinNumbers = String.join("", split(numbers));
        if (!joinNumbers.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException();
        }
    }
}
