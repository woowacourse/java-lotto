package lotto.model.lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class LottoNumbers {
    public static final int MIN = 1;
    public static final int MAX = 45;
    private static final Set<Integer> numbers = new HashSet<>();

    static {
        IntStream.range(MIN, MAX)
                .forEach(numbers::add);
    }

    private LottoNumbers() {
    }

    static Set<Integer> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }
}
