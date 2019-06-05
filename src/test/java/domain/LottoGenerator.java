package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGenerator {
    public static Lotto from(List<Integer> integers) {
        List<Number> numbers = integers.stream().map(i -> Number.from(i)).collect(Collectors.toList());
        return new Lotto(numbers);
    }
}
