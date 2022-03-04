package lotto.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import lotto.domain.enumeration.Rank;
import lotto.domain.vo.LottoPurchaseMoney;

public class LottoResult {

    private final Map<Rank, Integer> ranks;

    public LottoResult(Map<Rank, Integer> ranks) {
        this.ranks = ranks;
    }

    public double calculateYield(LottoPurchaseMoney lottoPurchaseMoney) {
        int price = lottoPurchaseMoney.getPrice();

        double totalPrizeMoney = getTotalPrizeMoney();

        return totalPrizeMoney / price;
    }

    public Map<Rank, Integer> getRanks() {
        return Collections.unmodifiableMap(ranks);
    }

    private double getTotalPrizeMoney() {
        Set<Rank> rankSet = ranks.keySet();

        return rankSet.stream()
                .mapToDouble(this::getSum)
                .sum();
    }

    private double getSum(Rank rank) {
        Integer count = ranks.get(rank);

        return count * rank.getPrizeMoney();
    }
}
