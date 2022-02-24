package lotto.model;

import lotto.exception.DuplicatedNumberException;
import lotto.exception.InvalidLottoSizeException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    static final int LOTTO_SIZE = 6;

    private final Set<Number> numbers;

    public Lotto(List<Integer> numbers) {
        checkNumbers(numbers);
        this.numbers = numbers.stream()
            .map(Number::new)
            .collect(Collectors.toSet());
    }

    private void checkNumbers(List<Integer> numbers) {
        if (hasDuplicatedNumber(numbers)) {
            throw new DuplicatedNumberException();
        }

        if (isInvalidSize(numbers)) {
            throw new InvalidLottoSizeException();
        }
    }

    private boolean hasDuplicatedNumber(List<Integer> numbers) {
        return getDistinctSize(numbers) != numbers.size();
    }

    private boolean isInvalidSize(List<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private long getDistinctSize(List<Integer> numbers) {
        return numbers.stream()
            .distinct()
            .count();
    }

    public boolean contains(Number number) {
        return numbers.stream()
            .anyMatch(lottoNumber -> lottoNumber.equals(number));
    }

    public int getMatchedCount(Lotto otherLotto) {
        return (int) this.numbers.stream()
            .filter(number -> otherLotto.contains(number))
            .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto that = (Lotto) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    public List<Integer> getIntValues() {
        return numbers.stream()
            .map(Number::getIntValue)
            .collect(Collectors.toList());
    }
}
