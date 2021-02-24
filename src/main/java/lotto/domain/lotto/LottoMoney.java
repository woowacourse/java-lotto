package lotto.domain.lotto;


import static lotto.domain.lotto.LottoTicket.LOTTO_PURCHASE_MONEY_LACK_ERROR;

import lotto.domain.PurchaseCount;

public class LottoMoney {

    public static final int LOTTO_LINE_PRICE = 1000;
    private final int value;

    public LottoMoney(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_LACK_ERROR);
        }
        this.value = value;
    }

    public LottoMoney spendLottoLine(PurchaseCount lottoPurchaseCount) {
        return new LottoMoney(value - lottoPurchaseCount.getValue() * LOTTO_LINE_PRICE);
    }

    public int getCanBuyLottoLineCount() {
        return value / LOTTO_LINE_PRICE;
    }

}
