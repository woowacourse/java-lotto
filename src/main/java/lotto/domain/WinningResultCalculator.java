package lotto.domain;

import java.util.List;

public class WinningResultCalculator {

    private static final int PERCENT = 100;

    public static int calculateEarningRate(double totalWinningMoney, int purchaseAmount) {
        return (int)((totalWinningMoney / purchaseAmount) * PERCENT);
    }

    static int calculateTotalWinningMoney(List<Integer> winningMoneys) {
        return winningMoneys.stream().mapToInt(Integer::intValue).sum();
    }
}


