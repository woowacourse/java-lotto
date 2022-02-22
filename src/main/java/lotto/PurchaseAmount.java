package lotto;

import lotto.util.StringToIntConverter;

public class PurchaseAmount {

    private static final int TICKET_PRICE = 1000;

    private final int amount;

    public PurchaseAmount(String input) {
        final int inputAmount = StringToIntConverter.toInt(input);
        validateMinimumAmount(inputAmount);
        amount = calculateActualAmount(inputAmount);
    }

    private void validateMinimumAmount(final int amount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException("구입금액은 1000원 이상이어야 합니다");
        }
    }

    private int calculateActualAmount(final int amount) {
        return amount - (amount % TICKET_PRICE);
    }

    public int getAmount() {
        return amount;
    }
}
