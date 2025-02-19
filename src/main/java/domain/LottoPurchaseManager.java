package domain;

import static error.ErrorMessage.INVALID_LOTTO_PRICE;

public class LottoPurchaseManager {
    private static final int TICKET_PRICE = 1_000;

    private LottoPurchaseManager() {
    }

    public static int purchaseLottoByAmount(int amount) {
        validateAmount(amount);
        return calculateQuantity(amount);
    }

    private static void validateAmount(int amount) {
        if (amount % TICKET_PRICE == 0) {
            return;
        }
        throw new IllegalArgumentException(INVALID_LOTTO_PRICE.getMessage());
    }

    private static int calculateQuantity(int amount) {
        return amount / TICKET_PRICE;
    }

}
