package lotto.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
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

    public Statistic summarize(List<Lotto> lottoes) {
        return new Statistic(ranks(lottoes));
    }

    private List<Rank> ranks(List<Lotto> lottoes) {
        return lottoes.stream()
            .map(this::rankBy)
            .collect(toUnmodifiableList());
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
