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

    public static void validateRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException("숫자는 1~45 사이의 정수로 입력해주세요.");
        }
    }

    public boolean isSameWith(List<Integer> targetNumbers) {
        if(targetNumbers.size() != LottoConstants.NUMBER_COUNT) {
            return false;
        }
        return new HashSet<>(numbers).containsAll(targetNumbers);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 한 개는 여섯개의 숫자로 이루어져 있어야 합니다.");
        }
        validateRange(numbers);
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("로또의 모든 번호는 서로 중복되지 않아야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        numbers.forEach(Lotto::validateRange);
    }
}
