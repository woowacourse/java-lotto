package lotto;

import java.util.Comparator;
import java.util.List;

public class Lotto {
    private static final int FIRST_INDEX = 0;
    private static final int LAST_INDEX = 5;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final String NOT_DISTINCT_NUMBERS_ERROR_MSG = "중복되는 로또 번호가 존재합니다.";
    private static final String NOT_IN_SCOPE_NUMBERS_ERROR_MSG = "로또 번호의 범위가 잘못되었습니다.";

    public static void validateDistinctNumbers(List<Integer> inputNumbers) {
        if (inputNumbers.stream().distinct().count() != inputNumbers.size()) {
            throw new IllegalArgumentException(NOT_DISTINCT_NUMBERS_ERROR_MSG);
        }
    }

    public static void validateNumberScope(List<Integer> invalidNumbersUnderScope) {
        invalidNumbersUnderScope.sort(Integer::compareTo);
        if(invalidNumbersUnderScope.get(FIRST_INDEX) < MIN_LOTTO_NUMBER
                || invalidNumbersUnderScope.get(LAST_INDEX) > MAX_LOTTO_NUMBER ){
            throw new IllegalArgumentException(NOT_IN_SCOPE_NUMBERS_ERROR_MSG);
        }
    }
}
