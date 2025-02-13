package model;

public class LottoRankCalculator {

    public void calculate(LottoTicket lottoTicket, WinningLotto winningLotto) {
        int overlappedCount = winningLotto.countOverlappedNumbers(lottoTicket.getNumbers());
        boolean isBonusNumberOverlapped = winningLotto.isOverlappedBonusNumber(lottoTicket.getNumbers());
    }
}
