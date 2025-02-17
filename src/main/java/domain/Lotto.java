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

    private Lotto(Set<Number> numbers) {
        this.numbers = numbers;
    }

    public static Lotto createWinningLotto(List<Integer> number) {
        List<Number> numbers = number.stream().map(Number::new).toList();
        validateNumberSize(numbers);
        validateNonDuplicatedNumbers(numbers);
        return new Lotto(new HashSet<>(numbers));
    }

    public static Lotto createRandomLotto(LottoPickStrategy lottoPickStrategy) {
        Set<Number> numbers;
        do {
            numbers = new HashSet<>(selectNumbers(lottoPickStrategy));
        } while (numbers.size() != NUMBERS_SIZE);
        return new Lotto(numbers);
    }

    private static List<Number> selectNumbers(LottoPickStrategy lottoPickStrategy) {
        List<Integer> numbers = lottoPickStrategy.pickNumbers(NUMBERS_SIZE);
        return numbers.stream().map(Number::new).toList();
    }

    private static void validateNumberSize(Collection<Number> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + NUMBERS_SIZE + "개여야 합니다.");
        }
    }

    private static void validateNonDuplicatedNumbers(List<Number> numbers) {
        Set<Number> nonDuplicatedNumbers = new HashSet<>(numbers);
        if (nonDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    public int calculateMatchCount(Lotto lotto) {
        return (int) lotto.numbers.stream()
                .filter(numbers::contains)
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

        Lotto lotto = (Lotto) o;
        return Objects.equals(getNumbers(), lotto.getNumbers());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNumbers());
    }

    public Set<Number> getNumbers() {
        return unmodifiableSet(numbers);
    }
}
