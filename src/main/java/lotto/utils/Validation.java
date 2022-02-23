package lotto.utils;

import java.util.List;
import java.util.Set;

public class Validation {

    public static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 중복된 숫자가 존재합니다.";

    public static void checkDuplicateNumber(final List<Integer> numbers) {
        if (numbers.size() != Set.copyOf(numbers).size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public static void checkInputLottoWinningNumbers(String numbers) {

    }
}
