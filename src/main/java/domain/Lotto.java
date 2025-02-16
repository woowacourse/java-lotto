package domain;

import static java.util.Collections.unmodifiableSet;

import domain.numbergenerator.NumberGenerator;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {

    private static final int NUMBERS_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(Set<LottoNumber> numbers) {
        validateLottoNumberSize(numbers);
        this.numbers = numbers;
    }

    public Lotto(List<LottoNumber> numbers) {
        validateLottoNumberSize(numbers);
        validateNonDuplicatedLottoNumbers(numbers);
        this.numbers = new HashSet<>(numbers);
    }

    public static Lotto create(NumberGenerator numberGenerator) {
        Set<LottoNumber> numbers = new HashSet<>();
        while (numbers.size() < NUMBERS_SIZE) {
            int number = numberGenerator.generate();
            numbers.add(new LottoNumber(number));
        }
        return new Lotto(numbers);
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public int calculateMatchCount(Lotto lotto) {
        return (int) lotto.numbers.stream()
                .filter(numbers::contains)
                .count();
    }

    public Set<LottoNumber> getNumbers() {
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

    private void validateLottoNumberSize(Collection<LottoNumber> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + NUMBERS_SIZE + "개여야 합니다.");
        }
    }

    private void validateNonDuplicatedLottoNumbers(List<LottoNumber> numbers) {
        Set<LottoNumber> nonDuplicatedNumbers = new HashSet<>(numbers);
        if (nonDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다.");
        }
    }
}
