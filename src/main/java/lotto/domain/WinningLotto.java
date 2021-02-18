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
        generateWinningLotto(winningInput);
        validateBonusBallType(bonusBallInput);
        validateBonusBallRange();
        validateDuplicate();
    }

    public static int howManyWins(Lotto lotto) {
        ArrayList<Integer> wins = new ArrayList<>(winLotto.getLottoNumbers());
        wins.retainAll(lotto.getLottoNumbers());
        return wins.size();
    }

    private List<Integer> changeToList(String numberInput) {
        List<Integer> winningNumbers = Arrays.stream(numberInput.split(DELIMITER, -1))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return winningNumbers;
    }

    private void generateWinningLotto(String numberInput) {
        List<Integer> winningNumbers = changeToList(numberInput);
        ArrayList<Integer> winningNums = new ArrayList<Integer>();
        winningNums.addAll(winningNumbers);
        return winningNums;
    }

    public Rank findRank(Lotto lotto) {
        int match = howManyWins(lotto);
        boolean bonusMatch = hasBonusBall(lotto);
        Rank rank = Rank.makeRankByMatch(match, bonusMatch);
        return rank;
    }

}
