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

    public String calculateResultRate(int inputMoney) {
        double prizeSum = getPrizeSum();
        return String.format("%.2f", (prizeSum / (inputMoney * MONEY_UNIT)) * PERCENT);
    }

    public double getPrizeSum() {
        double sum = 0;

        for (Rank rank : rankResult) {
            sum += rank.getPrize();
        }

        return sum;
    }
}
