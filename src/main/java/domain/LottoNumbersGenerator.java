package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbersGenerator {

    private static final List<LottoNumber> numbers;

    static {
        numbers = IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
    }

    public static List<LottoNumber> generate() {
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(6)
                .collect(Collectors.toList());
    }
}
