package lotto.domain;

import java.util.Comparator;
import java.util.List;

public class Lotto {
    private static final String NOT_DISTINCT_NUMBERS_ERROR_MSG = "중복되는 로또 번호가 존재합니다.";
    List<LottoNumber> lottoNumbers;
    public static void validateDistinctNumbers(List<Integer> inputNumbers) {
        if (inputNumbers.stream().distinct().count() != inputNumbers.size()) {
            throw new IllegalArgumentException(NOT_DISTINCT_NUMBERS_ERROR_MSG);
        }
    }

}
