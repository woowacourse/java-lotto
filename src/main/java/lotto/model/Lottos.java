package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lottos {

    private static final int PRICE_PER_LOTTO = 1000;

    private final List<Lotto> lottos;
    private final Map<Rank, Integer> rankCount;

    public Lottos() {
        this.lottos = new ArrayList<>();
        rankCount = Rank.initMap();
    }

    public void insert(Lotto lotto) {
        lottos.add(lotto);
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
        return ((double)sum / (lottos.size() * PRICE_PER_LOTTO));
    }

    private Integer getEachRankCount(Rank rank) {
        return rankCount.get(rank);
    }
}
