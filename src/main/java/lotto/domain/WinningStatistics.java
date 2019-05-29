package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.LottoVendingMachine.LOTTO_PRICE;

public class WinningStatistics {
    private final Map<Rank, Integer> statistics = new HashMap<>();

    {
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
    }

    public WinningStatistics(List<Rank> ranks) {
        for (Rank rank : ranks) {
            statistics.put(rank, statistics.get(rank) + 1);
        }
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }

    public int getInterestRate() {
        Money money = new Money(0);
        for (Map.Entry<Rank, Integer> entry : statistics.entrySet()) {
            money = money.add(new Money(entry.getKey().getWinningMoney() * entry.getValue()));
        }
        return money.divideBy(new Money(LOTTO_PRICE));
    }
}
