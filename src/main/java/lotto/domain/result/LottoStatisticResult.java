package lotto.domain.result;

import lotto.domain.Rank;

import java.util.Map;

public class LottoStatisticResult {
    
    private static final int LOTTO_PAY = 1000;
    
    private static final long NO_COUNT = 0L;
    
    private final Map<Rank, Long> rankCount;
    
    public LottoStatisticResult(Map<Rank, Long> rankCount) {
        this.rankCount = rankCount;
    }
    
    public Long get(Rank rank) {
        return rankCount.getOrDefault(rank, NO_COUNT);
    }
    
    public double calculateIncomeRate() {
        return (double) calculateTotalReward() / (calculatePurchaseCount() * LOTTO_PAY);
    }
    
    private long calculatePurchaseCount() {
        return rankCount.values()
                        .stream()
                        .mapToLong(t -> t)
                        .sum();
    }
    
    private long calculateTotalReward() {
        return rankCount.keySet()
                        .stream()
                        .mapToLong(rank -> rankCount.get(rank) * rank.getReward())
                        .sum();
    }
}
