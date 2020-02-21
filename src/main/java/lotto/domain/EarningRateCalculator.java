package lotto.domain;

public class EarningRateCalculator {

    /* PERCENTILE은 백분위를 의미합니다 */
    private static final int PERCENTILE = 100;

    public static int calculate(double totalWinningMoney, int purchaseAmount) {
        return (int)((totalWinningMoney / purchaseAmount) * PERCENTILE);
    }
}


