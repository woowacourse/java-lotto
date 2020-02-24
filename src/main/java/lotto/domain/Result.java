package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Result {
    private static final long DECIMAL_TO_PERCENT_RATE = 100;
    private static final int ORIGINAL_PERCENT = 100;
    private static final int LOTTO_PRICE = 1000;

    private final Map<Rank, Integer> rankCountMap = new HashMap<>();

    public Result() {
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }
    }

    public void calculateRank(PurchaseLottos purchaseLottos, WinningRule winningRule) {
        for (Lotto lotto : purchaseLottos.getPurchaseLottos()) {
            calculateRank(lotto, winningRule);
        }
    }

    private void calculateRank(Lotto lotto, WinningRule winningRule) {
        Optional<Rank> rank = winningRule.findRank(lotto);
        if (rank.isPresent()) {
            rankCountMap.replace(rank.get(), rankCountMap.get(rank.get()) + 1);
        }
    }

    public long calculateEarningRate(PurchaseAmount purchaseAmount) {
        int purchaseMoney = purchaseAmount.getPurchaseNumber() * LOTTO_PRICE;
        int totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += rank.prize * rankCountMap.get(rank);
        }
        return (DECIMAL_TO_PERCENT_RATE * totalPrize / purchaseMoney) - ORIGINAL_PERCENT;
    }

    public int getRankCount(Rank rank) {
        return rankCountMap.get(rank);
    }
}
