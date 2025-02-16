package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> values) {
        validate(values);
        this.numbers = makeNumber(values);
    }

    private void validate(final List<Integer> values) {
        validateDuplication(values);
        validateSize(values);
    }

    private void validateSize(final List<Integer> values) {
        if (values.size() != RandomLottoGenerator.LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }
    }

    private void validateDuplication(final List<Integer> values) {
        if (hasDuplication(values)) {
            throw new IllegalArgumentException("중복되지 않은 로또 번호를 입력해 주세요.");
        }
    }

    public int countMatchingLottoNumber(final Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    boolean contains(final LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    private List<LottoNumber> makeNumber(final List<Integer> values) {
        return values.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private boolean hasDuplication(final List<Integer> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
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

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }
}
