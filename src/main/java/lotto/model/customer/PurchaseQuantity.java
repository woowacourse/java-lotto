package lotto.model.customer;

import lotto.model.customer.exception.InvalidPurchaseQuantityException;

public class PurchaseQuantity {
    private static final int ZERO = 0;
    private int purchaseQuantity;

    public PurchaseQuantity(int purchaseQuantity) {
        checkValidPurchaseQuantity(purchaseQuantity);
        this.purchaseQuantity = purchaseQuantity;
    }

    public boolean isLessThanPurchaseQuantity(int count) {
        return purchaseQuantity > count;
    }

    private void checkValidPurchaseQuantity(int purchaseQuantity) {
        if (purchaseQuantity < ZERO) {
            throw new InvalidPurchaseQuantityException("구입 금액이 부족합니다.");
        }
    }
}
