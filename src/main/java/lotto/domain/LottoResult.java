package lotto.domain;

import java.util.Collections;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> ranks;

    public LottoResult(Map<Rank, Integer> ranks) {
        this.ranks = ranks;
    }

    public double calculateYield(Money money) {
        int price = money.getPrice();
        int totalPrizeMoney = 0;

        for (Rank rank : ranks.keySet()) {
            totalPrizeMoney += rank.getPrizeMoney();
        }

        return (double) totalPrizeMoney / (double) price;
    }

    public Map<Rank, Integer> getRanks() {
        return Collections.unmodifiableMap(ranks);
    }
}
