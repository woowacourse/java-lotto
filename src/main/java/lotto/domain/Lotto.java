package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(final Set<Integer> values) {
        validateSize(values);
        this.numbers = makeNumber(values);
    }

    public int countMatchingLottoNumber(final Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    boolean contains(final LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    private void validateSize(final Set<Integer> values) {
        if (values.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않은 6개의 숫자여야 합니다.");
        }
    }

    private Set<LottoNumber> makeNumber(final Set<Integer> values) {
        return values.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
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

    public List<Integer> getSortedNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .toList();
    }
}
