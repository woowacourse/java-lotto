package lotto.model;

import lotto.Rank;

public final class RankDeterminer {

    private RankDeterminer() {
    }

    public static Rank determine(final Lotto lotto, final Lotto winningLotto, final LottoNumber bonusNumber) {
        int matchCount = lotto.getMatchCount(winningLotto);
        boolean isBonusMatch = lotto.contains(bonusNumber);
        return Rank.classifyRank(matchCount, isBonusMatch);
    }
}
