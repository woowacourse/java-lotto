package model;

import dto.LottoDto;
import java.util.EnumMap;
import java.util.Map.Entry;

public class LottoResult {
    private final EnumMap<Rank, Integer> ranks = new EnumMap<>(Rank.class);
    private final double profitRate;

    public LottoResult(UserLotto userLotto, WinningLotto winningLotto) {
        initRank();
        calculateRanks(userLotto, winningLotto);
        profitRate = calculateProfitRate(userLotto.getPurchaseAmount(), calculateProfit());
    }

    public EnumMap<Rank, Integer> getRanks() {
        return ranks;
    }

    public double getProfitRate() {
        return profitRate;
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

    private void calculateRanks(UserLotto userLotto, WinningLotto winningLotto) {
        for (LottoDto lottoDto : userLotto.getLottosDto()) {
            Rank rank = Rank.getRank(winningLotto, lottoDto);
            ranks.put(rank, ranks.get(rank) + 1);
        }
    }

    private void initRank() {
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
    }
}
