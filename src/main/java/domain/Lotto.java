package domain;

import static java.util.Collections.unmodifiableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int NUMBERS_SIZE = 6;

    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        validateNumberSize(numbers);
        this.numbers = numbers;
    }

    public Lotto(List<Integer> numbers) {
        validateNumberSize(numbers);
        validateNonDuplicatedNumbers(numbers);
        this.numbers = new HashSet<>(numbers);
    }


    public Set<Integer> getNumbers() {
        return unmodifiableSet(numbers);
    }

    private void validateNumberSize(Collection<Integer> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNonDuplicatedNumbers(List<Integer> numbers) {
        Set<Integer> nonDuplicatedNumbers = new HashSet<>(numbers);
        if (nonDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }
}
