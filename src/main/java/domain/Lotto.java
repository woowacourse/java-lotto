package domain;

import static java.util.Collections.unmodifiableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int NUMBERS_SIZE = 6;

    private final Set<Number> numbers;

    public Lotto(Set<Number> numbers) {
        validateNumberSize(numbers);
        this.numbers = numbers;
    }

    public Lotto(List<Number> numbers) {
        this(new HashSet<>(numbers));
        validateNonDuplicatedNumbers(numbers);
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

    public Set<Number> getNumbers() {
        return unmodifiableSet(numbers);
    }

    public int calculateMatchCount(Lotto purchaseLotto) {
        int matchCount = 0;
        for (Number purchaseNumber : purchaseLotto.numbers) {
            if (numbers.contains(purchaseNumber)) {
                matchCount++;
            }
        }
        return matchCount;
    }
}
