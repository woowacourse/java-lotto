package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String DELIMITER = ",";
    private static Lotto winLotto;
    private static int bonusBall;

    public WinningLotto(String winningInput, String bonusBallInput) {
        generateWinningLotto(winningInput);
        validateBonusBallType(bonusBallInput);
        validateBonusBallRange();
        validateDuplicate();
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
        this.winLotto = new Lotto(winningNums);
    }

    private void validateBonusBallType(String input) {
        try {
            this.bonusBall = Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력할 수 있습니다");
        }
    }

    private void validateBonusBallRange() {
        if (bonusBall < 1 || bonusBall > 45)
            throw new IllegalArgumentException("[ERROR] 보너스볼 숫자를 1 ~ 45 사이로 입력해주세요");
    }

    private void validateDuplicate() {
        if (winLotto.isContainNum(bonusBall)) {
            throw new IllegalArgumentException("[ERROR] 보너스볼 숫자는 당첨번호와 중복될 수 없습니다");
        }
    }

    public Rank findRank(Lotto lotto) {
        int match = howManyWins(lotto);
        boolean bonusMatch = hasBonusBall(lotto);
        Rank rank = Rank.makeRankByMatch(match, bonusMatch);
        return rank;
    }

    public int howManyWins(Lotto lotto) {
        ArrayList<Integer> wins = new ArrayList<>(winLotto.getLottoNumbers());
        wins.retainAll(lotto.getLottoNumbers());
        return wins.size();
    }

    public boolean hasBonusBall(Lotto lotto) {
        return lotto.isContainNum(bonusBall);
    }
}
