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
        validateRange(numbers);
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

    private static void validateRange(List<Integer> numbers) {
        boolean isValidRange = numbers.stream()
                .allMatch(LottoRule::isLottoRange);

        if (isValidRange) {
            return;
        }
        throw new IllegalArgumentException("로또 번호는 "
                + LottoRule.MIN_LOTTO_NUMBER.getValue() + "부터 "
                + LottoRule.MAX_LOTTO_NUMBER.getValue() + " 사이의 숫자여야 합니다. "
                + "입력된 번호: " + numbers);
    }

    @Override
    public String toString() {
        return "[" + String.join(", ", numbers.stream().map(String::valueOf).toList()) + "]";
    }
}
