package lotto.money;

import lotto.ranking.Ranking;
import lotto.ranking.Statistics;

public class PrizeMoney {
    private int prizeMoney;

    public PrizeMoney(){
        this.prizeMoney = 0;
    }

    public int totalPrize(Statistics statistics) {
        for(Ranking ranking : Ranking.values()) {
            prizeMoney += ranking.calculatePrize(statistics.findRankingCount(ranking));
        }
        return prizeMoney;
    }
}
