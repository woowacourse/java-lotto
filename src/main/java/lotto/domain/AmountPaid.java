package lotto.domain;

import static lotto.exception.ErrorMessage.MUST_BE_DIVIDE_BY_THOUSAND;
import static lotto.exception.ErrorMessage.NOT_ALLOW_NEGATIVE;

import lotto.exception.LottoException;

public class AmountPaid {

    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public AmountPaid(int amount) {
        this.amount = amount;
        validateDivideByLottoPrice();
        validateNegativeValue();
    }

    public String calculateProfitRate(int totalPrize) {
        return String.format("%.2f", Math.floor(((double) totalPrize / (double) amount) * 100) * 0.01);
    }

    private void validateDivideByLottoPrice() {
        if (amount % LOTTO_PRICE != 0) {
            throw new LottoException(MUST_BE_DIVIDE_BY_THOUSAND);
        }
    }

    private void validateNegativeValue() {
        if (amount < 0) {
            throw new LottoException(NOT_ALLOW_NEGATIVE);
        }
    }

    public int getLottoQuantity() {
        return amount / LOTTO_PRICE;
    }
}
