package lotto.domain;

public class ResultCalculator {
    private static final long DECIMAL_TO_PERCENT_RATE = 100;
    private static final int ORIGINAL_PERCENT = 100;
    private static final int LOTTO_PRICE = 1000;

    public static long calculateEarningRate(PurchaseAmount purchaseAmount) {
        int purchaseMoney = purchaseAmount.getPurchaseNumber() * LOTTO_PRICE;
        int totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += rank.prize * rank.count;
        }
        return (DECIMAL_TO_PERCENT_RATE * totalPrize / purchaseMoney) - ORIGINAL_PERCENT;
    }
}
