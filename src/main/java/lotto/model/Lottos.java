package lotto.model;

import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;
    private final Map<Rank, Integer> rankCount;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
        rankCount = Rank.initMap();
    }

    public void calculateRanks(List<Integer> winningNumbers, int bonusNumber) {
        lottos.forEach(lotto -> lotto.calculateRank(winningNumbers, bonusNumber));
    }

    public void countRank() {
        lottos.forEach(lotto -> {
            rankCount.put(lotto.getRank(), rankCount.get(lotto.getRank()) + 1);
        });
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Map<Rank, Integer> getRankCount() {
        return rankCount;
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public double getRevenue() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrice() * getEachRankCount(rank);
        }
        return ((double)sum / (lottos.size() * Lotto.LOTTO_PRICE));
    }

    private Integer getEachRankCount(Rank rank) {
        return rankCount.get(rank);
    }
}
