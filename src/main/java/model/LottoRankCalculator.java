package model;

public class LottoRankCalculator {

    public LottoRank calculate(LottoNumbers lottoNumbers, WinningLotto winningLotto) {
        int overlappedCount = winningLotto.countOverlappedNumbers(lottoNumbers.numbers());

        if (LottoRank.requiredBonusNumber(overlappedCount)) {
            boolean isBonusNumberOverlapped = winningLotto.isOverlappedBonusNumber(lottoNumbers.numbers());
            return LottoRank.findByMatchCondition(overlappedCount, isBonusNumberOverlapped);
        }

        return LottoRank.findByMatchCondition(overlappedCount, false);
    }
}
