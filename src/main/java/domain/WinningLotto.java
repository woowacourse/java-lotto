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
        int count = (int) lottoNumbers.stream()
                .filter(this.winningNumbers::contains)
                .count();
        return LottoRank.getRankByCountAndBonus(count, checkBonus(lottoNumbers, count));
    }

    private boolean checkBonus(List<Integer> lottoNumbers, int count) {
        if (count == 5) {
            return lottoNumbers.contains(bonusNumber);
        }
        return false;
    }
}
