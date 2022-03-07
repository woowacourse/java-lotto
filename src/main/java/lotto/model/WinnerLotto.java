package lotto.model;

import static java.util.stream.Collectors.toList;

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

    public Collection<Rank> classify(Lottoes lottoes) {
        return lottoes.stream()
            .map(this::rankBy)
            .collect(toList());
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
