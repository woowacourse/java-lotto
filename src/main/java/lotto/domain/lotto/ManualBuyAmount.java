package lotto.domain.lotto;

import lotto.utils.NumericStringValidator;

public class ManualBuyAmount {

    private final int value;

    private ManualBuyAmount(int value) {
        this.value = value;
    }

    public static ManualBuyAmount getInstance(String manualAmountInput, Money money) {
        validateNumeric(manualAmountInput);

        int manualAmount = Integer.parseInt(manualAmountInput);
        validateLessThanTotalAmount(money, manualAmount);

        return new ManualBuyAmount(manualAmount);
    }

    private static void validateLessThanTotalAmount(Money money, int manualAmount) {
        int totalAmount = getTotalTicketAmount(money);
        if (manualAmount > totalAmount) {
            throw new IllegalArgumentException("수동 구매 개수는 총 구매 개수 이내만 가능합니다.");
        }
    }

    private static void validateNumeric(String manualAmountInput) {
        if (!NumericStringValidator.isValid(manualAmountInput)) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상의 정수만 가능합니다.");
        }
    }

    private static int getTotalTicketAmount(Money money) {
        //todo 티켓수 BigInteger or int?
        return money.toBigInteger().divide(LottoTicket.PRICE).intValue();
    }

    public int getValue() {
        return value;
    }
}