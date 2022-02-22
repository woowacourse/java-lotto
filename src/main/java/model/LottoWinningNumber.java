package model;

import java.util.Arrays;
import java.util.List;

public class LottoWinningNumber {
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    public LottoWinningNumber(String numbers) {
        validateInputNumbersBlank(numbers);
        validateNumbersConsistOfInt(numbers);
        validateNumberOutOfRange(numbers);
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

    private void validateNumberOutOfRange(String numbers) {
        long count = split(numbers).stream()
                .filter(number -> Integer.parseInt(number) < LOTTO_MIN_NUMBER || Integer.parseInt(number) > LOTTO_MAX_NUMBER)
                .count();

        if (count > 0) {
            throw new IllegalArgumentException();
        }
    }
}
