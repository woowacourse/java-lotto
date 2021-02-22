package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGenerator {

    private static final int MAX_BOUND = 45;
    private static final int BASE_BOUND = 1;
    private static final int LOTTO_SIZE = 6;
    private static final Random RANDOM = new Random();

    private LottoGenerator() {
    }

    public static Lotto generate() {
        return new Lotto(createRandomNumber());
    }

    private static List<LottoNumber> createRandomNumber() {
        return Stream.generate(() -> RANDOM
                .nextInt(MAX_BOUND) + BASE_BOUND)
                .distinct()
                .limit(LOTTO_SIZE)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    public static Lotto generate(List<Integer> numbers) {
        return new Lotto(LottoNumber.asList(numbers));
    }
}
