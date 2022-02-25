package lotto.domain;

public class Money {

    private final int amount;

    public Money(final int amount) {
        checkLowerThanStandard(amount);
        this.amount = amount;
    }

    private void checkLowerThanStandard(final int amount) {
        if (amount < Lotto.LOTTO_PURCHASE_MONEY) {
            throw new IllegalArgumentException("[ERROR] 로또를 구매할 수 없는 금액입니다.");
        }
    }

    public int calculateLottoCount() {
        return amount / Lotto.LOTTO_PURCHASE_MONEY;
    }

    public LottoPurchaseCounts calculatePurchaseCounts(final int manualCounts) {
        if (calculateLottoCount() < manualCounts) {
            throw new IllegalArgumentException("[ERROR] 보유 금액보다 많은 로또를 구매할 수 없습니다.");
        }
        return new LottoPurchaseCounts(manualCounts, calculateLottoCount() - manualCounts);
    }
}
