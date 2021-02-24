package lotto.domain;

import java.util.ArrayList;

public class WinningLotto {
    private static Lotto winLotto;
    private static BonusBall bonusBall;

    public WinningLotto(Lotto lotto, String bonusBallInput) {
        winLotto = lotto;
        bonusBall = new BonusBall(winLotto, bonusBallInput);
    }

    public static int howManyWins(Lotto lotto) {
        ArrayList<Integer> wins = new ArrayList<>(winLotto.getLottoNumbers());
        wins.retainAll(lotto.getLottoNumbers());
        return wins.size();
    }

    public Rank findRank(Lotto lotto) {
        int match = howManyWins(lotto);
        boolean bonusMatch = bonusBall.hasBonusBall(lotto);
        return Rank.makeRankByMatch(match, bonusMatch);
    }
}
