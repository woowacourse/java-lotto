package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberGenerator {
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final List<Number> generator;

    private NumberGenerator() {
        this.generator = new ArrayList<>();

        for (int i = MIN; i <= MAX; i++) {
            generator.add(Number.create(i));
        }

        Collections.shuffle(generator);
    }

    public static NumberGenerator create() {
        return new NumberGenerator();
    }

    public List<Number> getNumbers() {
        List<Number> numbers = generator.subList(0, 6);
        Collections.sort(numbers);
        return numbers;
    }
}
