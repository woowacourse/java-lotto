package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

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
        Map<Rank, Integer> winners = new TreeMap<>();
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


    @Override
    public String toString() {
        winners.remove(Rank.MISS);
        StringBuilder sb = new StringBuilder();
        for (Rank rank : winners.keySet()) {
            sb.append(rank.getCountOfMatch()).append("개 일치");
            String second = (rank == Rank.SECOND) ? ", 보너스 볼 일치" : "";
            sb.append(second).append("(").append(rank.getWinningMoney()).append(")- ");
            sb.append(winners.get(rank)).append("개\n");
        }
        return sb.toString();
    }

}
