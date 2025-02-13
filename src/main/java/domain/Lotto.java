package domain;

import static java.util.Collections.unmodifiableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {

    public static final int NUMBERS_SIZE = 6;

    private final Set<Number> numbers;

    public Lotto(Set<Number> numbers) {
        validateNumberSize(numbers);
        this.numbers = numbers;
    }

    public Lotto(List<Number> numbers) {
        validateNumberSize(numbers);
        validateNonDuplicatedNumbers(numbers);
        this.numbers = new HashSet<>(numbers);
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    private void validateNumberSize(Collection<Number> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + NUMBERS_SIZE + "개여야 합니다.");
        }
    }

    private void validateNonDuplicatedNumbers(List<Number> numbers) {
        Set<Number> nonDuplicatedNumbers = new HashSet<>(numbers);
        if (nonDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public int calculateMatchCount(Lotto lotto) {
        int matchCount = 0;
        for (Number number : lotto.numbers) {
            if (numbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public Set<Number> getNumbers() {
        return unmodifiableSet(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Lotto lotto = (Lotto) o;
        return Objects.equals(getNumbers(), lotto.getNumbers());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNumbers());
    }
}
