package lotto.model.lotto.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DefaultNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generate(int min, int max, int size) {
        List<Integer> numbers = new ArrayList<>(max - min + 1);
        IntStream.rangeClosed(min, max).forEach(numbers::add);
        Collections.shuffle(numbers);

        return numbers.subList(0, size);
    }
}
