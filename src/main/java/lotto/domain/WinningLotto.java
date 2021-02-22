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

    private ArrayList<Integer> generateWinningLotto(String numberInput) {
        List<Integer> winningNumbers = changeToList(numberInput);
        ArrayList<Integer> winningNums = new ArrayList<>();
        winningNums.addAll(winningNumbers);
        return winningNums;
    }

    private List<Integer> changeToList(String numberInput) {
        List<Integer> winningNumbers = Arrays.stream(numberInput.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return winningNumbers;
    }

    public static int howManyWins(Lotto lotto) {
        ArrayList<Integer> wins = new ArrayList<>(winLotto.getLottoNumbers());
        wins.retainAll(lotto.getLottoNumbers());
        return wins.size();
    }

    public Rank findRank(Lotto lotto) {
        int match = howManyWins(lotto);
        boolean bonusMatch = bonusBall.hasBonusBall(lotto);
        Rank rank = Rank.makeRankByMatch(match, bonusMatch);
        return rank;
    }
}
