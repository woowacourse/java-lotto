package model;


import exception.LottoPurchaseExceptionType;

public record LottoPurchase(int amount) {

    public static LottoPurchase of(final int input) {
        return new LottoPurchase(input);
    }

    public LottoPurchase {
        validateMinAmount(amount);
        validateAmountUnit(amount);
    }

    private void validateMinAmount(int amount) {
        if (amount < LottoConstant.TICKET_PRICE_UNIT) {
            throw new IllegalArgumentException(
                    LottoPurchaseExceptionType.INVALID_LOTTO_MIN_PURCHASE.getMessage(LottoConstant.TICKET_PRICE_UNIT));
        }
    }

    private void validateAmountUnit(int amount) {
        if (amount % LottoConstant.TICKET_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(
                    LottoPurchaseExceptionType.INVALID_LOTTO_PURCHASE_UNIT.getMessage(LottoConstant.TICKET_PRICE_UNIT));
        }
    }
}
