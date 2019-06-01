package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumbersGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int SUBLIST_LOWER_BOUND = 0;
    private static final int SUBLIST_UPPER_BOUND = 6;
    private static final List<LottoNumber> totalLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            totalLottoNumbers.add(LottoNumber.valueOf(i));
        }
    }

    private LottoNumbersGenerator() {
        throw new AssertionError();
    }

    public static LottoNumber getLottoNumber(int number) {
        return LottoNumber.valueOf(number);
    }

    public static LottoNumbers getLottoNumbers() {
        Collections.shuffle(totalLottoNumbers, new Random());
        return new LottoNumbers(totalLottoNumbers.subList(SUBLIST_LOWER_BOUND, SUBLIST_UPPER_BOUND));
    }

    public static LottoNumbers getLottoNumbers(List<Integer> numbers) {
        return new LottoNumbers(numbers.stream()
                .map(LottoNumbersGenerator::getLottoNumber)
                .collect(Collectors.toList()));
    }
}
