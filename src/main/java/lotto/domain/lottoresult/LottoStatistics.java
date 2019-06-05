package lotto.domain.lottoresult;

import lotto.domain.Rank;
import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottoticket.LottoTickets;

public class LottoStatistics {
    private LottoResult lottoResult;
    private double profitRatio;

    private LottoStatistics(LottoTickets lottoTickets, WinningLotto winningLotto) {
        lottoResult = LottoResult.of(lottoTickets, winningLotto);
        profitRatio = calculateProfitRatio(lottoTickets.size());
    }

    private double calculateProfitRatio(long numOfTickets) {
        return (double) lottoResult.sumAllPrize() / (numOfTickets * MoneyForLotto.MIN_PRICE_OF_LOTTO);
    }

    public static LottoStatistics of(LottoTickets lottoTickets, WinningLotto winningLotto) {
        return new LottoStatistics(lottoTickets, winningLotto);
    }

    public Integer getCountsBy(Rank key) {
        return lottoResult.getCountsBy(key);
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}
