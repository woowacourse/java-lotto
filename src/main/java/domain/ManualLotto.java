package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLotto {

    private final List<Integer> numbers;

    public ManualLotto(List<Integer> numbers) {
        this.numbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
