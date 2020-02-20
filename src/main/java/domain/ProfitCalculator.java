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
        return totalPrizeMoney / money.getMoney();
    }
}
