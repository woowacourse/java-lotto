package lotto.domain;

import lotto.domain.exception.WinningLottoContainBonusException;

import java.util.Objects;

public class WinningNumber {
    private final WinningLotto winningLotto;
    private final BonusBall bonusBall;

    public WinningNumber(final Lotto winningLotto, int bonusNumber) {
        this.winningLotto = new WinningLotto(winningLotto);
        this.bonusBall = new BonusBall(bonusNumber);

        if (winningLotto.hasBonusBall(this.bonusBall)) {
            throw new WinningLottoContainBonusException("보너스 볼이 당첨 로또 번호와 중복됩니다.");
        }
    }

    public Prize prize(final Lotto lotto) {
        int matchCount = winningLotto.countOfMatchLottoNumber(lotto);
        boolean hasBonus = lotto.hasBonusBall(bonusBall);
        return Prize.valueOf(matchCount, hasBonus);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WinningNumber that = (WinningNumber) o;
        return Objects.equals(winningLotto, that.winningLotto) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto, bonusBall);
    }
}
