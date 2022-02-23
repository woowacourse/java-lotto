package lotto.domain;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    public int getLottoCount(PurchaseAmount purchaseAmount) {
        return purchaseAmount.getPurchaseAmount() / LOTTO_PRICE;
    }
}
