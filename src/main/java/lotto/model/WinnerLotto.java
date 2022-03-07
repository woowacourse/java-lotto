package lotto.model;

import java.util.Collection;
import lotto.model.exception.DuplicatedNumberException;

public class WinnerLotto {

    private final Lotto winnerLotto;
    private final LottoNumber bonus;

    public WinnerLotto(Lotto winnerLotto, LottoNumber bonus) {
        if (winnerLotto.contains(bonus)) {
            throw new DuplicatedNumberException();
        }
        this.winnerLotto = winnerLotto;
        this.bonus = bonus;
    }

    public Statistic summarize(Lottoes lottoes) {
        return Statistic.summarizeBy(ranksBy(lottoes));
    }

    private Collection<Rank> ranksBy(Lottoes lottoes) {
        return lottoes.mapAndCollect(this::rankBy);
    }

    private Rank rankBy(Lotto lotto) {
        return Rank.of(matchedCount(lotto), isBonusMatched(lotto));
    }

    private int matchedCount(Lotto lottoNumbers) {
        return this.winnerLotto.getMatchedCount(lottoNumbers);
    }

    private boolean isBonusMatched(Lotto lottoNumbers) {
        return lottoNumbers.contains(bonus);
    }
}
