package lotto.lottogame;

import lotto.money.Money;
import lotto.money.PrizeMoney;
import lotto.ranking.Statistics;

public class LottoGameResult {
    private final double profit;
    private final Statistics statistics;

    public LottoGameResult(Statistics statistics, Money money) {
        this.statistics = statistics;
        this.profit = new PrizeMoney(statistics).calculateProfit(money);
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public double getProfit() {
        return profit;
    }
}
