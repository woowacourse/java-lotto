package lotto.domain.lottomanager;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LottoNumberManager {
    private static final int LIMIT_MIN_NUM = 1;
    private static final int LIMIT_MAX_NUM = 45;

    private static List<LottoNumber> numbers;

    static {
        numbers = IntStream.rangeClosed(LIMIT_MIN_NUM, LIMIT_MAX_NUM)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    static List<LottoNumber> getNumbers() {
        return numbers;
    }

    static LottoNumber getMatchNumber(int number) {
        return numbers.stream()
                .filter(LottoNum -> LottoNum.isMatchNumber(number))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
