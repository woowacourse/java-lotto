package Lotto.domain;

import Lotto.utils.NumberParser;

public class PurchaseAmount {
    private static final String PURCHASE_AMOUNT_BIGGER_THAN_LOTTO_PRICE = "구입 금액은 1000 보다 커야합니다.";
    private static final int LOTTO_PRICE = 1_000;

    private int purchaseAmount;

    public PurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = validatePurchaseAmountRange(purchaseAmount);
    }

    private int validatePurchaseAmountRange(String purchaseAmount) {
        int purchaseNumber = NumberParser.parseIntoOneNumber(purchaseAmount);
        if (isLessThanMinPrice(purchaseNumber)) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_BIGGER_THAN_LOTTO_PRICE);
        }
        return purchaseNumber;
    }

    private boolean isLessThanMinPrice(int purchaseAmount) {
        return purchaseAmount < LOTTO_PRICE;
    }

    public int calculateLottoAmount() {
        return this.purchaseAmount / LOTTO_PRICE;
    }

    int getPurchaseAmount() {
        return this.purchaseAmount;
    }
}
