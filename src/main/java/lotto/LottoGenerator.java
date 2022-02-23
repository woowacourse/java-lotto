package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final int START_INCLUSIVE = 0;
    private static final int END_EXCLUSIVE = 6;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    private final List<Number> numbers;

    public LottoGenerator() {
        this.numbers = initNumbers();
    }

    public Lotto generate() {
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(START_INCLUSIVE, END_EXCLUSIVE));
    }

    private List<Number> initNumbers() {
        return IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
            .mapToObj(Number::new)
            .collect(Collectors.toList());
    }
}
