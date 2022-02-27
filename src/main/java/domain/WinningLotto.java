package domain;

import java.util.List;

public class WinningLotto {

    private static final String DUPLICATED_BONUS_BALL_ERROR_MESSAGE = " 보너스 볼은 당첨 번호와 중복됩니다.";
    private static final int SECOND_AND_THIRD_RANK_COUNT = 5;

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateDuplicatedNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatedNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(bonusNumber + DUPLICATED_BONUS_BALL_ERROR_MESSAGE);
        }
    }

    public LottoRank countLottoRank(Lotto lotto) {
        int count = lotto.countSameNumbers(winningNumbers);
        boolean bonus = false;
        if (count == SECOND_AND_THIRD_RANK_COUNT) {
            bonus = lotto.checkBonus(bonusNumber);
        }
        return LottoRank.getRankByCountAndBonus(count, bonus);
    }
}
