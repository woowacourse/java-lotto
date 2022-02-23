package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine {

    private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>();

    static {
        IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::valueOf)
                .forEach(LOTTO_NUMBERS::add);
    }

    private RandomLottoMachine() {
    }

    public static List<LottoNumber> createRandomLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS);
        return IntStream.rangeClosed(0, 5)
                .boxed()
                .map(LOTTO_NUMBERS::get)
                .sorted()
                .collect(Collectors.toList());
    }
}
