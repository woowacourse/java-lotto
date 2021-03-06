package lotto.domain;

import java.util.ArrayList;

public class WinningLotto {
    private static Lotto winLotto;
    private static BonusBall bonusBall;

    public WinningLotto(final Lotto lotto, final String bonusBallInput) {
        winLotto = lotto;
        bonusBall = new BonusBall(winLotto, bonusBallInput);
    }

    public static int howManyWins(final Lotto lotto) {
        final ArrayList<Integer> wins = new ArrayList<>(winLotto.changeToList());
        wins.retainAll(lotto.changeToList());
        return wins.size();
    }

    public Rank findRank(final Lotto lotto) {
        final int match = howManyWins(lotto);
        final boolean bonusMatch = bonusBall.hasBonusBall(lotto);
        return Rank.makeRankByMatch(match, bonusMatch);
    }
}
