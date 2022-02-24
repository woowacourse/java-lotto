package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoStatistics {

    private final Map<LottoRank, Long> rankCountMap;
    private final int money;

    public LottoStatistics(List<LottoRank> ranks, Money money) {
        this.rankCountMap = ranks.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        this.money = money.getAmount();
    }

    public Map<LottoRank, Long> getRankCountMap() {
        return rankCountMap;
    }

    public int getMoney() {
        return money;
    }
}
