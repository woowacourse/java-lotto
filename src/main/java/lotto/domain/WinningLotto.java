package lotto.domain;

import java.util.List;
import java.util.Objects;
import lotto.exception.DuplicateLottoNumberException;

public class WinningLotto extends Lotto {

    private final LottoNumber bonusNumber;

    public WinningLotto(final List<Integer> numbers, final int bonusNumber) {
        super(numbers);
        validateBonusNumber(numbers, bonusNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateBonusNumber(final List<Integer> values, int value) {
        if (values.contains(value)) {
            throw new DuplicateLottoNumberException();
        }
    }

    public Reword match(final Lotto lotto) {
        int count = (int) lottoNumbers.stream()
            .filter(lotto::isContainsNumber)
            .count();

        return Reword.valueOf(count, lotto.isContainsNumber(bonusNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        WinningLotto that = (WinningLotto) o;
        return bonusNumber == that.bonusNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonusNumber);
    }
}
