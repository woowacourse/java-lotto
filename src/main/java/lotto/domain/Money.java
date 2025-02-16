package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lotto.common.ErrorMessage;

public class Money {
    private static final int SCALE_VALUE = 2;
    static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public void validateAmount(int amount) {
        if (amount % LOTTO_PRICE != 0 || amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_INPUT.getMessage());
        }
    }

    public String calculateAverageProfitRate(long totalProfit){
        return new BigDecimal(totalProfit)
                .divide(new BigDecimal(amount), SCALE_VALUE, RoundingMode.HALF_UP).toString();
    }

    public int getLottoTicketCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}


