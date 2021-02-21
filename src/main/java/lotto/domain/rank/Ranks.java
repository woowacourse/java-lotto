package lotto.domain.rank;


import static lotto.domain.lotto.LottoTicket.LOTTO_LINE_PRICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ranks {

    private final List<Rank> ranks;

    public Ranks(List<Rank> ranks) {
        this.ranks = new ArrayList<>(ranks);
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
