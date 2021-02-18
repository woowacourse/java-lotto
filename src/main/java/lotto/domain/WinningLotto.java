package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {

    private static final String DELIMITER = ",";
    private static Lotto winLotto;
    private static BonusBall bonusBall;

    public WinningLotto(String winningInput, String bonusBallInput) {
        winLotto = new Lotto(generateWinningLotto(winningInput));
        bonusBall = new BonusBall(winLotto, bonusBallInput);
    }

    public static int howManyWins(Lotto lotto) {
        ArrayList<Integer> wins = new ArrayList<>(winLotto.getLotto());
        wins.retainAll(lotto.getLotto());
        return wins.size();
    }

    private List<Integer> changeToList(String numberInput) {
        return Arrays.stream(numberInput.split(DELIMITER))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private ArrayList<Integer> generateWinningLotto(String numberInput) {
        List<Integer> winningNumbers = changeToList(numberInput);
        ArrayList<Integer> winningNums = new ArrayList<>(winningNumbers);
        return winningNums;
    }

    public Rank findRank(Lotto lotto) {
        int match = howManyWins(lotto);
        boolean bonusMatch = bonusBall.hasBonusBall(lotto);
        return Rank.makeRankByMatch(match, bonusMatch);
    }
}
