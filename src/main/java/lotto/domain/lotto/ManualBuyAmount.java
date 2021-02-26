package lotto.domain.lotto;

import java.math.BigInteger;
import lotto.utils.NumericStringValidator;

public final class ManualBuyAmount {

    private final BigInteger value;

    private ManualBuyAmount(BigInteger value) {
        this.value = value;
    }

    public static ManualBuyAmount getInstance(String manualAmountInput, Money money) {
        validateNumeric(manualAmountInput);

        BigInteger manualAmount = new BigInteger(manualAmountInput);
        validateLessThanTotalAmount(money, manualAmount);

        return new ManualBuyAmount(manualAmount);
    }

    private static void validateNumeric(String manualAmountInput) {
        if (!NumericStringValidator.isValid(manualAmountInput)) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상의 정수만 가능합니다.");
        }
    }

    private static void validateLessThanTotalAmount(Money money, BigInteger manualAmount) {
        if (manualAmountIsLessThanTotalAmount(money, manualAmount)) {
            throw new IllegalArgumentException("수동 구매 개수는 총 구매 개수 이내만 가능합니다.");
        }
    }

    private static boolean manualAmountIsLessThanTotalAmount(Money money, BigInteger manualAmount) {
        BigInteger totalTicketAmount = getTotalTicketAmount(money);

        return totalTicketAmount.compareTo(manualAmount) < 0;
    }

    private static BigInteger getTotalTicketAmount(Money money) {
        return money.toBigInteger().divide(LottoTicket.PRICE);
    }

    public BigInteger getValue() {
        return value;
    }
}