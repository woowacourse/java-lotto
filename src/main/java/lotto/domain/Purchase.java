package lotto.domain;

import lotto.exception.LottoCustomException;

public class Purchase {
    private final Money money;
    private final int autoPurchase;
    private final int manualPurchase;

    public Purchase(Money money, int manualPurchase) {
        this.money = money;
        validate(manualPurchase);
        this.manualPurchase = manualPurchase;
        this.autoPurchase = calculate(manualPurchase);
    }

    public int getAutoPurchase() {
        return autoPurchase;
    }

    public int getManualPurchase() {
        return manualPurchase;
    }

    private int calculate(int manualPurchase) {
        return money.getTotalPurchaseCount() - manualPurchase;
    }

    private void validate(int manualPurchase) {
        if (money.isCountExceedLimit(manualPurchase)) {
            throw new LottoCustomException("수동으로 구매할 로또의 개수가 금액을 초과합니다.");
        }
    }
}
