package lotto.domain.rank;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class RankResult {
    public static final int ZERO = 0;
    private static final int PERCENT = 100;
    private Map<RankType, AbstractRank> winners;

    public RankResult() {
        this.winners = initWinners();
    }

    private Map<RankType, AbstractRank> initWinners() {
        Map<RankType, AbstractRank> winners = new LinkedHashMap<>();
        winners.put(RankType.FIFTH, new FifthRank());
        winners.put(RankType.FOURTH, new FourthRank());
        winners.put(RankType.THIRD, new ThirdRank());
        winners.put(RankType.SECOND, new SecondRank());
        winners.put(RankType.FIRST, new FirstRank());
        return winners;
    }

    public void addWinner(int matchNumber, boolean bonus) {
        RankType winnerType = RankType.valueOf(matchNumber, bonus);
        if (winnerType != RankType.MISS) {
            winners.get(winnerType).addWinner();
        }
    }

    public int rateOfReturn(int userMoney) {
        long totalRewardMoney = totalRewardMoney();
        return (int) ((double) totalRewardMoney / userMoney) * PERCENT;
    }

    public long totalRewardMoney() {
        long totalRewardMoney = ZERO;
        for (AbstractRank rank : winners.values()) {
            totalRewardMoney += rank.rewardMoney();
        }
        return totalRewardMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankResult rank = (RankResult) o;
        return Objects.equals(winners, rank.winners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winners);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractRank rank : winners.values()) {
            stringBuilder.append(rank.toString());
        }
        return stringBuilder.toString();
    }
}
