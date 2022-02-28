package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumbersGenerator implements LottoNumbersGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;

    @Override
    public Set<LottoNumber> generateNumbers() {
        List<LottoNumber> numbers = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LOTTO_LENGTH)
                .collect(Collectors.toSet());
    }
}
