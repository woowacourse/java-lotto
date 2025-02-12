package domain;

import static java.util.Collections.unmodifiableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int NUMBERS_SIZE = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;


    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        validateNumberSize(numbers);
        validateNumberRange(numbers);
        this.numbers = numbers;
    }

    public Lotto(List<Integer> numbers) {
        this(new HashSet<>(numbers));
        validateNonDuplicatedNumbers(numbers);
    }

    private void validateNumberRange(Collection<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException("당첨번호는 " + MIN_NUMBER + " 이상 " + MAX_NUMBER + "이하여야 합니다.");
            }
        }
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

    public Set<Integer> getNumbers() {
        return unmodifiableSet(numbers);
    }
}
