package lotto.domain;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberGenerator {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final int START_POINT = 0;
    private static final int NUMBER_BOUND = 6;

    private final List<Number> generator;

    private NumberGenerator() {
        this.generator = new ArrayList<>();
        addNumbers();
    }

    public static NumberGenerator create() {
        return new NumberGenerator();
    }

    public List<Number> getNumbers() {
        Collections.shuffle(generator);
        List<Number> numbers = Lists.newCopyOnWriteArrayList(generator.subList(START_POINT, NUMBER_BOUND));
        Collections.sort(numbers);

        return numbers;
    }

    private void addNumbers() {
        for (int i = MIN; i <= MAX; i++) {
            generator.add(new Number(i));
        }
    }
}
