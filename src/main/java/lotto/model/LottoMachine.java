package lotto.model;

import java.util.Map;

public class LottoMachine {

    private final Lottos lottos;
    private final WinningLotto winningLotto;
    private Map<Rank, Integer> rankCount;

    public LottoMachine(final Lottos lottos, final WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public Map<Rank, Integer> calculateResult() {
        return this.rankCount = winningLotto.checkRank(lottos);
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
