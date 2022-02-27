package lotto.model;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableSet;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import lotto.model.exception.DuplicatedNumberException;
import lotto.model.exception.InvalidLottoSizeException;

public class Lotto {

    static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> numbers;

    private Lotto(Set<Integer> numbers) {
        if (isInvalidSize(numbers)) {
            throw new InvalidLottoSizeException();
        }

        this.numbers = numbers.stream()
            .map(LottoNumber::new)
            .collect(toUnmodifiableSet());
    }

    private boolean isInvalidSize(Set<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    public boolean contains(LottoNumber number) {
        return numbers.stream()
            .anyMatch(lottoNumber -> lottoNumber.equals(number));
    }

    public int getMatchedCount(Lotto otherLotto) {
        return (int) this.numbers.stream()
            .filter(number -> otherLotto.contains(number))
            .count();
    }

    public List<Integer> getIntValues() {
        return numbers.stream()
            .map(LottoNumber::getIntValue)
            .collect(toList());
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

    public static Lotto create(List<Integer> numbers) {
        if (hasDuplicatedNumber(numbers)) {
            throw new DuplicatedNumberException();
        }
        Set<Integer> distinctNumbers = numbers.stream().collect(toUnmodifiableSet());
        return new Lotto(distinctNumbers);
    }

    private static boolean hasDuplicatedNumber(List<Integer> numbers) {
        return getDistinctSize(numbers) != numbers.size();
    }

    private static long getDistinctSize(List<Integer> numbers) {
        return numbers.stream()
            .distinct()
            .count();
    }
}
