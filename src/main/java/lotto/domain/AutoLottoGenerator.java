package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.LottoNumber.*;

public class AutoLottoGenerator implements LottoGenerator {
    private static final List<LottoNumber> NUMBERS;

    static {
        NUMBERS = new ArrayList<>();
        IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .forEachOrdered(value -> NUMBERS.add(LottoNumber.generateNumber(value)));
    }

    @Override
    public Lotto makeLotto() {
        Collections.shuffle(NUMBERS);
        return new Lotto(NUMBERS.stream()
                .limit(LOTTO_SIZE)
                .collect(Collectors.toSet()));
    }
}