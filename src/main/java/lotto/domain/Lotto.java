package lotto.domain;

import lotto.constant.ErrorMessage;

import java.util.List;
import java.util.Objects;

public class Lotto {

    static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> values) {
        validate(values);
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

    private void validate(final List<Integer> values) {
        validateDuplicated(values);
        validateSize(values);
    }

    private void validateSize(final List<Integer> values) {
        if (values.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_COUNT_IS_UNVALID);
        }
    }

    private void validateDuplicated(final List<Integer> values) {
        boolean isDuplicated = values.stream()
                .distinct()
                .count() != values.size();
        if (isDuplicated) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_IS_DUPLICATED);
        }
    }

    private List<LottoNumber> makeNumber(final List<Integer> values) {
        return values.stream()
                .map(LottoNumber::new)
                .toList();
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::number)
                .toList();
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
}
