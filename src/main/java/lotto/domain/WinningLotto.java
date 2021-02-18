package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonus;

    public WinningLotto(Lotto winningLotto, int bonus) {
        this.winningLotto = winningLotto;
        this.bonus = LottoNumber.valueOf(bonus);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}
