package domain;

import java.util.Comparator;
import java.util.List;

public class Numbers {
    private final List<Integer> numbers;

    public Numbers(List<Integer> numbers) {
        sortingNumbers(numbers);
        this.numbers = numbers;
    }

    private void sortingNumbers(List<Integer> numbers) {
        numbers.sort(Comparator.naturalOrder());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
