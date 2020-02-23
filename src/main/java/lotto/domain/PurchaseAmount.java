package lotto.domain;

import lotto.Exception.NotBuyLottoTicketException;
import lotto.util.InputValidationUtil;

public class PurchaseAmount {
    private static final int LOTTO_PURCHASE_UNIT = 1000;
    public static final String NOT_BUY_LOTTO_TICKET_ERROR_MESSAGE = "한개도 구매할 수 없습니다. %d원을 반환합니다.";

    private long purchaseAmount;

    public PurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = InputValidationUtil.returnNumberWithNumberCheck(purchaseAmount);
        InputValidationUtil.isPositiveNumber(this.purchaseAmount);
        validateLottoUnit();
    }

    private void validateLottoUnit() {
        if (this.purchaseAmount < LOTTO_PURCHASE_UNIT) {
            throw new NotBuyLottoTicketException(String.format(NOT_BUY_LOTTO_TICKET_ERROR_MESSAGE,
                    this.purchaseAmount));
        }
    }

    public int giveLottoTicketNumber() {
        return (int) (this.purchaseAmount / LOTTO_PURCHASE_UNIT);
    }

    public int giveChangeMoney() {
        return (int) (this.purchaseAmount % LOTTO_PURCHASE_UNIT);
    }

    public long getPurchaseAmount() {
        return purchaseAmount;
    }
}
