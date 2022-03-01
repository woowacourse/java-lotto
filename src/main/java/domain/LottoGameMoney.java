package domain;

public class LottoGameMoney {

    private static final String ERROR_MESSAGE_MONEY_RANGE = "금액은 0이하일 수 없습니다.";
    private static final String ERROR_MESSAGE_LOTTO_MONEY_DIGITS = "로또 구매 금액은 1000원 단위여야 합니다.";

    private static final int MINIMUM_AMOUNT = 0;
    private static final int LOTTO_PRICE = 1000;
    private static final int REMINDER_STANDARD = 0;

    private final int amount;

    public LottoGameMoney(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    private void validateRange(int amount) {
        if (amount <= MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE_MONEY_RANGE);
        }
        if (amount % LOTTO_PRICE != REMINDER_STANDARD) {
            throw new IllegalArgumentException(ERROR_MESSAGE_LOTTO_MONEY_DIGITS);
        }
    }

    public int purchasableLottoCount() {
        return amount / LOTTO_PRICE;
    }
}
