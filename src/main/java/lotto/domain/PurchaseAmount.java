package lotto.domain;

import lotto.exceptions.ExceptionMessage;

public class PurchaseAmount {

    private final int amount;

    public PurchaseAmount(int amount) {
        validateUnit(amount);
        this.amount = amount;
    }

    private void validateUnit(int purchaseAmount) {
        boolean isInValidAmount = purchaseAmount % Lotto.LOTTO_PRICE != 0 || purchaseAmount <= 0;
        if (isInValidAmount) {
            String messageTemplate = ExceptionMessage.INVALID_PURCHASE_AMOUNT.getContent();
            String exceptionMessage = String.format(messageTemplate, Lotto.LOTTO_PRICE);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}
