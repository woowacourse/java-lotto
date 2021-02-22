package lotto.domain;

import java.util.List;
import java.util.Objects;
import lotto.exception.DuplicateLottoNumberException;

public class WinningLotto {

    // todo - 컴포지션 사용하기
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final List<Integer> numbers, final int bonusNumber) {
        validateBonusNumber(numbers, bonusNumber);
        lotto = new Lotto(numbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateBonusNumber(final List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new DuplicateLottoNumberException();
        }
    }

    public Reward match(final Lotto lotto) {
        int count = (int) this.lotto.getLottoNumbers().stream()
            .filter(number -> lotto.isContainsNumber(new LottoNumber(number)))
            .count();

        return Reward.valueOf(count, lotto.isContainsNumber(bonusNumber));
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
        return Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonusNumber);
    }
}
