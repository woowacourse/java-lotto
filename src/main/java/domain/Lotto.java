package domain;

import java.util.List;

import static domain.LottoInformation.LOTTO_COUNT;

public class Lotto {

    private final Numbers numbers;

    public Lotto(Numbers numbers) {
        validate(numbers.getNumbers());
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }

    private void validate(List<Integer> numbers) {
        validateDistinct(numbers);
        validateSize(numbers);
    }

    private void validateDistinct(List<Integer> numbers) {
        if (numbers.stream().distinct().count() == LOTTO_COUNT) {
            return;
        }
        throw new IllegalArgumentException(String.format("로또 번호는 %d자리여야 합니다.", LOTTO_COUNT));
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() == LOTTO_COUNT) {
            return;
        }
        throw new IllegalArgumentException();
    }
}
