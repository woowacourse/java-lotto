package lotto.domain.lotto;

import java.math.BigInteger;
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
        if (manualAmountIsLessThanTotalAmount(money, manualAmount)) {
            throw new IllegalArgumentException("수동 구매 개수는 총 구매 개수 이내만 가능합니다.");
        }
    }

    private static boolean manualAmountIsLessThanTotalAmount(Money money, int manualAmount) {
        BigInteger totalTicketAmount = getTotalTicketAmount(money);
        BigInteger manualTicketAmount = BigInteger.valueOf(manualAmount);

        return totalTicketAmount.compareTo(manualTicketAmount) < 0;
    }

    private static void validateNumeric(String manualAmountInput) {
        if (!NumericStringValidator.isValid(manualAmountInput)) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상의 정수만 가능합니다.");
        }
    }

    private static BigInteger getTotalTicketAmount(Money money) {
        return money.toBigInteger().divide(LottoTicket.PRICE);
    }

    public int getValue() {
        return value;
    }
}