package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.LottoNumberConst.*;

public class RandomLottoNumberStrategy implements LottoNumberStrategy {

    private static final List<LottoNumber> numbers;

    static {
        numbers = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .mapToObj(LottoNumber::create)
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(6)
                .collect(Collectors.toList());
    }
}
