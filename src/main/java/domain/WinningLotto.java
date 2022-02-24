package domain;

import java.util.List;

public class WinningLotto {

    private static final int DEFAULT_VALUE = 0;

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
        List<Integer> lottoNumbers = lotto.getLottoNumbers();
        int count = DEFAULT_VALUE;
        for (Integer lottoNumber : lottoNumbers) {
            if (this.winningNumbers.contains(lottoNumber)) {
                count++;
            }
        }

        return LottoRank.getRankByCountAndBonus(count, lottoNumbers.contains(bonusNumber));
    }
}
