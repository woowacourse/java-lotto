package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {
    private final Map<Rank, Integer> statistics = new HashMap<>();

    public WinningStatistics(List<Rank> ranks) {
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
        for (Rank rank : ranks) {
            statistics.put(rank, statistics.get(rank) + 1);
        }
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }

    public long getInterestRate(LottoBuyingMoney inputLottoBuyingMoney) {
        return ((long) getPrize().getValue() * 100) / (inputLottoBuyingMoney.getValue());
    }

    public Money getPrize() {
        Money totalGain = new Money(0);
        for (Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            Rank rank = entry.getKey();
            int num = entry.getValue();
            totalGain = totalGain.add(new Money(rank.getWinningMoney() * num));
        }
        return totalGain;
    }
}
