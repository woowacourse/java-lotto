package lotto.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
        Set<Rank> rankSet = ranks.keySet();

        return rankSet.stream()
                .mapToInt(this::getSum)
                .sum();
    }

    private int getSum(Rank rank) {
        int result = 0;

        Integer integer = ranks.get(rank);

        for (int i = 0; i < integer; i++) {
            result += rank.getPrizeMoney();
        }

        return result;
    }
}
