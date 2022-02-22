package model;

import java.util.Arrays;
import java.util.List;

public class LottoWinningNumber {

    public LottoWinningNumber(String numbers) {
        validateInputNumbersBlank(numbers);
    }

    private void validateInputNumbersBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public static List<String> split(String numbers) {
        return Arrays.asList(numbers.replace(" ", "").split(","));
    }
}
