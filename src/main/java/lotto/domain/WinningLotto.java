package lotto.domain;

import java.util.List;
import java.util.Objects;
import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidLottoNumberException;

public class WinningLotto extends Lotto {

    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;

    private final int bonusNumber;

    public WinningLotto(final List<Integer> numbers, final int bonusNumber) {
        super(numbers);
        validate(numbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(final List<Integer> values, int value) {
        if (value < MIN_BOUND || value > MAX_BOUND) {
            throw new InvalidLottoNumberException();
        }
        if (values.contains(value)) {
            throw new DuplicateLottoNumberException();
        }
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
