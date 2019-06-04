package model;

public class LottoPurchaseAmount {
    private final int auto;
    private final int manual;

    public LottoPurchaseAmount(int totalPurchaseAmount, int manualPurchaseAmount) {
        if ((totalPurchaseAmount < manualPurchaseAmount) || (totalPurchaseAmount + manualPurchaseAmount < 0)) {
            throw new IllegalArgumentException();
        }
        this.auto = totalPurchaseAmount - manualPurchaseAmount;
        this.manual = manualPurchaseAmount;
    }

    public LottoPurchaseAmount(Money investment, int manualPurchaseAmount) {
        this(investment.amount() / Lotto.PRICE, manualPurchaseAmount);
    }

    public int auto() {
        return this.auto;
    }

    public int manual() {
        return this.manual;
    }
}