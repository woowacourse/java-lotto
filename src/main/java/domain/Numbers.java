package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Numbers {

    private static final int NUMBER_COUNT = 6;

    private final List<Integer> numbers;

    private Numbers(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberNotDuplicated(numbers);
        this.numbers = numbers;
    }

    public static Numbers from(List<Integer> numbers) {
        return new Numbers(numbers);
    }

    public Numbers sortNumbers() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return Numbers.from(sortedNumbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개가 되어야 합니다.");
        }
    }


    private void validateNumberNotDuplicated(List<Integer> numbers) {
        HashSet<Integer> nonDuplicateNumbers = new HashSet<>(numbers);
        if (numbers.size() != nonDuplicateNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되면 안됩니다.");
        }
    }

    public boolean contains(Integer matchNumber) {
        return numbers.contains(matchNumber);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
