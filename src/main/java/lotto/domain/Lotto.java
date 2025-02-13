package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> values) {
        this.numbers = makeNumber(values);
    }

    boolean isDuplicateNumber(final LottoNumber inputNumber) {
        return numbers.stream()
                .anyMatch(number -> number.equals(inputNumber));
    }

    private List<LottoNumber> makeNumber(final List<Integer> values) {
        return values.stream()
                .map(LottoNumber::new)
                .toList();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Lotto lotto)) {
            return false;
        }
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }
}
