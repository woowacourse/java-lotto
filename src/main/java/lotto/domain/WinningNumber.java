package lotto.domain;

import java.util.Objects;

public class WinningNumber {
    private final WinningLotto winningLotto;
    private final BonusBall bonusBall;

    public WinningNumber(final Lotto winningLotto, int bonusNumber) {
        this.winningLotto = new WinningLotto(winningLotto);
        this.bonusBall = new BonusBall(bonusNumber);
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
        return Objects.equals(winningLotto, that.winningLotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto);
    }
}
