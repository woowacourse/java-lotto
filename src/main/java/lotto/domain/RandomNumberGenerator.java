package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {

    private final int min;
    private final int max;

    public RandomNumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public List<Integer> generate(int size) {
        List<Integer> numbers = IntStream.range(min, max + 1)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(numbers);
        List<Integer> randomNumbers = numbers.subList(0, size);
        Collections.sort(randomNumbers);
        return randomNumbers;
    }
}
