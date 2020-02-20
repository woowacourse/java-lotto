package domain.lottonumber.generator;

import domain.lottonumber.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    private static final List<LottoNumber> numbers;

    static {
        numbers = IntStream.range(1, 46)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> create() {
        Collections.shuffle(numbers);
        return new ArrayList<>(numbers.subList(0, 6));
    }
}
