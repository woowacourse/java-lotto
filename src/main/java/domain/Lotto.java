package domain;

import static java.util.Collections.unmodifiableSet;

import domain.lottogeneratestrategy.LottoPickStrategy;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {

    private static final int NUMBERS_SIZE = 6;

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

    public static LottoMachine createLottoMachine(LottoPickStrategy strategy) {
        return new LottoMachine(strategy, NUMBERS_SIZE);
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    public boolean hasCorrectSize(Collection<Number> numbers) {
        return numbers.size() == NUMBERS_SIZE;
    }

    private void validateNumberSize(Collection<Number> numbers) {
        if (!hasCorrectSize(numbers)) {
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
        return (int) lotto.numbers.stream()
                .filter(numbers::contains)
                .count();
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
