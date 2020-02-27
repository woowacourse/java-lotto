package domain;

public class ProfitCalculator {
    private ProfitCalculator() {
    }

    public static int calculateProfit(Money money, WinningCalculator winningCalculator) {
        int totalPrizeMoney = 0;
        for (PrizeType prizeType : PrizeType.values()) {
            totalPrizeMoney = totalPrizeMoney +
                    prizeType.calculatePrizeMoney(winningCalculator.getPrizeTypeValue(prizeType));
        }
        return castingInteger(totalPrizeMoney, money.getMoney());
    }

    private static int castingInteger(int totalPrizeMoney, int money) {
        Double profitPercent = Double.valueOf(totalPrizeMoney) / Double.valueOf(money) * 100;
        return profitPercent.intValue();
    }
}
