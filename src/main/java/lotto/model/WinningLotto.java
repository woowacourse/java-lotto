package lotto.model;

import java.util.List;

public class WinningLotto {

    private List<Integer> winningNumbers;
    private int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public void checkRank(Lottos lottos) {
        lottos.calculateRanks(winningNumbers, bonusNumber);
    }

//    public Rank calculateRank(Lotto lotto) {
//        int count = lotto.countMatchingNumber(winningNumbers);
//        return Rank.getRank(count, lotto.winBonusNumber(bonusNumber));
//    }
}
