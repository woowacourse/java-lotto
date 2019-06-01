package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private final Map<Rank, Integer> winners;
    private final double yield;

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        this.winners = calculateRank(lottos, winningLotto);
        this.yield = calculateYield(lottos);
    }

    private Map<Rank, Integer> calculateRank(Lottos lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> winners = initWinners();
        Rank rank;
        for (int i = 0; i < lottos.getLottoCount(); i++) {
            rank = winningLotto.getCountOfMatch(lottos.getLottoByIndex(i));
            winners.put(rank, winners.get(rank) + 1);
        }
        return winners;

    }

    private Map<Rank, Integer> initWinners() {
        Map<Rank, Integer> winners = new HashMap<>();
        for (Rank value : Rank.values()) {
            winners.put(value, 0);
        }
        return winners;
    }

    private double calculateYield(Lottos lottos) {
        int totalWinningMoney = 0;
        for (Rank rank : winners.keySet()) {
            totalWinningMoney += rank.getWinningMoney() * winners.get(rank);
        }
        return (double)totalWinningMoney / (lottos.getLottoCount() * LottoMoney.LOTTO_PRICE);
    }

    public double getYield() {
        return this.yield;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Double.compare(that.yield, yield) == 0 &&
                Objects.equals(winners, that.winners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winners, yield);
    }
}
