package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
public class RankResult {
    private final Map<Rank, Integer> rankResult;
    private final float rate;

    public RankResult(Lotteries lotteries, Winner winner, Money money) {
        this.rankResult = initRankResult();
        generateRankResult(lotteries, winner);
        this.rate = calculateRateOfJackpot(money);

    }

    private void generateRankResult(Lotteries lotteries, Winner winner) {
        for (Lotto lotto : lotteries) {
            addRank(winner.generateRank(lotto));
        }
    }

    private Map<Rank, Integer> initRankResult() {
        Map<Rank, Integer> rankResult = new HashMap<>();
        for (Rank value : Rank.values()) {
            rankResult.put(value, 0);
        }

        return rankResult;
    }

    public void addRank(Rank rank) {
        rankResult.put(rank, rankResult.get(rank) + 1);
    }

    public int matchRankCount(Rank rank) {
        return rankResult.get(rank);
    }

    public float calculateRateOfJackpot(Money money) {
        long jackpot = calculateJackpot();
        return money.calculateRate(jackpot);
    }

    private long calculateJackpot() {
        long jackpot = 0;
        for (Map.Entry<Rank, Integer> rankIntegerEntry : rankResult.entrySet()) {
            jackpot += rankIntegerEntry.getKey().getWinningMoney() * rankIntegerEntry.getValue();
        }
        return jackpot;
    }

    public Map<Rank, Integer> getRankResult() {
        return rankResult;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "RankResult{" +
                "rankResult=" + rankResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankResult that = (RankResult) o;
        return Objects.equals(rankResult, that.rankResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankResult);
    }
}
