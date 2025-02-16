package domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted(Comparator.comparingInt(LottoNumber::getValue))
                .toList();
    }

    public static Lotto from(List<LottoNumber> numbers) {
        return new Lotto(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDistinct(numbers);
    }

    private static void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LottoRule.LOTTO_SELECTION_SIZE.getValue()) {
            throw new IllegalArgumentException(
                    "로또 번호는 " + LottoRule.LOTTO_SELECTION_SIZE.getValue() + "개여야 합니다. "
                            + "입력된 개수: " + numbers.size());
        }
    }

    private void validateDistinct(List<LottoNumber> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != LottoRule.LOTTO_SELECTION_SIZE.getValue()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다. 입력된 번호: " + numbers);
        }
    }

    @Override
    public String toString() {
        return "[" + numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")) + "]";
    }
}
