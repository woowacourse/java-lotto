package Lotto.domain;

import Lotto.utils.NumberParser;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1_000;

    private int purchaseAmount;

    public PurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = validate(purchaseAmount);
    }

    private int validate(String purchaseAmount) {
        int purchaseNumber = NumberParser.parseIntoOneNumber(purchaseAmount);
        if(isLessThanMinPrice(purchaseNumber)) {
            throw new IllegalArgumentException("구입 금액은 "+ LOTTO_PRICE +"보다 커야합니다.");
        }
        return purchaseNumber;
    }

    private boolean isLessThanMinPrice(int purchaseAmount) {
        return purchaseAmount < LOTTO_PRICE;
    }

    public int calculateLottoAmount() {
        return this.purchaseAmount / LOTTO_PRICE;
    }

    public int getPurchaseAmount() {
        return this.purchaseAmount;
    }
}
