package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public boolean isUnique(List<Integer> targetNumbers) {
        return new HashSet<>(numbers).containsAll(targetNumbers);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validate(List<Integer> numbers) {
        if(numbers.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 한 개는 여섯개의 숫자로 이루어져 있어야 합니다.");
        }
        if(isNotValidRange(numbers)) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이의 숫자여야 합니다.");
        }
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("로또의 모든 번호는 서로 중복되지 않아야 합니다.");
        }
    }

    private boolean isNotValidRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
                return true;
            }
        }
        return false;
    }
}
