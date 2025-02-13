package model;

public class LottoRankCalculator {

    public LottoRank calculate(LottoTicket lottoTicket, WinningLotto winningLotto) {
        int overlappedCount = winningLotto.countOverlappedNumbers(lottoTicket.getNumbers());
        if (LottoRank.requiredBonusNumber(overlappedCount)) {
            boolean isBonusNumberOverlapped = winningLotto.isOverlappedBonusNumber(lottoTicket.getNumbers());
            return LottoRank.findByMatchCondition(overlappedCount, isBonusNumberOverlapped);
        }
        return LottoRank.findByMatchCondition(overlappedCount, false);
    }
}
