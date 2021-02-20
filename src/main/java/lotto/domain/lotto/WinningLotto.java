package lotto.domain.lotto;

import lotto.domain.reword.Reword;
import lotto.exception.DuplicateLottoNumberException;

import java.util.List;

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

    public Reword matchAll(final Lotto lotto) {
        int count = match(lotto);

        return Reword.valueOf(count, lotto.isContainsNumber(bonusNumber));
    }
}
