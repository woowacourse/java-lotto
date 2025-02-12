package view.input;

import constans.ErrorType;

public class BasicInputValidator implements InputValidator {

    @Override
    public void validatePurchaseAmount(final int purchaseAmount) {
        validatePurchaseAmountRange(purchaseAmount);
    }

    private void validatePurchaseAmountRange(final int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ErrorType.PURCHASE_AMOUNT_RANGE_INVALID.getMessage());
        }
    }
}
