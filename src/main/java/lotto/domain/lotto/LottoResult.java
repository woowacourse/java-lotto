package lotto.domain.lotto;


import static lotto.domain.lotto.utils.LottoAttributes.LOTTO_LINE_PRICE;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.utils.Rank;

public class LottoResult {

    private final List<Rank> ranks;

    public LottoResult(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public int getNumberOfRank(Rank rank) {
        long count = ranks.stream().filter(it -> it.equals(rank)).count();
        return (int) count;
    }

    public float calculateProfitRate() {
        float totalWinMoney = Arrays.stream(Rank.values())
            .mapToInt(rank -> getNumberOfRank(rank) * rank.getMoney()).sum();
        return totalWinMoney / getPurchaseMoney();
    }

    private int getPurchaseMoney() {
        return ranks.size() * LOTTO_LINE_PRICE;
    }

}
