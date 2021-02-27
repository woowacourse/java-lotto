package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonus;

    public WinningLotto(Lotto winningLotto, int bonus) {
        this.winningLotto = winningLotto;
        this.bonus = LottoNumber.valueOf(bonus);
        validateWinningLotto();
    }

    private void validateWinningLotto() {
        if (winningLotto.isContains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 당첨번호에 이미 존재하는 번호는 보너스 번호가 될 수 없습니다.");
        }
    }

    public LottoRank match(Lotto userLotto) {
        boolean isMatchBonus = userLotto.isContains(bonus);
        int numOfMatch = userLotto.match(winningLotto);
        return LottoRank.valueOf(numOfMatch, isMatchBonus);
    }
}
