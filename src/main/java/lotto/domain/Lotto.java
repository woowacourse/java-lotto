package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {

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

    boolean isDuplicateNumber(final LottoNumber inputNumber) {
        return numbers.stream()
                .anyMatch(number -> number.equals(inputNumber));
    }

    private void validate(final List<Integer> values) {
        validateDuplicated(values);
        validateSize(values);
    }

    private void validateSize(final List<Integer> values) {
        if (values.size() != LottoGenerator.LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }
    }

    private void validateDuplicated(final List<Integer> values) {
        boolean isDuplicated = values.stream()
                .distinct()
                .count() != values.size();
        if (isDuplicated) {
            throw new IllegalArgumentException("중복되지 않은 로또 번호를 입력해 주세요.");
        }
    }

    private List<LottoNumber> makeNumber(final List<Integer> values) {
        return values.stream()
                .map(LottoNumber::new)
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

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::number)
                .toList();
    }
}
