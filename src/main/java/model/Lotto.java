package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberDuplication(numbers);
        for (int number : numbers) {
            validateNumberRange(number);
        }
        this.numbers = numbers;
    }

    public boolean checkDuplication(List<Integer> targetNumbers) {
        return new HashSet<>(numbers).containsAll(targetNumbers);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalStateException("로또 번호의 개수는 6입니다.");
        }
    }

    private void validateNumberDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복이 허용되지 않습니다.");
        }
    }

    private void validateNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalStateException("로또 번호는 1~45 사이의 정수입니다.");
        }
    }
}
