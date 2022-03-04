package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_LENGTH = 6;

    @Override
    public Lotto generateLotto() {
        List<LottoNumber> numbers = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return new Lotto(numbers.stream()
                .limit(LOTTO_LENGTH)
                .collect(Collectors.toSet()));
    }
}
