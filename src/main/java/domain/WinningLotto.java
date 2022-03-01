package domain;

import java.util.List;

public class WinningLotto {

    private static final int SECOND_AND_THIRD_RANK_COUNT = 5;
    private static final boolean DEFAULT_BONUS_CHECK = false;

    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        validateDuplicatedNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatedNumber(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
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
