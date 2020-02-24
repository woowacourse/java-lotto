package lotto.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberRandomGenerator implements LottoNumberGenerator {
    private static Random random;
    private static List<LottoNumber> totalRandomNumbers;

    static {
        random = new Random();
        totalRandomNumbers = IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public Set<LottoNumber> create() {
        Collections.shuffle(totalRandomNumbers);
        Set<LottoNumber> randomNumbers = new TreeSet<>();
        int length = Lotto.LOTTO_NUMBERS_SIZE;
        for (int i = 0; i < length; i++) {
            randomNumbers.add(totalRandomNumbers.get(i));
        }
        return randomNumbers;
    }
}
