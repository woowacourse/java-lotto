package lotto.domain;

import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> ranks;

    public LottoResult(Map<Rank, Integer> ranks) {
        this.ranks = ranks;
    }

    public double calculateYield(Amount amount) {
        int price = amount.getPrice();
        int totalPrizeMoney = 0;

        for (Rank rank : ranks.keySet()) {
            totalPrizeMoney += rank.getPrizeMoney();
        }

        return (double) totalPrizeMoney / (double) price;
    }
}
