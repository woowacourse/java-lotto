package lotto.domain;

public class EarningRateCalculator {

    private static final int STANDARD = 100;

    public static int calculate(double totalWinningMoney, int purchaseAmount) {
        return (int)((totalWinningMoney / purchaseAmount) * STANDARD);
    }
}
