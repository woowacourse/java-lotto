package model;

import dto.LottoDto;
import java.util.EnumMap;
import java.util.Map.Entry;

public class LottoResult {
    private final EnumMap<Rank, Integer> ranks;

    public LottoResult(final EnumMap<Rank, Integer> ranks) {
        this.ranks = ranks;
    }

    public EnumMap<Rank, Integer> getRanks() {
        return ranks;
    }

    public double getProfitRate(UserLotto userLotto) {
        return calculateProfitRate(userLotto.getPurchaseAmount(), calculateProfit());
    }

    private double calculateProfitRate(int purchaseAmount, long winningLotto) {
        return (double) winningLotto / purchaseAmount;
    }

    private long calculateProfit() {
        long profit = 0;
        for (Entry<Rank, Integer> entry : ranks.entrySet()) {
            Rank rank = entry.getKey();
            int rankCount = entry.getValue();
            profit += (long) rank.getWinningAmount() * rankCount;
        }
        return profit;
    }

}
