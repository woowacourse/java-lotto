package lotto.model;

import lotto.Rank;

public class RankDeterminer {

    public static Rank determine(Lotto lotto, Lotto winningLotto, int bonusNumber) {
        int matchCount = lotto.countMatchingNumbers(winningLotto);
        boolean isBonusMatch = lotto.contains(bonusNumber);
        return Rank.classifyRank(matchCount, isBonusMatch);
    }
}
