package lotto.model;

import java.util.Map;

public class LottoMachine {

    private static final int PRICE_PER_LOTTO = 1000;

    private final Lottos lottos;
    private final WinningLotto winningLotto;
    private Map<Rank, Integer> rankCount;

    public LottoMachine(final Lottos lottos, final WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public Map<Rank, Integer> run() {
        return this.rankCount = winningLotto.checkRank(lottos);
    }

    public double getRevenue() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrice() * getEachRankCount(rank);
        }
        return ((double) sum / (lottos.size() * PRICE_PER_LOTTO));
    }

    public Integer getEachRankCount(final Rank rank) {
        return rankCount.get(rank);
    }
}
