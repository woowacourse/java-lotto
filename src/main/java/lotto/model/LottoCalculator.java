package lotto.model;

import java.util.Map;

public class LottoCalculator {

    private final Lottos lottos;
    private final WinningLotto winningLotto;

    private Map<Rank, Integer> rankCount;

    public LottoCalculator(final Lottos lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public void calculateResult() {
        this.rankCount = winningLotto.checkRank(lottos);
    }

    public double getRevenue() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrice() * getEachRankCount(rank);
        }
        return ((double) sum / (lottos.size() * Money.PRICE_PER_LOTTO));
    }

    public Integer getEachRankCount(final Rank rank) {
        return rankCount.get(rank);
    }
}
