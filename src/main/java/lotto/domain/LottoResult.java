package lotto.domain;

import java.util.Map;

public class LottoResult {
    private Map<Rank, Integer> results;
    private double profitRatio;

    private LottoResult(LottoTickets lottoTickets, LottoTicket winningLotto) {
        results = lottoTickets.countRanksWith(winningLotto);
        profitRatio = calculateProfitRatio(lottoTickets.size());
    }

    private double calculateProfitRatio(long numOfTickets) {
        return (double) sumAllPrize() / (numOfTickets * LottoSeller.MIN_PRICE_OF_LOTTO);
    }

    private long sumAllPrize() {
        long sumOfPrize = 0;
        for (Rank rank : results.keySet()) {
            sumOfPrize += rank.calculatePrize(results.get(rank));
        }
        return sumOfPrize;
    }

    public static LottoResult of(LottoTickets lottoTickets, LottoTicket winningLotto) {
        return new LottoResult(lottoTickets, winningLotto);
    }

    public Integer getCountsBy(Rank key) {
        return results.get(key);
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}
