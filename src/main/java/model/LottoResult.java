package model;

import dto.LottoResultDto;
import java.util.EnumMap;
import java.util.Map.Entry;

public class LottoResult {
    public static final int LOTTO_PRICE = 1000;
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
        int purchaseCount = ranks.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals(Rank.FAIL))
                .mapToInt(Entry::getValue)
                .sum();

        return purchaseCount * LOTTO_PRICE;
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
