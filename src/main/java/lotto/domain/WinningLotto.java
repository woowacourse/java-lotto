package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.model.LottoRank;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonus;

    public WinningLotto(Lotto winningLotto, int bonus) {
        this.winningLotto = winningLotto;
        this.bonus = LottoNumber.valueOf(bonus);
    }

    public LottoRank match(Lotto userLotto) {
        boolean isMatchBonus = userLotto.getNumbers().contains(bonus);
        long numOfMatch = userLotto.getNumbers()
            .stream()
            .filter(lottoNumber -> winningLotto.getNumbers().contains(lottoNumber))
            .count();
        return LottoRank.valueOf(numOfMatch, isMatchBonus);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}
