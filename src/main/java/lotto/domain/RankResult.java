package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
public class RankResult {
    private final Map<Rank, Integer> rankResult;
    private final float rate;
    private final Winner winner;
    private final Lotteries lotteries;

    public RankResult(Lotteries lotteries, Winner winner, Money money) {
        this.lotteries = lotteries;
        this.winner = winner;
        this.rankResult = initRankResult(lotteries, winner);
        this.rate = calculateRateOfJackpot(money);

    }

    private Map<Rank, Integer> initRankResult(Lotteries lotteries, Winner winner) {
        Map<Rank, Integer> rankResult = new LinkedHashMap<>();
        for (Rank value : Rank.values()) {
            rankResult.put(value, 0);
        }
        return generateRankResult(rankResult, lotteries, winner);
    }

    private Map<Rank, Integer> generateRankResult(Map<Rank, Integer> rankResult, Lotteries lotteries, Winner winner) {
        for (Lotto lotto : lotteries) {
            Rank rank = winner.generateRank(lotto);
            rankResult.put(rank, rankResult.get(rank) + 1);
        }
        return rankResult;
    }

    public int matchRankCount(Rank rank) {
        return rankResult.get(rank);
    }

    public float calculateRateOfJackpot(Money money) {
        long jackpot = calculateJackpot();
        return money.calculateRate(jackpot) * 100;
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

    public LottoNumber winLotto(int index) {
        return winner.winLotto(index);
    }

    public LottoNumber winBonus() {
        return winner.getBonus();
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
