package lotto.domain.rank;


import static lotto.domain.lotto.LottoMoney.LOTTO_LINE_PRICE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ranks {

    private final Map<Rank, Long> rankCounts;
    private final int size;

    public Ranks(List<Rank> ranks) {
        this.rankCounts = ranks.stream()
            .collect(Collectors.groupingBy(it -> it, HashMap::new, Collectors.counting()));
        size = ranks.size();
    }

    public int getNumberOfRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0L).intValue();
    }

    public float calculateProfitRate() {
        float totalWinMoney = Arrays.stream(Rank.values())
            .mapToInt(rank -> getNumberOfRank(rank) * rank.getMoney())
            .sum();
        return totalWinMoney / getPurchaseMoney();
    }

    private int getPurchaseMoney() {
        return size * LOTTO_LINE_PRICE;
    }

}