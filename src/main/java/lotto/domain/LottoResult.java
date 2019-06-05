package lotto.domain;

import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;

import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> results;
    private double profitRatio;

    private LottoResult(LottoTickets lottoTickets, LottoTicket winningLotto, LottoNumber bonusBall) {
        results = lottoTickets.countRanksWith(winningLotto, bonusBall);
        profitRatio = calculateProfitRatio(lottoTickets.size());
    }

    private double calculateProfitRatio(long numOfTickets) {
        return (double) sumAllPrize() / (numOfTickets * MoneyForLotto.MIN_PRICE_OF_LOTTO);
    }

    private long sumAllPrize() {
        long sumOfPrize = 0;
        for (Rank rank : results.keySet()) {
            sumOfPrize += rank.calculatePrize(results.get(rank));
        }
        return sumOfPrize;
    }

    public static LottoResult of(LottoTickets lottoTickets, LottoTicket winningLotto, LottoNumber bonusBall) {
        return new LottoResult(lottoTickets, winningLotto, bonusBall);
    }

    public Integer getCountsBy(Rank key) {
        return results.get(key);
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}
