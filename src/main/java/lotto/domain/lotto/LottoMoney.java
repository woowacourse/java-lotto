package lotto.domain.lotto;


import static lotto.domain.lotto.LottoTicket.LOTTO_LINE_PRICE;
import static lotto.domain.lotto.LottoTicket.LOTTO_PURCHASE_MONEY_LACK_ERROR;

import lotto.domain.PurchaseCount;

public class LottoMoney {

    private final int value;

    public LottoMoney(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_LACK_ERROR);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public LottoMoney spendLottoLine(PurchaseCount purchaseCount) {
        return new LottoMoney(value - purchaseCount.getValue() * LOTTO_LINE_PRICE);
    }

}
