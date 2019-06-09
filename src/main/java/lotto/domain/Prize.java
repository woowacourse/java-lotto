package lotto.domain;

import java.util.Map;

public class Prize {
    private long prize;

    public Prize(Map<Rank, Integer> countOfRank) {
        this.prize = 0;
        this.generatePrize(countOfRank);
    }

    public double divideByMoney(Money money) {
        return money.prizeDivideMoney(prize);
    }

    public void generatePrize(Map<Rank, Integer> countOfRank) {
        countOfRank.forEach((rank, count) -> this.prize += rank.calculatePrizeOfThisRank(count));
    }

}
