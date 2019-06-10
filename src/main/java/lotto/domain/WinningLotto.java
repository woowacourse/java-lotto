package lotto.domain;

import lotto.domain.exception.WinningLottoHasBonusException;

import java.util.Objects;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonus;

    private WinningLotto(final Lotto winningLotto, final LottoNumber bonus) {
        validate(winningLotto, bonus);
        this.winningLotto = winningLotto;
        this.bonus = bonus;
    }

    private void validate(final Lotto winningLotto, final LottoNumber bonus) {
        if (winningLotto.contains(bonus)) {
            throw new WinningLottoHasBonusException("보너스 번호와 로또 번호가 같으면 안됩니다");
        }
    }

    public static WinningLotto of(final Lotto winningLotto, final LottoNumber bonus) {
        return new WinningLotto(winningLotto, bonus);
    }

    Rank match(final Lotto other) {
        boolean existBonus = other.contains(this.bonus);
        return Rank.valueOf(winningLotto.matchCount(other), existBonus);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public Integer getBonusNumber() {
        return bonus.getLottoNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WinningLotto)) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningLotto, that.winningLotto) &&
                Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto, bonus);
    }
}
