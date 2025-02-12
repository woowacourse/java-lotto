package src.model.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generate(int min, int max, int size) {
        List<Integer> numbers = new ArrayList<>(max - min + 1);
        for (int i = min; i <= max; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        return numbers.subList(0, size);
    }
}
