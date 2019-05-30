package lotto.model.customer;

import lotto.model.customer.exception.InvalidPurchaseAmountException;
import lotto.model.lottostore.Price;

public class PurchaseAmount {
    private static final int ZERO = 0;
    private final int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        checkValidPurchaseAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public PurchaseQuantity calculatePurchaseQuantity(Price price, int countOfCustomLotto) {
        try {
            int purchaseQuantity = (this.purchaseAmount / price.getPrice() - countOfCustomLotto);

            return new PurchaseQuantity(purchaseQuantity);
        } catch (ArithmeticException e) {
            throw new InvalidPurchaseAmountException("잘못된 가격입니다.");
        }
    }

    private void checkValidPurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= ZERO) {
            throw new InvalidPurchaseAmountException("잘못된 구입 금액입니다.");
        }
    }
}