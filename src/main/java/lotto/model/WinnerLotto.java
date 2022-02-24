package lotto.model;

import java.util.List;
import lotto.model.exception.DuplicatedNumberException;

public class WinnerLotto {

    private final Lotto winnerLotto;
    private final Number bonus;

    public WinnerLotto(Lotto winnerLotto, Number bonus) {
        if (winnerLotto.contains(bonus)) {
            throw new DuplicatedNumberException();
        }
        this.winnerLotto = winnerLotto;
        this.bonus = bonus;
    }

    public Statistic summarize(List<Lotto> lottoes, Money inputMoney) {
        Statistic statistic = new Statistic(inputMoney);
        for (Lotto lotto : lottoes) {
            statistic.addRank(getRankBy(lotto));
        }
        return statistic;
    }

    private Rank getRankBy(Lotto lotto) {
        return Rank.of(matchedCount(lotto), isBonusMatched(lotto));
    }

    private int matchedCount(Lotto lottoNumbers) {
        return this.winnerLotto.getMatchedCount(lottoNumbers);
    }

    private boolean isBonusMatched(Lotto lottoNumbers) {
        return lottoNumbers.contains(bonus);
    }
}
