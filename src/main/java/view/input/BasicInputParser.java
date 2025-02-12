package view.input;

import constans.ErrorType;

public class BasicInputParser implements InputParser {
    @Override
    public int parsePurchaseAmount(final String purchaseAmountText) {
        try {
            return Integer.parseInt(purchaseAmountText);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(ErrorType.PURCHASE_AMOUNT_IS_NOT_NUMBER.getMessage());
        }
    }
}
