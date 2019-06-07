package lotto.domain;

import java.util.List;

public class Winners {
    private static final int MONEY_UNIT = 1000;
    private static final int PERCENT = 100;

    private final List<Rank> rankResult;
    private final int returnRate;

    public Winners(List<Rank> rankResult, int returnRate) {
        this.rankResult = rankResult;
        this.returnRate = returnRate;
    }

    private static double calculateResultRate(int inputMoney, double sum) {
        return (sum / (inputMoney * MONEY_UNIT)) * PERCENT;
    }

    private static double getReturnRate(List<Rank> ranks, int inputMoney) {
        return calculateResultRate(inputMoney, getSum(ranks));
    }


    private static double getSum(List<Rank> ranks) {
        double sum = 0;

        for (Rank rank : Rank.values()) {
            sum += rank.getPrize(ranks);
        }

        if (sum == 0) {
            return 0;
        }
        return sum;
    }


}
