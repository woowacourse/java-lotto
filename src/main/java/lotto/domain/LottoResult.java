package lotto.domain;

import java.util.Collections;
import java.util.Map;
import lotto.domain.enumeration.Rank;
import lotto.domain.vo.LottoPurchaseMoney;

public class LottoResult {

    private final Map<Rank, Integer> ranks;

    public LottoResult(Map<Rank, Integer> ranks) {
        this.ranks = ranks;
    }

    public double calculateYield(LottoPurchaseMoney lottoPurchaseMoney) {
        int price = lottoPurchaseMoney.getPrice();

        int totalPrizeMoney = getTotalPrizeMoney();

        return  Math.floor((double) totalPrizeMoney / price * 100) / 100.0;
    }

    public Map<Rank, Integer> getRanks() {
        return Collections.unmodifiableMap(ranks);
    }

    private int getTotalPrizeMoney() {
        return ranks.keySet()
                .stream()
                .mapToInt(Rank::getPrizeMoney)
                .sum();
    }
}
