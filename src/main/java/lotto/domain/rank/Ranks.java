package lotto.domain.rank;


import static lotto.domain.lotto.util.LottoMoney.LOTTO_LINE_PRICE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ranks {

    private final Map<Rank, Long> rankCounts;

    public Ranks(List<Rank> ranks) {
        this.rankCounts = ranks.stream()
            .collect(Collectors.groupingBy(it -> it, HashMap::new, Collectors.counting()));
    }

    public int getNumberOfRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0L).intValue();
    }

    public float calculateProfitRate() {
        float totalWinMoney = Arrays.stream(Rank.values())
            .mapToInt(rank -> rank.calculateRankMoney(getNumberOfRank(rank)))
            .sum();
        return totalWinMoney / getTotalPurchaseMoney();
    }

    private int getTotalPurchaseMoney() {
        return rankCounts.values().stream().mapToInt(Long::intValue).sum() * LOTTO_LINE_PRICE;
    }

}