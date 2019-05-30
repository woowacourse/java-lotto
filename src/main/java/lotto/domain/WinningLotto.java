package lotto.domain;

import lotto.domain.exception.WinningLottoCreateException;

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
            throw new WinningLottoCreateException("보너스 번호와 로또 번호가 같으면 안됩니다");
        }
    }

    static WinningLotto of(final Lotto winningLotto, final LottoNumber bonus) {
        return new WinningLotto(winningLotto, bonus);
    }

    Rank match(final Lotto other) {
        boolean existBonus = other.contains(this.bonus);
        return Rank.valueOf(winningLotto.matchCount(other), existBonus);
    }
}
