package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private List<Integer> numbers;

    public Lotto(ArrayList<Integer> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    private void validate(ArrayList<Integer> numbers) {
        validateNumbersCount(numbers);
        validateNumbersRange(numbers);
        validateNumbersDuplication(numbers);
    }

    private void validateNumbersCount(ArrayList<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또의 숫자는 6개여야 합니다.");
        }
    }

    private void validateNumbersRange(ArrayList<Integer> numbers) {
        if (getOutOfRangeCount(numbers) > 0) {
            throw new IllegalArgumentException("로또의 숫자는 1~45이어야 합니다.");
        }
    }

    private long getOutOfRangeCount(ArrayList<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number < 1)
                .filter(number -> number > 45)
                .count();
    }

    private void validateNumbersDuplication(ArrayList<Integer> numbers) {
        if (getDistinctCount(numbers) != numbers.size()) {
            throw new IllegalArgumentException("로또의 숫자는 중복될 수 없습니다.");
        }
    }

    private long getDistinctCount(ArrayList<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}