package lotto.domain;

import static lotto.exception.ExceptionMessage.MUST_BE_DIVIDE_BY_THOUSAND;
import static lotto.exception.ExceptionMessage.NOT_ALLOW_NEGATIVE;

import lotto.exception.LottoException;

public class AmountPaid {

    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public AmountPaid(int amount) {
        validatePositiveValue(amount);
        validateDivideByLottoPrice(amount);
        this.amount = amount;
    }

    public String calculateProfitRate(int totalPrize) {
        double profitRate = (double) totalPrize / amount;
        return String.format("%.2f", Math.floor(profitRate * 100) * 0.01);
    }

    private void validatePositiveValue(int amount) {
        if (amount <= 0) {
            throw new LottoException(NOT_ALLOW_NEGATIVE);
        }
    }

    private void validateDivideByLottoPrice(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new LottoException(MUST_BE_DIVIDE_BY_THOUSAND);
        }
    }

    public int getLottoQuantity() {
        return amount / LOTTO_PRICE;
    }
}
