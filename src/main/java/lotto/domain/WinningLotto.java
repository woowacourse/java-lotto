package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String DELIMITER = ",";
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
