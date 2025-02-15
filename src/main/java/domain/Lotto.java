package domain;

import java.util.Comparator;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        numbers.sort(Comparator.naturalOrder());
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateDistinct(numbers);
        validateSize(numbers);
    }

    private void validateDistinct(List<Integer> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != LottoRule.LOTTO_SELECTION_SIZE.getValue()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다. 입력된 번호: " + numbers);
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoRule.LOTTO_SELECTION_SIZE.getValue()) {
            throw new IllegalArgumentException(
                    "로또 번호는 " + LottoRule.LOTTO_SELECTION_SIZE.getValue() + "개여야 합니다. "
                            + "입력된 개수: " + numbers.size());
        }
    }

    @Override
    public String toString() {
        return "[" + String.join(", ", numbers.stream().map(String::valueOf).toList()) + "]";
    }
}
