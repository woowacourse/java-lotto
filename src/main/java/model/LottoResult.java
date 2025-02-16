package model;

import dto.LottoResultDto;
import java.util.EnumMap;
import java.util.Map.Entry;

public class LottoResult {
    private final EnumMap<Rank, Integer> ranks = new EnumMap<>(Rank.class);

    public LottoResult(UserLotto userLotto, WinningLotto winningLotto) {
        initRank();
        userLotto.calculateLottoResult(ranks, winningLotto);
    }

    public LottoResultDto toDto() {
        return new LottoResultDto(ranks, getProfitRate());
    }

    private double getProfitRate() {
        long profit = calculateProfit();
        if(profit == 0) return 0.0;
        return (double) calculateProfit() / calculatePurchaseAmount();
    }

    private int calculatePurchaseAmount() {
        int purchaseCount = 0;
        for (Rank rank : ranks.keySet()) {
            if (rank.equals(Rank.FAIL)) {
                continue;
            }
            purchaseCount += ranks.get(rank);
        }

        return purchaseCount * 1000;
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

    private void initRank() {
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
    }
}
