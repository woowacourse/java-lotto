package domain;

import java.util.List;

public class WinningLotto {

    private static final int DEFAULT_VALUE = 0;
    private static final int SECOND_AND_THIRD_RANK_COUNT = 5;
    public static final boolean DEFAULT_BONUS_CHECK = false;

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateDuplicatedNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatedNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }

    public LottoRank countLottoRank(Lotto lotto) {
        int count = lotto.countSameNumbers(winningNumbers);
        if (count == SECOND_AND_THIRD_RANK_COUNT) {
            return LottoRank.getRankByCountAndBonus(count, lotto.checkBonus(bonusNumber));
        }
        return LottoRank.getRankByCountAndBonus(count, DEFAULT_BONUS_CHECK);
    }
}
