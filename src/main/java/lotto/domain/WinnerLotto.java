package lotto.domain;

import lotto.domain.vo.LottoNumber;

public class WinnerLotto {

    private final Lotto winner;
    private final LottoNumber bonus;

    public WinnerLotto(Lotto winner, LottoNumber bonus) {
        if (winner.containsNumber(bonus)) {
            throw new IllegalArgumentException("보너스볼은 당첨번호와 중복될 수 없다.");
        }
        this.winner = winner;
        this.bonus = bonus;
    }

    public Rank findRank(Lotto lotto) {
        return Rank.find(winner.countMatchNumbers(lotto), lotto.containsNumber(bonus));
    }
}
