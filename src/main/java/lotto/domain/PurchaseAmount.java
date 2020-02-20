package lotto.domain;

import lotto.Exception.NotBuyLottoTicketException;
import lotto.util.InputValidationUtil;

public class PurchaseAmount {

    private static final int LOTTO_PURCHASE_UNIT = 1000;
    private long purchaseAmount;

    public PurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = InputValidationUtil.returnNumberWithNumberCheck(purchaseAmount);
        InputValidationUtil.isPositiveNumber(this.purchaseAmount);
        underLottoUnit();
    }

    public int lottoTicket() {
        return (int) (this.purchaseAmount / LOTTO_PURCHASE_UNIT);
    }

    public int giveChangeMoney() {
        return (int) (this.purchaseAmount % LOTTO_PURCHASE_UNIT);
    }

    public void underLottoUnit() {
        if (this.purchaseAmount < LOTTO_PURCHASE_UNIT) {
            throw new NotBuyLottoTicketException("한개도 구매할 수 없습니다." + this.purchaseAmount + "원을 반환합니다.");
        }
    }

    public long getPurchaseAmount() {
        return purchaseAmount;
    }
}
