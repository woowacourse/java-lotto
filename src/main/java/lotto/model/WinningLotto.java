package lotto.model;

import lotto.exception.DuplicatedNumberException;
import java.util.List;

public class WinningLotto {

    private final Lotto winningLotto;
    private final Number bonus;

    public WinningLotto(Lotto winningLotto, Number bonus) {
        if (winningLotto.contains(bonus)) {
            throw new DuplicatedNumberException();
        }
        this.winningLotto = winningLotto;
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
        return this.winningLotto.getMatchedCount(lottoNumbers);
    }

    private boolean isBonusMatched(Lotto lottoNumbers) {
        return lottoNumbers.contains(bonus);
    }
}
