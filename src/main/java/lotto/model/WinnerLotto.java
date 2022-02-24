package lotto.model;

import lotto.model.exception.DuplicatedNumberException;
import java.util.List;

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
        Statistic result = new Statistic(inputMoney);
        lottoes.stream()
            .map(this::getRankBy)
            .forEach(result::addRank);
        return result;
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
