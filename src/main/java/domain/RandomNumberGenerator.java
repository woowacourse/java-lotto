package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    private static final List<Integer> numbers = new ArrayList<>();

    static {
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
    }

    @Override
    public List<Integer> create() {
        Collections.shuffle(numbers);
        return numbers.subList(0,6);
    }
}
