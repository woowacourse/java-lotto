package lotto.model;

import java.util.Map;

public class LottoCalculator {

    private final Money money;
    private final Lottos lottos;
    private final WinningLotto winningLotto;

    private Map<Rank, Integer> rankCount;

    public LottoCalculator(final Money money, final Lottos lottos, final WinningLotto winningLotto) {
        this.money = money;
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
        return ((double) sum / money.getMoney());
    }

    public Integer getEachRankCount(final Rank rank) {
        return rankCount.get(rank);
    }
}
