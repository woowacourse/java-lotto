package lotto.domain;

import java.util.List;

public class Winners {
    private static final int MONEY_UNIT = 1000;
    private static final int PERCENT = 100;

    private final List<Rank> rankResult;

    public Winners(List<Rank> results) {
        this.rankResult = results;
    }

    public List<Rank> getRankResult() {
        return rankResult;
    }

    public double calculateResultRate(int inputMoney) {
        double prizeSum = getPrizeSum(rankResult);
        return (prizeSum / (inputMoney * MONEY_UNIT)) * PERCENT;
    }

    private static double getPrizeSum(List<Rank> ranks) {
        double sum = 0;

        for (Rank rank : ranks) {
            sum += rank.getPrize();
        }

        return sum;
    }
}
