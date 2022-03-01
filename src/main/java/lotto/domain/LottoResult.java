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

        int totalPrizeMoney = getTotalPrizeMoney();

        return  Math.floor((double) totalPrizeMoney / price * 100) / 100.0;
    }

    public Map<Rank, Integer> getRanks() {
        return Collections.unmodifiableMap(ranks);
    }

    private int getTotalPrizeMoney() {
        int sum = 0;

        Set<Rank> rankSet = ranks.keySet();

        for (Rank rank : rankSet) {
            sum = getSum(sum, rank);
        }

        return sum;
    }

    private int getSum(int sum, Rank rank) {
        Integer integer = ranks.get(rank);

        for (int i = 0; i < integer; i++) {
            sum += rank.getPrizeMoney();
        }
        return sum;
    }
}
