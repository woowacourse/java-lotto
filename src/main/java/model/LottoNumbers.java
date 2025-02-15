package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {

    private static final int VALID_SIZE_OF_NUMBERS = 6;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;

    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public boolean contains(int comparedValue) {
        return numbers.contains(comparedValue);
    }

    private void validateNumbers(List<Integer> numbers) {
        numbers.forEach(this::validateRange);
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateRange(int number) {
        if (number < LOWER_BOUND || number > UPPER_BOUND) {
            throw new IllegalArgumentException("[ERROR] 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != VALID_SIZE_OF_NUMBERS) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되어서는 안됩니다.");
        }
    }
}
