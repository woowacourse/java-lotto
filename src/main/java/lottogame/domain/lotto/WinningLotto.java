package lottogame.domain.lotto;

import lottogame.utils.RedundantNumbersException;

import java.util.Objects;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, String bonusNumber) {
        this.bonusNumber = LottoNumber.of(bonusNumber);
        isDuplicate(lotto, this.bonusNumber);
        this.lotto = lotto;
    }

    private void isDuplicate(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new RedundantNumbersException();
        }
    }

    public LottoNumber getBonusBall() {
        return this.bonusNumber;
    }

    public Lotto values() {
        return lotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lotto, that.lotto) && Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusNumber);
    }
}
